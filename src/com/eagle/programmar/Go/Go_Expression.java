// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Go_Syntax.Go_Multiline_Syntax;
import com.eagle.programmar.Go.Symbols.Go_Field_Definition;
import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.programmar.Go.Terminals.Go_Literal;
import com.eagle.programmar.Go.Terminals.Go_Number;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Go_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Go_Number number;
	public @P(20) Go_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Go_Expression()
	{
	    super(_operators);
	}

	public Go_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Go_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Go_Variable methodName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT @SYNTAX(Go_Multiline_Syntax.class) SeparatedList<Go_Expression,PunctuationComma> argList;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(110) class Go_Constructor extends PrimaryOperator
	{
		public @S(10) Go_Variable className;
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) @OPT  @SYNTAX(Go_Multiline_Syntax.class) SeparatedList<Go_ConstructField,PunctuationComma> fields;
		public @S(40) PunctuationRightBrace rightBrace;
		
		public static class Go_ConstructField extends TokenSequence
		{
			public @S(10) Go_Field_Definition fieldName;
			public @S(20) PunctuationColon colon;
			public @S(30) Go_Expression value;
		}
	}

	public static @P(120) class Go_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Go_Punctuation preIncrementOperator = new Go_Punctuation("++");
		public @S(20) Go_Variable var;
	}

	public static @P(130) class Go_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) Go_Punctuation preDecrementOperator = new Go_Punctuation("--");
		public @S(20) Go_Variable var;
	}
	
	public static @P(140) class Go_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Go_Variable var;
		public @S(20) Go_Punctuation postIncrementOperator = new Go_Punctuation("++");
	}

	public static @P(150) class Go_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) Go_Variable var;
		public @S(20) Go_Punctuation postDecrementOperator = new Go_Punctuation("--");
	}

	public static @P(160) class Go_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Go_PunctuationChoice operator = new Go_PunctuationChoice("-");
		public @S(20) Go_Expression expr;
	}

	public static @P(170) class Go_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Go_Punctuation logicalNotOperator = new Go_Punctuation('~');
		public @S(20) Go_Expression expr;
	}
	
	public static @P(180) class Go_NotExpression extends PrimaryOperator
	{
		public @S(10) Go_Punctuation notOperator = new Go_Punctuation('!');
		public @S(20) Go_Expression expr;
	}
	
	public static @P(190) class Go_BuiltIn extends PrimaryOperator
	{
		public @S(10) Go_KeywordChoice builtinConstant = new Go_KeywordChoice("false", "true");
	}
	
	public static @P(200) class Go_VariableExpression extends PrimaryOperator
	{
		public @S(10) Go_Variable variable;
	}
	
	public static @P(210) class Go_BracesExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) SeparatedList<Go_Expression,PunctuationComma> expression;
		public @S(30) PunctuationRightBrace rightBrace;
	}

	public static @P(220) class Go_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Go_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(230) class Go_EmptyArrayExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
		public @S(30) Go_Expression expression;
	}
	
	public static @P(240) class Go_StarExpression extends PrimaryOperator
	{
		public @S(10) PunctuationStar star;
		public @S(20) Go_Expression expression;
	}

	public static @P(250) class Go_AmpersandExpression extends PrimaryOperator
	{
		public @S(10) PunctuationAmpersand ampersand;
		public @S(20) Go_Expression expression;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Go_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression expr = new Go_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Go_Expression subscr1;
		public @S(40) @OPT PunctuationColon colon;
		public @S(50) @OPT Go_Expression subscr2;
		public @S(60) PunctuationRightBracket rightBracket;
	}

	public static @P(510) class Go_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("*", "/", "%");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Go_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("+", "-");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class Go_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class Go_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("==", "!=");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(550) class Go_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_Punctuation andOperator = new Go_Punctuation("&&");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Go_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression left = new Go_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Go_Punctuation orOperator = new Go_Punctuation("||");
		public @S(30) Go_Expression right = new Go_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(570) class Go_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Go_Expression var = new Go_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice(
				":=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=");
		public @S(30) Go_Expression expr;
	}
}
