// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameters;
import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) CSharp_HexNumber hex;
	public @P(20) CSharp_Number number;
	public @P(30) CSharp_Literal literal;
	public @P(40) CSharp_Character_Literal characters;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public CSharp_Expression()
	{
	    super(_operators);
	}

	public CSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class CSharp_DotClass extends PrimaryOperator
	{
		public @S(10) CSharp_Type jtype;
		public @S(20) PunctuationPeriod dot;
		public @S(30) CSharp_Keyword CLASS = new CSharp_Keyword("class");
	}
	
	public static @P(110) class CSharp_CastExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) CSharp_Type jtype;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) CSharp_Expression expr;
	}

	public static @P(120) class CSharp_ExpressionList extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT @NOSPACE TokenList<CSharp_Comment> comment;
		public @S(30) CSharp_ArgumentList valueList;
		public @S(40) @NOSPACE PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class CSharp_InterfaceCreationWithMethod extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
		public @S(20) CSharp_KeywordChoice jinterface = new CSharp_KeywordChoice(
				"Runnable", "ActionListener", "WindowAdapter");
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) @NOSPACE CSharp_Method method;
		public @S(70) @NOSPACE PunctuationRightBrace rightBrace;
	}

	public static @P(140) class CSharp_ClassCreationExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
		public @S(20) CSharp_Type jtype;
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @OPT TokenList<CSharp_Comment> comments;
		public @S(50) @OPT @NOSPACE CSharp_ArgumentList argList;
		public @S(60) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(150) class CSharp_ClassCreationWithInitializers extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
		public @S(20) CSharp_Type jtype;
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) @OPT @NOSPACE CSharp_ArgumentList valueList;
		public @S(50) @NOSPACE PunctuationRightBrace rightBrace;
	}
	
	public static @P(160) class CSharp_ClassCreationWithSubscript extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
		public @S(20) @OPT CSharp_Type jtype;
		public @S(30) TokenList<CSharp_Subscript> subscripts;
		public @S(40) @OPT CSharp_ExpressionList values;
	}

	public static @P(170) class CSharp_MethodInvocation extends PrimaryOperator
	{
		public @S(10) CSharp_Variable methodName;
		public @S(20) @OPT CSharp_GenericType generic;
		public @S(30) @NOSPACE PunctuationLeftParen leftParen;
		public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
		public @S(50) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(180) class CSharp_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Punctuation preIncrementOperator = new CSharp_Punctuation("++");
		public @S(20) @NOSPACE CSharp_Variable var;
	}

	public static @P(190) class CSharp_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Punctuation preDecrementOperator = new CSharp_Punctuation("--");
		public @S(20) @NOSPACE CSharp_Variable var;
	}
	
	public static @P(200) class CSharp_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Variable var;
		public @S(20) @NOSPACE CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");
	}

	public static @P(210) class CSharp_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Variable var;
		public @S(20) @NOSPACE CSharp_Punctuation postDecrementOperator = new CSharp_Punctuation("--");
	}
	
	public static @P(220) class CSharp_NegativeExpression extends PrimaryOperator
	{
		public @S(10) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
		public @S(20) CSharp_Expression expr;
	}

	public static @P(230) class CSharp_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Punctuation logicalNotOperator = new CSharp_Punctuation('~');
		public @S(20) CSharp_Expression expr;
	}
	
	public static @P(240) class CSharp_NotExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Punctuation notOperator = new CSharp_Punctuation('!');
		public @S(20) CSharp_Expression expr;
	}
	
	public static @P(250) class CSharp_BuiltIn extends PrimaryOperator
	{
		public @S(10) CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice(
				"default", "false", "true", "null", "this", "super");
	}
	
	public static @P(260) class CSharp_VariableExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Variable variable;
	}
	
	public static @P(270) class CSharp_TypeExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Type type;
	}
	
	public static @P(280) class CSharp_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @NOSPACE CSharp_Expression expression;
		public @S(30) @NOSPACE PunctuationRightParen rightParen;
	}
	
	public static @P(290) class CSharp_CommentExpression extends PrimaryOperator
	{
		public @S(10) CSharp_Comment comment;
		public @S(20) CSharp_Expression expr;
	}
	
	public static @P(300) class CSharp_TypeOf extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword TYPEOF = new CSharp_Keyword("typeof");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSharp_Type type;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(310) class CSharp_Delegation extends PrimaryOperator
	{
		public @S(10) CSharp_Keyword DELEGATE = new CSharp_Keyword("delegate");
		public @S(20) CSharp_MethodParameters parameters;
		public @S(30) @NEWLINE CSharp_MethodBody body;
	}

	public static @P(320) class CSharp_LambdaBlock extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<CSharp_Variable, PunctuationComma> vars;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public @S(50) CSharp_StatementBlock block;
	}

	public static @P(330) class CSharp_LambdaParameters extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<CSharp_Variable, PunctuationComma> vars;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public @S(50) CSharp_Expression expr;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class CSharp_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE PunctuationLeftBracket leftBracket;
		public @S(30) @NOSPACE CSharp_Expression subscr = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	public static @P(510) class CSharp_NamespaceExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		public @S(30) @NOSPACE CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class CSharp_SubfieldExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(15) @OPT CSharp_Punctuation question = new CSharp_Punctuation('?');
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(530) class CSharp_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class CSharp_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("+", "-");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(550) class CSharp_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<<", ">>", ">>>");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class CSharp_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(570) class CSharp_InstanceOfExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression expr = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Keyword instanceOperator = new CSharp_Keyword("is");
		public @S(30) CSharp_Type type;
	}

	public static @P(580) class CSharp_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_EqualityOperator equalityOperator;
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

		public static class CSharp_EqualityOperator extends TokenChooser
		{
			public @CHOICE CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "??");
			public @CHOICE CSharp_KeywordChoice asIs = new CSharp_KeywordChoice("as", "is");
		}
	}

	public static @P(590) class CSharp_AndExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Punctuation bitwiseAndOperator = new CSharp_Punctuation('&');
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(600) class CSharp_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Punctuation bitwiseXOrOperator = new CSharp_Punctuation('^');
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(610) class CSharp_InclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Punctuation bitwiseOrOperator = new CSharp_Punctuation('|');
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(620) class CSharp_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Punctuation andOperator = new CSharp_Punctuation("&&");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(630) class CSharp_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) CSharp_Punctuation orOperator = new CSharp_Punctuation("||");
		public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(640) class CSharp_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) CSharp_Punctuation questionMark = new CSharp_Punctuation('?');
		public @S(30) CSharp_Expression middle = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(40) PunctuationColon colon;
		public @S(50) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(650) class CSharp_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public @S(30) CSharp_Expression expr;
	}

	public static @P(660) class CSharp_LambdaFunction extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public @S(30) CSharp_StatementBlock block;
	}

	public static @P(670) class CSharp_LambdaExpression extends PrecedenceOperator
	{
		public @S(10) CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
		public @S(30) CSharp_Expression expr;
	}
}
