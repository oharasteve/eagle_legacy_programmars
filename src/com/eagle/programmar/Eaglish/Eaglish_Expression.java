// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Literal;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Number;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Eaglish_Number number;
	public @P(20) Eaglish_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Eaglish_Expression()
	{
	    super(_operators);
	}

	public Eaglish_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class Eaglish_FunctionCall extends PrimaryOperator
	{
		public @S(10) Eaglish_Identifier_Reference name;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT SeparatedList<Eaglish_Expression, PunctuationComma> args;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(110) class Eaglish_NegativeExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("-", "+");
		public @S(20) Eaglish_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int value = interpreter.getIntValue(expr);
			String oper = operator.getValue();
			switch (oper)
			{
			case "-" :
				interpreter.pushInt(-value);
				break;
			case "+" :
				interpreter.pushInt(value);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_NegativeExpression");	
			}
		}
	}

	public static @P(120) class Eaglish_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Eaglish_Expression expr;
		public @S(30) PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int value = interpreter.getIntValue(expr);
			interpreter.pushInt(value);
		}
	}

	public static @P(130) class Eaglish_VariableExpression extends PrimaryOperator
	{
		public @S(10) Eaglish_Variable variable;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Eaglish_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Eaglish_Expression expr = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Eaglish_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(510) class Eaglish_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_MultiplicationOperator operator;
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
		public static class Eaglish_MultiplicationOperator extends TokenChooser
		{
			public @CHOICE Eaglish_PunctuationChoice operSymbol = new Eaglish_PunctuationChoice("*");
			public @CHOICE Eaglish_KeywordChoice operWord = new Eaglish_KeywordChoice("DIVIDE_TRUNCATE", "REMAINDER");
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			AbstractToken which = operator.getWhich();
			if (which instanceof Eaglish_PunctuationChoice)
			{
				Eaglish_PunctuationChoice oper = (Eaglish_PunctuationChoice) which;
				switch (oper.getValue())
				{
				case "*":
					interpreter.pushInt(leftValue * rightValue);
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper + " in Eaglish_MultiplicativeExpression");	
				}
			}
			else if (which instanceof Eaglish_KeywordChoice)
			{
				Eaglish_KeywordChoice oper = (Eaglish_KeywordChoice) which;
				switch (oper.getValue())
				{
				case "DIVIDE_TRUNCATE":
					interpreter.pushInt(leftValue / rightValue);
					break;
				case "REMAINDER":
					interpreter.pushInt(leftValue % rightValue);
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper + " in Eaglish_MultiplicativeExpression");	
				}
			}
			else throw new RuntimeException("Unexpected operator: " + which.getClass().getName());
		}
	}

	public static @P(520) class Eaglish_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("+", "-");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			String oper = operator.getValue();
			switch (oper)
			{
			case "+":
				interpreter.pushInt(leftValue + rightValue);
				break;
			case "-":
				interpreter.pushInt(leftValue - rightValue);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_AdditiveExpression");	
			}
		}
	}
	
	public static @P(530) class Eaglish_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_RelationalOperator operator;
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		
		public static class Eaglish_RelationalOperator extends TokenChooser
		{
			public @CHOICE Eaglish_PunctuationChoice operSymbol = new Eaglish_PunctuationChoice("=", "<", ">", "<=", ">=");
			public @CHOICE Eaglish_KeywordChoice operWord = new Eaglish_KeywordChoice("NOT_EQUALS");
		}
	}
	
	public static @P(540) class Eaglish_ConditionStringMatch extends PrecedenceOperator
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_KeywordChoice matchOperator = new Eaglish_KeywordChoice("ENDS_WITH", "STARTS_WITH");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) @OPT Eaglish_Condition_MatchAt atClause;
		
		public static class Eaglish_Condition_MatchAt extends TokenSequence
		{
			public @S(10) Eaglish_Keyword AT = new Eaglish_Keyword("AT");
			public @S(20) Eaglish_Expression position;
		}
	}
	
	public static @P(550) class Eaglish_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_Keyword andOperator = new Eaglish_Keyword("AND");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Eaglish_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_KeywordChoice orOperator = new Eaglish_KeywordChoice("OR", "XOR");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	}
}