// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_FunctionDefinition;
import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_FunctionVariable;
import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_FunctionVariableOrTypeVariable;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Perl_Identifier_Reference fnName;
	public @S(20) @OPT TokenList<Perl_MoreFunctionName> moreName;
	public @S(30) @OPT TokenList<Perl_Method> perlMethods;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
	public @S(60) @OPT Perl_Expression argument;
	public @S(70) @OPT TokenList<Perl_MoreFnArguments> moreArgs;
	public @S(80) PunctuationRightParen rightParen;

	public static class Perl_MoreFunctionName extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference fnName;
	}

	public static class Perl_Method extends TokenSequence
	{
		public @S(10) Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @S(20) Perl_Identifier_Reference fnName;
	}

	public static class Perl_MoreFnArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Perl_Comment comment;
		public @S(30) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(40) Perl_Expression argument;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();
		
		// See if it is a predefined function, like array()
		if (name.equals("array"))
		{
			EagleArray array = new EagleArray();
			EagleValue arg = interpreter.getEagleValue(argument);
			array.addValue(arg);
			for (Perl_MoreFnArguments more : moreArgs._elements)
			{
				arg = interpreter.getEagleValue(more.argument);
				array.addValue(arg);
			}
			interpreter.pushEagleValue(array);
			return;
		}
		if (name.equals("strlen"))
		{
			String str = interpreter.getStrValue(argument);
			interpreter.pushInt(str.length());
			return;
		}
		if (name.equals("substr"))
		{
			String str = interpreter.getStrValue(argument);
			int sc = interpreter.getIntValue(moreArgs._elements.get(0).argument);
			int nc = interpreter.getIntValue(moreArgs._elements.get(1).argument);
			int len = str.length();
			if (sc + nc > len) nc = len - sc;	// Don't go past the end of the string
			interpreter.pushStr(str.substring(sc, sc + nc));
			return;
		}
		
		// Look up the function
		AbstractFunction fn = interpreter._functionList.get(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Perl_FunctionDefinition func = (Perl_FunctionDefinition) fn;
	
		// Make sure the function args match up
		int argCount = 0;
		if (argument != null && argument.isPresent()) argCount++;
		if (moreArgs != null && moreArgs.isPresent()) argCount += moreArgs.size();
		int paramCount = 0;
		if (func.params.param != null && func.params.param.isPresent()) paramCount++;
		if (func.params.moreParams != null && func.params.moreParams.isPresent()) paramCount += func.params.moreParams.size();
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}
	
		// Now assign all the parameters
		Perl_Expression arg = argument;
		Perl_FunctionVariableOrTypeVariable param = func.params.param;
		for (int i = 0; i < argCount; i++)
		{
			if (i > 0)
			{
				arg = moreArgs._elements.get(i-1).argument;
				param = func.params.moreParams._elements.get(i-1).var;
			}
			if (param.getWhich() instanceof Perl_FunctionVariable)
			{
				Perl_FunctionVariable fnVar = (Perl_FunctionVariable) param.getWhich();
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						fnVar.param.getValue(), val);
			}
		}
	
		// Prepare to evaluate the method
		long startTime = System.nanoTime();
	
		// And transfer control to the method
		interpreter.tryToInterpret(func.block);
	
		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);
	
		// Now remove all those parameters
		param = func.params.param;
		for (int i = 0; i < argCount; i++)
		{
			if (i > 0)
			{
				param = func.params.moreParams._elements.get(i-1).var;
			}
			if (param.getWhich() instanceof Perl_FunctionVariable)
			{
				Perl_FunctionVariable fnVar = (Perl_FunctionVariable) param.getWhich();
				interpreter._symbolTable.removeSymbols(fnVar.param.getValue());
			}
		}
	}
}
