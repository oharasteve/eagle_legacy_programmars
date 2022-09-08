// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CMacro;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Expression.CMacro_FunctionCall.CMacroFunctionParens;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_Character_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_HexNumber;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.programmar.CMacro.Terminals.CMacro_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) CMacro_HexNumber hex;
	public @P(20) CMacro_Number number;
	public @P(30) CMacro_Literal literal;
	public @P(40) CMacro_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public CMacro_Expression()
	{
	    super(_operators);
	}

	public CMacro_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class CMacro_FunctionCall extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) CMacro_Keyword DEFINED = new CMacro_Keyword("defined");
		public @S(20) CMacro_FunctionType funcType;
		
		public static class CMacro_FunctionType extends TokenChooser
		{
			public @CHOICE CMacro_Identifier_Reference variable;
			public @CHOICE CMacroFunctionParens params;
		}
		
		public static class CMacroFunctionParens extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT CMacro_Identifier_Reference variable;
			public @S(30) PunctuationRightParen rightParen;
		}
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = funcType.getWhich();
			String name;
			if (which instanceof CMacro_Identifier_Reference)
			{
				name = ((CMacro_Identifier_Reference) which).toString();
			}
			else if (which instanceof CMacroFunctionParens)
			{
				name = ((CMacroFunctionParens) which).variable.toString();
			}
			else
			{
				throw new RuntimeException("Unexpected token: " + which.toString());
			}
			boolean val = interpreter._symbolTable.isDefined(name);
			interpreter.pushBool(val);
		}
	}
	
	public static @P(110) class CMacro_IdentifierExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) CMacro_Identifier_Reference identifier;
		public @S(20) @OPT CMacroFunctionParens params;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(identifier);
		}
	}

	public static @P(120) class CMacro_SignedExpression extends PrimaryOperator
	{
		public @S(10) CMacro_PunctuationChoice signedOperator = new CMacro_PunctuationChoice("+", "-");
		public @S(20) CMacro_Expression expr;
	}

	public static @P(130) class CMacro_NotExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) CMacro_Punctuation notOperator = new CMacro_Punctuation('!');
		public @S(20) CMacro_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean val = interpreter.getBoolValue(expr);
			interpreter.pushBool(! val);
		}
	}
	
	public static @P(140) class CMacro_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) CMacro_Expression expression;
		public @S(30) PunctuationRightParen rightParen;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(val);
		}
	}

	public static @P(150) class CMacro_SymbolExpression extends PrimaryOperator
	{
		public @S(10) CMacro_Punctuation poundOperator = new CMacro_Punctuation('#');
		public @S(20) CMacro_Expression expr;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class CMacro_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_PunctuationChoice operator = new CMacro_PunctuationChoice("*", "/", "%");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			interpreter.pushInt(leftVal * rightVal);
		}
	}
	
	public static @P(510) class CMacro_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_PunctuationChoice operator = new CMacro_PunctuationChoice("+", "-");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			interpreter.pushInt(leftVal + rightVal);
		}
	}

	public static @P(520) class CMacro_RelationalExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_PunctuationChoice operator = new CMacro_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			String oper = operator.getValue();
			if (oper.equals("<")) interpreter.pushBool(leftVal < rightVal);
			else if (oper.equals(">")) interpreter.pushBool(leftVal > rightVal);
			else if (oper.equals("<=")) interpreter.pushBool(leftVal >= rightVal);
			else if (oper.equals(">=")) interpreter.pushBool(leftVal >= rightVal);
			else throw new RuntimeException("Unexpected operator: " + oper);
		}
	}

	public static @P(530) class CMacro_EqualityExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_PunctuationChoice operator = new CMacro_PunctuationChoice("==", "!=");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftVal = interpreter.getIntValue(left);
			int rightVal = interpreter.getIntValue(right);
			String oper = operator.getValue();
			if (oper.equals("==")) interpreter.pushBool(leftVal == rightVal);
			else if (oper.equals("!=")) interpreter.pushBool(leftVal != rightVal);
			else throw new RuntimeException("Unexpected operator: " + oper);
		}
	}
	
	public static @P(540) class CMacro_BitwiseAndExpression extends PrecedenceOperator
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation bitwiseAndOperator = new CMacro_Punctuation('&');
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(550) class CMacro_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation bitwiseXOrOperator = new CMacro_Punctuation('^');
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(560) class CMacro_BitwiseOrExpression extends PrecedenceOperator
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation bitwiseOrOperator = new CMacro_Punctuation('|');
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(570) class CMacro_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation andOperator = new CMacro_Punctuation("&&");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftVal = interpreter.getBoolValue(left);
			if (! leftVal)
			{
				// Short circuit a bit
				interpreter.pushBool(false);
			}
			else
			{
				boolean rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(rightVal);
			}
		}
	}
		
	public static @P(580) class CMacro_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation orOperator = new CMacro_Punctuation("||");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			boolean leftVal = interpreter.getBoolValue(left);
			if (leftVal)
			{
				// Short circuit a bit
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightVal = interpreter.getBoolValue(right);
				interpreter.pushBool(rightVal);
			}
		}
	}
	
	public static @P(590) class CMacro_ConcatenateExpression extends PrecedenceOperator
	{
		public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CMacro_Punctuation poundOperator = new CMacro_Punctuation("##");
		public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	}

	//////////////////////////////////////////////////////////////
	// Evaluation routine, for macros
	
	public boolean getBooleanValue(CMacro_Preprocess preprocessor)
	{
		AbstractToken which = _whichToken;
		if (! (which instanceof EagleRunnable))
		{
			throw new RuntimeException("Need to implement EagleRunnable for " + which);
		}
		
		EagleRunnable runnable = (EagleRunnable) which;
		CMacro_Interpreter interpreter = new CMacro_Interpreter(preprocessor._parser, preprocessor._symbolTable);
		runnable.interpret(interpreter);
		boolean val = interpreter.getBoolValue(this);
		return val;
	}
}
