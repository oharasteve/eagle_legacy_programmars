// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using IntelASM_EquDirective = com.eagle.programmar.IntelASM.Directives.IntelASM_EquDirective;
	using IntelASM_DB = com.eagle.programmar.IntelASM.Instructions.IntelASM_DB;
	using IntelASM_DQ = com.eagle.programmar.IntelASM.Instructions.IntelASM_DQ;
	using IntelASM_Comment = com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
	using IntelASM_EndOfLine = com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class IntelASM_Program : AbstractLanguage, EagleRunnable, EagleScope.EagleScopeInterface
	{
		public const string INTELASM = "IntelASM";

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<IntelASM_Line> lines;
		public TokenList<IntelASM_Line> lines;

		public class IntelASM_Line : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_EndOfLine XXblankLine;
			public IntelASM_EndOfLine XXblankLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Comment XXcomment;
			public IntelASM_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Directive XXdirective;
			public IntelASM_Directive XXdirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Instruction XXinstruction;
			public IntelASM_Instruction XXinstruction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Label XXlabel;
			public IntelASM_Label XXlabel;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, IntelASM_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, IntelASM_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public IntelASM_Program() : base(INTELASM, new IntelASM_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "TBD";
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			IntelASM_StateMachine state = new IntelASM_StateMachine();
			interpreter._state = state;
			int numLines = lines.size();

			// Two passes. First collect data, etc.
			state._currentLine = 0;
			foreach (IntelASM_Line line in lines._elements)
			{
				if (line.getWhich() is IntelASM_Instruction)
				{
					IntelASM_Instruction instr = (IntelASM_Instruction) line.getWhich();
					AbstractToken which = instr.instruction.getWhich();
					if (which is IntelASM_DB || which is IntelASM_DQ)
					{
						state._dollarLine = state._memoryUsed; // Beginning of the line
						interpreter.tryToInterpret(which); // Data only (DB or DQ)
					}
				}
				else if (line.getWhich() is IntelASM_Directive)
				{
					IntelASM_Directive direct = (IntelASM_Directive) line.getWhich();
					AbstractToken which = direct.directive.getWhich();
					if (which is IntelASM_EquDirective)
					{
						state._dollarLine = state._memoryUsed; // Beginning of the line
						interpreter.tryToInterpret(which); // EQU only
					}
					else
					{
						interpreter.tryToInterpret(line);
					}
				}
				else
				{
					if (state._TRACE && line.getWhich() is IntelASM_Label)
					{
						string nxt = state._prt.writeToken(line); // Usually includes a newline
						Console.Write("**** Label " + state._currentLine + ": " + nxt);
						if (!nxt.EndsWith("\n", StringComparison.Ordinal))
						{
							Console.WriteLine();
						}
					}
					interpreter.tryToInterpret(line); // Should never be a return code from data
				}
				state._currentLine++;
			}

			// Find the "GLOBAL _start" entry
			if (string.ReferenceEquals(state._startLabel, null))
			{
				throw new Exception("Unable to find a start label");
			}
			if (!state._labels.ContainsKey(state._startLabel))
			{
				throw new Exception("Unable to find start label: " + state._startLabel);
			}
			state._nextInstruction = state._labels[state._startLabel];

			// Second pass to run logic
			while (state._nextInstruction < numLines)
			{
				IntelASM_Line line = lines._elements.get(state._nextInstruction);

				if (state._TRACE)
				{
					string nxt = state._prt.writeToken(line); // Usually includes a newline
					Console.Write("**** Line " + state._nextInstruction + ": " + nxt);
					if (!nxt.EndsWith("\n", StringComparison.Ordinal))
					{
						Console.WriteLine();
					}
				}

				// Default is to run the next instruction
				// Might get changed by a GOTO / CALL / RET etc.
				state._nextInstruction++;

				AbstractToken which = line.getWhich();
				if (which is IntelASM_Label || which is IntelASM_EndOfLine || which is IntelASM_Comment)
				{
					// Already processed labels above
					continue;
				}
				if (!(which is IntelASM_Instruction))
				{
					// Should not try to execute data!
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getCanonicalName method:
					throw new Exception("Line should be an instruction, not " + which.GetType().FullName);
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
}
