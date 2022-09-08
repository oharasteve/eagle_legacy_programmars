// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class C_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();
	
	public @P(10) C_HexNumber hex;
	public @P(20) C_Number number;
	public @P(30) C_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public C_Expression()
	{
	    super(_operators);
	}

	public C_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class C_Literals extends PrimaryOperator
	{
		public @S(10) TokenList<C_Literal> literals;
	}
	
	public static @P(110) class C_CastExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) C_Type ctype;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) C_Expression expr;
	}

	public static @P(120) class C_ExpressionList extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT C_ArgumentList valueList;
		public @S(30) @OPT C_Comment comment;
		public @S(40) PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class C_FunctionCall extends PrimaryOperator
	{
		public @S(10) @OPT CPlus_NamespaceList namespace;
		public @S(20) C_Variable functionName;
		public @S(30) @OPT C_Generic generic;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) @OPT C_ArgumentList argList;
		public @S(60) PunctuationRightParen rightParen;
	}
				
	public static @P(140) class C_FunctionName extends PrimaryOperator
	{
		public @S(10) C_Variable functionName;
		public @S(20) C_Generic generic;
	}

	public static @P(150) class C_FunctionPointerCall extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen1;
		public @S(20) @OPT PunctuationStar star2;
		public @S(30) C_Variable methodName;
		public @S(40) PunctuationRightParen rightParen1;
		public @S(50) PunctuationLeftParen leftParen2;
		public @S(60) @OPT C_ArgumentList argList;
		public @S(70) PunctuationRightParen rightParen2;
	}

	public static @P(160) class C_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) C_Punctuation preIncrementOperator = new C_Punctuation("++");
		public @S(20) C_Expression expr;
	}

	public static @P(170) class C_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) C_Punctuation preDecrementOperator = new C_Punctuation("--");
		public @S(20) C_Expression expr;
	}
	
	public static @P(180) class C_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) C_Parenthesized_Expression expr;
		public @S(20) C_Punctuation postIncrementOperator = new C_Punctuation("++");
	}

	public static @P(190) class C_PostIncrementVariable extends PrimaryOperator
	{
		public @S(10) C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public @S(20) C_Punctuation postIncrementOperator = new C_Punctuation("++");
	}

	public static @P(200) class C_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) C_Parenthesized_Expression expr;
		public @S(20) C_Punctuation postDecrementOperator = new C_Punctuation("--");
	}

	public static @P(210) class C_PostDecrementVariable extends PrimaryOperator
	{
		public @S(10) C_Variable var;		// Cannot be just C_Expression -- infinite loop
		public @S(20) C_Punctuation postDecrementOperator = new C_Punctuation("--");
	}

	public static @P(220) class C_SignedExpression extends PrimaryOperator
	{
		public @S(10) C_PunctuationChoice signedOperator = new C_PunctuationChoice("+", "-");
		public @S(20) C_Expression expr;
	}

	public static @P(230) class C_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) C_Punctuation logicalNotOperator = new C_Punctuation('~');
		public @S(20) C_Expression expr;
	}
		
	public static @P(240) class C_NotExpression extends PrimaryOperator
	{
		public @S(10) C_Punctuation notOperator = new C_Punctuation('!');
		public @S(20) C_Expression expr;
	}
	
	public static @P(250) class C_BuiltIn extends PrimaryOperator
	{
		public @S(10) C_KeywordChoice logicalConstant = new C_KeywordChoice("false", "true", "NULL", "default");
	}
	
	public static @P(260) class C_VariableExpression extends PrimaryOperator
	{
		public @S(10) C_Variable variable;
	}
	
	public static @P(270) class C_AddressOfVariable extends PrimaryOperator
	{
		public @S(10) C_Punctuation ampersand = new C_Punctuation('&');
		public @S(20) C_Expression expr;
	}
	
	public static @P(280) class C_SizeOfType extends PrimaryOperator
	{
		public @S(10) C_Keyword SIZEOF = new C_Keyword("sizeof");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) C_Type ctype;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(290) class C_SizeOfExpr extends PrimaryOperator
	{
		public @S(10) C_Keyword SIZEOF = new C_Keyword("sizeof");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) C_Expression expr;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(300) class C_Parenthesized_Expression extends PrimaryOperator
	{
		public @S(10) C_ParenthesizedExpression expr;
	}

	public static @P(310) class C_StarExpression extends PrimaryOperator
	{
		public @S(10) PunctuationStar star;
		public @S(20) C_Expression expr;
	}

	public static @P(320) class C_CommentExpression extends PrimaryOperator
	{
		public @S(10) C_Comment comment;
		public @S(20) C_Expression expr;
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class C_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) C_Expression subscr = new C_Expression(this, AllowedPrecedence.ANY);
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(510) class C_DotSubfield extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) C_Expression rightSub = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class C_ArrowSubfield extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation arrow = new C_Punctuation("->");
		public @S(30) C_Expression rightArrow = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class C_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("*", "/", "%");
		public @S(30) C_Expression rightMult = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class C_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("+", "-");
		public @S(30) C_Expression rightAdd = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(550) class C_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("<<", ">>", ">>>");
		public @S(30) C_Expression rightShift = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(560) class C_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) C_Expression rightRel = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(570) class C_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("==", "!=");
		public @S(30) C_Expression rightEq = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(580) class C_BitwiseAndExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation bitwiseAndOperator = new C_Punctuation('&');
		public @S(30) C_Expression rightBitAnd = new C_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(590) class C_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation bitwiseXOrOperator = new C_Punctuation('^');
		public @S(30) C_Expression rightXor = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(600) class C_BitwiseOrExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation bitwiseOrOperator = new C_Punctuation('|');
		public @S(30) C_Expression rightBitOr = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(610) class C_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation andOperator = new C_Punctuation("&&");
		public @S(30) C_Expression rightAnd = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(620) class C_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) C_Punctuation orOperator = new C_Punctuation("||");
		public @S(30) C_Expression rightOr = new C_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(630) class C_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) C_Punctuation questionMark = new C_Punctuation('?');
		public @S(30) C_Expression middle = new C_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(40) PunctuationColon colon;
		public @S(50) C_Expression rightTF = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(640) class C_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) C_PunctuationChoice equals = new C_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public @S(30) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro;	// What the ...
		public @S(40) C_Expression rightAsg = new C_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	//
	// Not easy to have CPlus_Expression extend C_Expression.
	// Have to use <generics> to make it work. C_Expression and CPlus_Expression
	// would both have to derive off a generic base class which we are avoiding.
	//

	// NOTE: C++ adds the 'new' operator here. See CPlus_Expression.java and the constructor in CPlus_Program.java
}
