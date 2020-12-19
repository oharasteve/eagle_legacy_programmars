// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Terminals.Rust_BinaryNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Identifier;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Rust_BinaryNumber bin;
	public @P(20) Rust_HexNumber hex;
	public @P(40) Rust_Number number;
	public @P(50) Rust_Literal literal;
	public @P(60) Rust_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Rust_Expression()
	{
	    super(_operators);
	}

	public Rust_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(90) class Rust_DotClass extends PrimaryOperator
	{
		public @S(10) Rust_Type jtype;
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE Rust_Keyword CLASS = new Rust_Keyword("class");
	}
	
	public static @P(100) class Rust_LambdaExpression extends PrimaryOperator
	{
		public @S(10) Rust_LambdaVariables params;
		public @S(20) Rust_Punctuation arrow = new Rust_Punctuation("->");
//		public Rust_LambdaValue value;
		
		public static class Rust_LambdaVariables extends TokenChooser
		{
			public @CHOICE Rust_Identifier id;
			
			public @CHOICE static class Rust_LambdaVariableList extends TokenSequence
			{
				public @S(10) PunctuationLeftParen lParen;
				public @S(20) @OPT SeparatedList<Rust_Identifier,PunctuationComma> params;
				public @S(30) PunctuationRightParen rParen;
			}
			
			public @CHOICE static class Rust_LambdaTypedVariableList extends TokenSequence
			{
				public @S(10) PunctuationLeftParen lParen;
				public @S(20) @OPT SeparatedList<Rust_TypedIdentifier,PunctuationComma> params;
				public @S(30) PunctuationRightParen rParen;
				
				public static class Rust_TypedIdentifier extends TokenSequence
				{
					public @S(10) Rust_Type type;
					public @S(20) Rust_Identifier id;
				}
			}
		}

//		public static class Rust_LambdaValue extends TokenChooser
//		{
//			public @CHOICE Rust_Expression value;
//
//			public @FIRST static class Rust_LambdaBlock extends TokenSequence
//			{
//				public PunctuationLeftBrace leftBrace;
//				public @OPT TokenList<Rust_StatementOrComment> statements;
//				public PunctuationRightBrace rightBrace;
//			}
//		}
	}
			
	public static @P(110) class Rust_CastExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Rust_Type, PunctuationAmpersand> types;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) Rust_Expression expr;
	}

//	public static @P(120) class Rust_ExpressionList extends PrimaryOperator
//	{
//		public PunctuationLeftBrace leftBrace;
//		public @OPT TokenList<Rust_Comment> comment;
//		public @OPT Rust_ArgumentList valueList;
//		public PunctuationRightBrace rightBrace;
//	}
	
//	public static @P(130) class Rust_InterfaceCreationWithMethod extends PrimaryOperator
//	{
//		public Rust_Keyword NEW = new Rust_Keyword("new");
//		public Rust_KeywordChoice jinterface = new Rust_KeywordChoice( 
//				"Runnable", "ActionListener", "WindowAdapter");
//		public PunctuationLeftParen leftParen;
//		public PunctuationRightParen rightParen;
//		public PunctuationLeftBrace leftBrace;
//		public Rust_Method method;
//		public PunctuationRightBrace rightBrace;
//	}

//	public static @P(140) class Rust_ClassCreationExpression extends PrimaryOperator
//	{
//		public Rust_Keyword NEW = new Rust_Keyword("new");
//		public Rust_Type jtype;
//		public @NOSPACE PunctuationLeftParen leftParen;
//		public @NOSPACE @OPT TokenList<Rust_Comment> comments;
//		public @NOSPACE @OPT Rust_ArgumentList argList;
//		public @NOSPACE PunctuationRightParen rightParen;
//		public @OPT Rust_ClassOverride override;
//
//		public static class Rust_ClassOverride extends TokenSequence
//		{
//			public PunctuationLeftBrace leftBrace;
//			public TokenList<Rust_ClassElement> elementList;
//			public PunctuationRightBrace rightBrace;
//		}
//	}
	
