// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CMD.CMD_BasicExpression;
import com.eagle.programmar.CMD.CMD_Label;
import com.eagle.programmar.CMD.CMD_Program;
import com.eagle.programmar.CMD.CMD_Program.CMD_CommandOrLabel;
import com.eagle.programmar.CMD.Symbols.CMD_Label_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CMD_Call_Statement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("call.mspx") CMD_Keyword CALL = new CMD_Keyword("call");
	public @S(20) @OPT PunctuationColon colon;
	public @S(30) CMD_Label_Reference label;
	public @S(40) @OPT TokenList<CMD_Call_Argument> args;

	public static class CMD_Call_Argument extends TokenChooser
	{
		public @CHOICE CMD_ExpressionComma XXexpressionComma;
		public @CHOICE CMD_Call_Option XXcallOption;
	}

	public static class CMD_ExpressionComma extends TokenSequence
	{
		public @S(10) CMD_BasicExpression arg;
		public @S(20) @OPT PunctuationComma comma;
	}

	public static class CMD_Call_Option extends TokenSequence
	{
		public @S(10) CMD_PunctuationChoice minus = new CMD_PunctuationChoice("-", "/");
		public @S(20) CMD_BasicExpression option;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Look it up
		String name = label.getValue();
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a label named " + name);
		}
		CMD_Label func = (CMD_Label) fn;
		// AbstractFunction saveFunc = interpreter._currentFunction;	// Often null
		interpreter.setCurrentFunction(func);

		// Now assign all the parameters (%1 %2 etc)
		int argCount = 0;
		if (args != null && args.isPresent())
		{
			for (CMD_Call_Argument arg : args._elements)
			{
				if (arg.getWhich() instanceof CMD_ExpressionComma)
				{
					CMD_ExpressionComma argComma = (CMD_ExpressionComma) arg.getWhich();
					argCount++;
					EagleValue val = interpreter.getEagleValue(argComma.arg);
					interpreter.setSymbol(arg.getFileName(), arg.getStartLine(), arg.getStartChar(),
							"%~" + argCount, val);
				}
			}
		}

		// Prepare to evaluate the label
		long startTime = System.nanoTime();

		// And transfer control to the label
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		CMD_Program pgm = (CMD_Program) interpreter._lang;
		boolean foundLabel = false;
		for (CMD_CommandOrLabel cmdOr : pgm.commands._elements)
		{
			if (foundLabel)
			{
				result = interpreter.tryToInterpret(cmdOr);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			else   // Have to search for our label (aka function)
			{
				if (cmdOr.getWhich() instanceof CMD_Label)
				{
					CMD_Label lbl = (CMD_Label) cmdOr.getWhich();
					if (lbl == func)    // Careful, comparing Objects here
					{
						foundLabel = true;
					}
				}
			}
		}
		if (!foundLabel)
		{
			throw new RuntimeException("Unable to re-find label " + name);
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// interpreter._currentFunction = saveFunc;	// Restore previous value
	}
}
