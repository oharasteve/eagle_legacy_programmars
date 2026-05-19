// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_ExpressionFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
	public @S(20) COBOL_FunctionName func;
	public @S(30) @OPT COBOL_FunctionArgs args;

	public static class COBOL_FunctionName extends TokenChooser
	{
		public @FIRST COBOL_KeywordChoice XXbuiltins = new COBOL_KeywordChoice("CURRENT-DATE", "INTEGER-OF-DATE",
				"LENGTH", "LOWER-CASE", "MOD", "ORD-MAX", "ORD-MIN", "RANDOM", "REM", "REVERSE", "TRIM", "UPPER-CASE");

		public @CHOICE COBOL_Variable XXuserFunc;
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
		if (!(which instanceof COBOL_KeywordChoice))
		{
			throw new RuntimeException("UNable to evaluate function " + which);
		}
		COBOL_KeywordChoice funcKeyword = (COBOL_KeywordChoice) which;
		String funcName = funcKeyword.getValue();
		switch (funcName)
		{
		case "LENGTH":
			String str1 = firstStringArg(interpreter, funcName);
			interpreter.pushInt(str1.length());
			break;
		case "REM":
			int x1 = firstIntArg(interpreter, funcName);
			int y1 = secondIntArg(interpreter, funcName);
			interpreter.pushInt(x1 % y1);
			break;
		case "MOD":
			int x2 = firstIntArg(interpreter, funcName);
			int y2 = secondIntArg(interpreter, funcName);
			interpreter.pushInt(Math.floorMod(x2, y2));
			break;
		case "TRIM":
			String str2 = firstStringArg(interpreter, funcName);
			boolean leading = true;
			COBOL_FunctionParameter arg = args.parameters.first();
			if (arg.LEADING.isPresent())
			{
				if (arg.LEADING.getValue().equals("TRAILING")) leading = false;
			}
			String str3;
			if (leading)
			{
				str3 = str2.stripLeading();
			}
			else
			{
				str3 = str2.stripTrailing();
			}
			interpreter.pushStr(str3);
			break;
		default:
			throw new RuntimeException("Unable to evaluate function " + func);
		}
	}

	private String firstStringArg(EagleInterpreter interpreter, String funcName)
	{
		if (!args.isPresent())
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
	
	private int firstIntArg(EagleInterpreter interpreter, String funcName)
	{
		if (!args.isPresent())
		{
			throw new RuntimeException("Argument required for function " + funcName);
		}
		if (args.parameters._elements.size() < 1)
		{
			throw new RuntimeException("Function " + funcName + " requires at least one argument");
		}
		COBOL_FunctionParameter arg = args.parameters.first();
		int value = interpreter.getIntValue(arg.parameter);
		return value;
	}
	
	private int secondIntArg(EagleInterpreter interpreter, String funcName)
	{
		if (!args.isPresent())
		{
			throw new RuntimeException("Argument required for function " + funcName);
		}
		if (args.parameters._elements.size() < 2)
		{
			throw new RuntimeException("Function " + funcName + " requires at least two arguments");
		}
		COBOL_FunctionParameter arg = args.parameters._elements.get(1);
		int value = interpreter.getIntValue(arg.parameter);
		return value;
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = func.getWhich();
		if (!(which instanceof COBOL_KeywordChoice))
		{
			throw new RuntimeException("UNable to evaluate function " + which);
		}
		COBOL_KeywordChoice funcKeyword = (COBOL_KeywordChoice) which;
		String funcName = funcKeyword.getValue();
		switch (funcName)
		{
		case "REM":
		case "MOD":
			break;
		default:
			throw new RuntimeException("Unable to transform FUNCTION " + funcName);
		}

		if (!args.isPresent())
		{
			throw new RuntimeException("Argument required for function " + funcName);
		}
		if (args.parameters._elements.size() < 2)
		{
			throw new RuntimeException("Function " + funcName + " requires at least two arguments");
		}
		COBOL_FunctionParameter left = args.parameters._elements.get(0);
		COBOL_FunctionParameter right = args.parameters._elements.get(1);
		AbstractExpression leftExpr = transformer.transformExpression(generator, left.parameter);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right.parameter);
		switch (funcName)
		{
		case "REM":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		case "MOD":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.MODULUS, rightExpr, this);
		}
		throw new RuntimeException("Unable to transform FUNCTION " + funcName);	// Redundant, see above
	}
}