//	public static @P(150) class Rust_ClassCreationWithInitializers extends PrimaryOperator
//	{
//		public Rust_Keyword NEW = new Rust_Keyword("new");
//		public Rust_Type jtype;
//		public PunctuationLeftBrace leftBrace;
//		public @OPT Rust_ArgumentList valueList;
//		public PunctuationRightBrace rightBrace;
//	}
	
//	public static @P(160) class Rust_ClassCreationWithSubscript extends PrimaryOperator
//	{
//		public Rust_Keyword NEW = new Rust_Keyword("new");
//		public Rust_Type jtype;
//		public TokenList<Rust_Subscript> subscripts;
//	}
	
//	public static @P(170) class Rust_MethodInvocation extends PrimaryOperator
//	{
//		public Rust_Variable methodName;
//		public @NOSPACE PunctuationLeftParen leftParen;
//		public @NOSPACE @OPT Rust_ArgumentList argList;
//		public @NOSPACE PunctuationRightParen rightParen;
//	}
	
	public static @P(220) class Rust_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("-", "+");
		public @S(20) Rust_Expression expr;
	}

	public static @P(230) class Rust_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Rust_Punctuation logicalNotOperator = new Rust_Punctuation('~');
		public @S(20) Rust_Expression expr;
	}
	
	public static @P(240) class Rust_NotExpression extends PrimaryOperator
	{
		public @S(10) Rust_Punctuation notOperator = new Rust_Punctuation('!');
		public @S(20) Rust_Expression expr;
	}
	
	public static @P(250) class Rust_BuiltIn extends PrimaryOperator
	{
		public @S(10) Rust_KeywordChoice builtinConstant = new Rust_KeywordChoice("false", "true", "null", "this", "super");
	}
	
//	public static @P(260) class Rust_VariableExpression extends PrimaryOperator
//	{
//		public Rust_Variable variable;
//	}
	
	public static @P(270) class Rust_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @NOSPACE Rust_Expression expression;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(280) class Rust_CommentExpression extends PrimaryOperator
	{
		public @S(10) Rust_Comment comment;
		public @S(20) Rust_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(290) class Rust_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Rust_Expression subscr;
		public @S(40) PunctuationRightBracket rightBracket;
	}

//	public static @P(300) class Rust_Subfield extends PrecedenceOperator
//	{
//		public Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
//		public @NOSPACE PunctuationPeriod dot;
//		public @OPT @NOSPACE Rust_GenericType genericType;
//		public @NOSPACE Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
//	}

	public static @P(305) class Rust_ColonColon extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
		public @S(30) @NOSPACE Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(307) class Rust_ColonColonNew extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
		public @S(30) @NOSPACE Rust_Keyword NEW = new Rust_Keyword("new");
	}

	public static @P(310) class Rust_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("*", "/", "%");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(320) class Rust_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("+", "-");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(330) class Rust_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice(">>>", "<<", ">>");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(340) class Rust_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(350) class Rust_InstanceOfExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Keyword instanceOperator = new Rust_Keyword("instanceof");
		public @S(30) Rust_Type type;
	}

	public static @P(360) class Rust_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("==", "!=");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(370) class Rust_AndExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation bitwiseAndOperator = new Rust_Punctuation('&');
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(380) class Rust_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation bitwiseXOrOperator = new Rust_Punctuation('^');
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(390) class Rust_InclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation bitwiseOrOperator = new Rust_Punctuation('|');
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Rust_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation andOperator = new Rust_Punctuation("&&");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(410) class Rust_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Rust_Punctuation orOperator = new Rust_Punctuation("||");
		public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(420) class Rust_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression var = new Rust_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Rust_PunctuationChoice equals = new Rust_PunctuationChoice(
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
		public @S(30) Rust_Expression expr;
	}
	
	public static @P(430) class Rust_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Rust_Punctuation questionMark = new Rust_Punctuation('?');
		public @S(30) Rust_Expression middle = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(40) PunctuationColon colon;
		public @S(50) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	}
}
