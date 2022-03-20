// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Java_Class.Java_ClassElement;
import com.eagle.programmar.Java.Java_Statement.Java_StatementBlock.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Type.Java_GenericType;
import com.eagle.programmar.Java.Terminals.Java_BinaryNumber;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_HexFloatingNumber;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Java_HexFloatingNumber hexFloat;
	public @P(20) Java_HexNumber hex;
	public @P(30) Java_BinaryNumber binary;
	public @P(40) Java_Number number;
	public @P(50) Java_Literal literal;
	public @P(60) Java_Character_Literal characters;
	public @P(70) Java_Annotation annotation;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Java_Expression()
	{
	    super(_operators);
	}

	public Java_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(90) class Java_DotClass extends PrimaryOperator
	{
		public @S(10) Java_Type jtype;
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE Java_Keyword CLASS = new Java_Keyword("class");
	}
	
	public static @P(100) class Java_LambdaExpression extends PrimaryOperator
	{
		public @S(10) Java_LambdaVariables params;
		public @S(20) Java_Punctuation arrow = new Java_Punctuation("->");
		public @S(30) Java_LambdaValue value;
		
		public static class Java_LambdaVariables extends TokenChooser
		{
			public @CHOICE Java_Identifier id;
			
			public @CHOICE static class Java_LambdaVariableList extends TokenSequence
			{
				public @S(10) PunctuationLeftParen lParen;
				public @S(20) @OPT SeparatedList<Java_Identifier,PunctuationComma> params;
				public @S(30) PunctuationRightParen rParen;
			}
			
			public @CHOICE static class Java_LambdaTypedVariableList extends TokenSequence
			{
				public @S(10) PunctuationLeftParen lParen;
				public @S(20) @OPT SeparatedList<Java_TypedIdentifier,PunctuationComma> params;
				public @S(30) PunctuationRightParen rParen;
				
				public static class Java_TypedIdentifier extends TokenSequence
				{
					public @S(10) Java_Type type;
					public @S(20) Java_Identifier id;
				}
			}
		}

		public static class Java_LambdaValue extends TokenChooser
		{
			public @CHOICE Java_Expression value;

			public @FIRST static class Java_LambdaBlock extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace;
				public @S(20) @OPT TokenList<Java_StatementOrComment> statements;
				public @S(30) PunctuationRightBrace rightBrace;
			}
		}
	}
			
	public static @P(110) class Java_CastExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Java_Type, PunctuationAmpersand> types;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) Java_Expression expr;
	}

	public static @P(120) class Java_ExpressionList extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<Java_Comment> comment;
		public @S(30) @OPT Java_ArgumentList valueList;
		public @S(40) PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class Java_InterfaceCreationWithMethod extends PrimaryOperator
	{
		public @S(10) Java_Keyword NEW = new Java_Keyword("new");
		public @S(20) Java_KeywordChoice jinterface = new Java_KeywordChoice( 
				"Runnable", "ActionListener", "WindowAdapter");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) Java_Method method;
		public @S(70) PunctuationRightBrace rightBrace;
	}

	public static @P(140) class Java_ClassCreationExpression extends PrimaryOperator
	{
		public @S(10) Java_Keyword NEW = new Java_Keyword("new");
		public @S(20) Java_Type jtype;
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @NOSPACE @OPT TokenList<Java_Comment> comments;
		public @S(50) @NOSPACE @OPT Java_ArgumentList argList;
		public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public @S(70) @OPT Java_ClassOverride override;

		public static class Java_ClassOverride extends TokenSequence
		{
			public @S(10) PunctuationLeftBrace leftBrace;
			public @S(20) @OPT TokenList<Java_ClassElement> elementList;
			public @S(30) PunctuationRightBrace rightBrace;
		}
	}
	
	public static @P(150) class Java_ClassCreationWithInitializers extends PrimaryOperator
	{
		public @S(10) Java_Keyword NEW = new Java_Keyword("new");
		public @S(20) Java_Type jtype;
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) @OPT Java_ArgumentList valueList;
		public @S(50) PunctuationRightBrace rightBrace;
	}
	
	public static @P(160) class Java_ClassCreationWithSubscript extends PrimaryOperator
	{
		public @S(10) Java_Keyword NEW = new Java_Keyword("new");
		public @S(20) Java_Type jtype;
		public @S(30) TokenList<Java_Subscript> subscripts;
	}
	
	public static @P(170) class Java_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Java_Variable methodName;
		public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(180) class Java_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Java_Punctuation preIncrementOperator = new Java_Punctuation("++");
		public @S(20) @NOSPACE Java_Variable var;
	}

	public static @P(190) class Java_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) Java_Punctuation preDecrementOperator = new Java_Punctuation("--");
		public @S(20) @NOSPACE Java_Variable var;
	}
	
	public static @P(200) class Java_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Java_Variable var;
		public @S(20) @NOSPACE Java_Punctuation postIncrementOperator = new Java_Punctuation("++");
	}

	public static @P(210) class Java_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) Java_Variable var;
		public @S(20) @NOSPACE Java_Punctuation postDecrementOperator = new Java_Punctuation("--");
	}
	
	public static @P(220) class Java_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Java_PunctuationChoice operator = new Java_PunctuationChoice("-", "+");
		public @S(20) Java_Expression expr;
	}

	public static @P(230) class Java_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Java_Punctuation logicalNotOperator = new Java_Punctuation('~');
		public @S(20) Java_Expression expr;
	}
	
	public static @P(240) class Java_NotExpression extends PrimaryOperator
	{
		public @S(10) Java_Punctuation notOperator = new Java_Punctuation('!');
		public @S(20) Java_Expression expr;
	}
	
	public static @P(250) class Java_BuiltIn extends PrimaryOperator
	{
		public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice("false", "true", "null", "this", "super");
	}
	
	public static @P(260) class Java_VariableExpression extends PrimaryOperator
	{
		public @S(10) Java_Variable variable;
	}
	
	public static @P(270) class Java_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @NOSPACE Java_Expression expression;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(280) class Java_CommentExpression extends PrimaryOperator
	{
		public @S(10) Java_Comment comment;
		public @S(20) Java_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(290) class Java_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression expr = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Java_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(300) class Java_Subfield extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @OPT @NOSPACE Java_GenericType genericType;
		public @S(40) @NOSPACE Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(305) class Java_ColonColon extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE Java_Punctuation colonColon = new Java_Punctuation("::");
		public @S(30) @NOSPACE Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(307) class Java_ColonColonNew extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE Java_Punctuation colonColon = new Java_Punctuation("::");
		public @S(30) @NOSPACE Java_Keyword NEW = new Java_Keyword("new");
	}

	public static @P(310) class Java_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("*", "/", "%");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(320) class Java_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("+", "-");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(330) class Java_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice(">>>", "<<", ">>");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(340) class Java_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(350) class Java_InstanceOfExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression expr = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Keyword instanceOperator = new Java_Keyword("instanceof");
		public @S(30) Java_Type type;
	}

	public static @P(360) class Java_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("==", "!=");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(370) class Java_AndExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Punctuation bitwiseAndOperator = new Java_Punctuation('&');
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(380) class Java_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Punctuation bitwiseXOrOperator = new Java_Punctuation('^');
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(390) class Java_InclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Punctuation bitwiseOrOperator = new Java_Punctuation('|');
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Java_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Punctuation andOperator = new Java_Punctuation("&&");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(410) class Java_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Java_Punctuation orOperator = new Java_Punctuation("||");
		public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(420) class Java_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Java_PunctuationChoice equals = new Java_PunctuationChoice(
				"=",
				"*=",
				"/=",
				"%=",
				"+=",
				"-=",
				"<<=",
				">>=",
				">>>=",
				"&=",
				"^=",
				"|=");
		public @S(30) Java_Expression expr;
	}
	
	public static @P(430) class Java_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Java_Punctuation questionMark = new Java_Punctuation('?');
		public @S(30) Java_Expression middle = new Java_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(40) PunctuationColon colon;
		public @S(50) Java_Expression right = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	}
}
