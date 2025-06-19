// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.programmar.IntelASM.Directives.IntelASM_EquDirective;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_DB;
import com.eagle.programmar.IntelASM.Instructions.IntelASM_DQ;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class IntelASM_Program extends AbstractLanguage
		implements EagleRunnable, EagleScopeInterface
{
	public static final String INTELASM = "IntelASM";
	
	public @S(10) TokenList<IntelASM_Line> lines;

	public static class IntelASM_Line extends TokenChooser
	{
		public @CHOICE IntelASM_EndOfLine XXblankLine;
		public @CHOICE IntelASM_Comment XXcomment;
		public @CHOICE IntelASM_Directive XXdirective;
		public @CHOICE IntelASM_Instruction XXinstruction;
		public @CHOICE IntelASM_Label XXlabel;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, IntelASM_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	public IntelASM_Program()
	{
		super(INTELASM, new IntelASM_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = new IntelASM_StateMachine();
		interpreter._state = state;
		int numLines = lines.size();

		// Two passes. First collect data, etc.
		state._currentLine = 0;
		for (IntelASM_Line line : lines._elements)
		{
			if (line.getWhich() instanceof IntelASM_Instruction)
			{
				IntelASM_Instruction instr = (IntelASM_Instruction) line.getWhich();
				AbstractToken which = instr.instruction.getWhich();
				if (which instanceof IntelASM_DB || which instanceof IntelASM_DQ)
				{
					state._dollarLine = state._memoryUsed;	// Beginning of the line
					interpreter.tryToInterpret(which);		// Data only (DB or DQ)
				}
			}
			else if (line.getWhich() instanceof IntelASM_Directive)
			{
				IntelASM_Directive direct = (IntelASM_Directive) line.getWhich();
				AbstractToken which = direct.directive.getWhich();
				if (which instanceof IntelASM_EquDirective)
				{
					state._dollarLine = state._memoryUsed;	// Beginning of the line
					interpreter.tryToInterpret(which);		// EQU only
				}
				else
				{
					interpreter.tryToInterpret(line);
				}
			}
			else
			{
				if (state._TRACE && line.getWhich() instanceof IntelASM_Label)
				{
					String nxt = state._prt.writeToken(line); // Usually includes a newline
					System.out.print("**** Label " + state._currentLine + ": " + nxt);
					if (! nxt.endsWith("\n"))
					{
						System.out.println();
					}
				}
				interpreter.tryToInterpret(line); // Should never be a return code from data
			}
			state._currentLine++;
		}
		
		// Find the "GLOBAL _start" entry
		if (state._startLabel == null)
		{
			throw new RuntimeException("Unable to find a start label");
		}
		if (! state._labels.containsKey(state._startLabel))
		{
			throw new RuntimeException("Unable to find start label: " + state._startLabel);
		}
		state._nextInstruction = state._labels.get(state._startLabel);
		
		// Second pass to run logic
		while (state._nextInstruction < numLines)
		{
			IntelASM_Line line = lines._elements.get(state._nextInstruction);
			
			if (state._TRACE)
			{
				String nxt = state._prt.writeToken(line); // Usually includes a newline
				System.out.print("**** Line " + state._nextInstruction + ": " + nxt);
				if (! nxt.endsWith("\n"))
				{
					System.out.println();
				}
			}
			
			// Default is to run the next instruction
			// Might get changed by a GOTO / CALL / RET etc.
			state._nextInstruction ++;

			AbstractToken which = line.getWhich();
			if (which instanceof IntelASM_Label ||
					which instanceof IntelASM_EndOfLine ||
					which instanceof IntelASM_Comment)
			{
				// Already processed labels above
				continue;
			}
			if (! (which instanceof IntelASM_Instruction))
			{
				// Should not try to execute data!
				throw new RuntimeException("Line should be an instruction, not " +
						which.getClass().getCanonicalName());
			}
						
			Eagle_Statement_Result result = interpreter.tryToInterpret(line);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				// Should only get here for an EXIT (RAX=60; SYSCALL)
				// GOTO, CALL, RET should all be handled by themselves.
				// Although they can diddle with _state._nextInstruction
				break;
			}
		}
	}
}