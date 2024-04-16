// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_ExpressionFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
	public @S(20) COBOL_FunctionName func;
	public @S(30) @OPT COBOL_FunctionArgs args;
	
	public static class COBOL_FunctionName extends TokenChooser
	{
		public @FIRST COBOL_KeywordChoice builtins = new COBOL_KeywordChoice(
				"CURRENT-DATE",
				"INTEGER-OF-DATE",
				"LENGTH",
				"LOWER-CASE",
				"ORD-MAX",
				"ORD-MIN",
				"RANDOM",
				"REM",
				"REVERSE",
				"TRIM",
				"UPPER-CASE"
		);
		
		public @CHOICE COBOL_Variable userFunc;
	}

	public static class COBOL_FunctionParameter extends TokenSequence
	{
		public @S(10) COBOL_Expression parameter;
		public @S(20) @OPT COBOL_ExpressionFunctionRange range;
		public @S(30) @OPT COBOL_KeywordChoice LEADING = new COBOL_KeywordChoice("LEADING", "TRAILING");
		public @S(40) @OPT PunctuationComma comma;
		
		public static class COBOL_ExpressionFunctionRange extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) COBOL_Expression parameter;
		}
	}

	public static class COBOL_FunctionArgs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) TokenList<COBOL_FunctionParameter> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = func.getWhich();
		if (! (which instanceof COBOL_KeywordChoice))
		{
			throw new RuntimeException("UNable to evaluate function " + which);
		}
		COBOL_KeywordChoice funcKeyword = (COBOL_KeywordChoice) which;
		String funcName = funcKeyword.getValue();
		switch (funcName)
		{
		case "LENGTH":
			String str1 = oneStringArg(interpreter, funcName);
			interpreter.pushInt(str1.length());
			break;
		case "TRIM":
			String str2 = oneStringArg(interpreter, funcName);
			interpreter.pushStr(str2.trim());
			break;
		default:
			throw new RuntimeException("UNable to evaluate function " + func);
		}
	}
	
	private String oneStringArg(EagleInterpreter interpreter, String funcName)
	{
		if (! args.isPresent())
		{
			throw new RuntimeException("Argument required for function " + funcName);
		}
		if (args.parameters._elements.size() != 1)
		{
			throw new RuntimeException("Function " + funcName + " requires exactly one argument");
		}
		COBOL_FunctionParameter arg = args.parameters.first();
		String value = interpreter.getStrValue(arg.parameter);
		return value;
	}
}

