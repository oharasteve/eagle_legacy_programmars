// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Function.Javascript_FunctionImplementation;
import com.eagle.programmar.Javascript.Javascript_Function.Javascript_FunctionImplementation.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Javascript_HexNumber hex;
	public @P(20) Javascript_Number number;
	public @P(30) Javascript_Literal literal;
	public @P(40) Javascript_RegularExpression regEx;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Javascript_Expression()
	{
	    super(_operators);
	}

	public Javascript_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class Javascript_DotClass extends PrimaryOperator
	{
		public @S(10) Javascript_Type jtype;
		public @S(20) PunctuationPeriod dot;
		public @S(30) Javascript_Keyword CLASS = new Javascript_Keyword("class");
	}
	
	// See Javascript_LambdaFunction which also has @FIRST
	// Problem is (int)x and (num)/x. Ambiguous for the parser. It needs a real backtracker.
	public static @FIRST @P(110) class Javascript_CastExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Javascript_Type jtype;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) Javascript_Expression expr;
	}
	
	public static @P(120) class Javascript_ExpressionList extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<Javascript_Comment> comment;
		public @S(30) @OPT Javascript_ArgumentList valueList;
		public @S(40) PunctuationRightBrace rightBrace;
	}
	
	public static @P(130) class Javascript_StringFunction extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword STRING = new Javascript_Keyword("String");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Javascript_Expression expr;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static @P(140) class Javascript_DeleteExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword DELETE = new Javascript_Keyword("delete");
		public @S(20) Javascript_Expression expr;
	}

	public static @P(150) class Javascript_ClassCreationExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
		public @S(20) Javascript_Type jtype;
		public @S(30) Javascript_ParenthesizedExpression arguments;
	}
	
	public static @P(160) class Javascript_ClassCreationWithInitializers extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
		public @S(20) Javascript_Type jtype;
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) Javascript_ArgumentList valueList;
		public @S(50) PunctuationRightBrace rightBrace;
	}
	
	public static @P(170) class Javascript_ClassCreationWithSubscript extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
		public @S(20) Javascript_Type jtype;
		public @S(30) TokenList<Javascript_Subscript> subscripts;
	}

	public static @P(180) class Javascript_NewNoArgsExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
		public @S(20) Javascript_Type jtype;
	}
	
	public static @P(190) class Javascript_MethodInvocation extends PrimaryOperator
	{
		public @S(10) Javascript_Variable methodName;
		public @S(20) Javascript_ParenthesizedExpression arguments;
	}
	
	public static @P(200) class Javascript_PreIncrementExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Punctuation preIncrementOperator = new Javascript_Punctuation("++");
		public @S(20) Javascript_Variable var;
	}

	public static @P(210) class Javascript_PreDecrementExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Punctuation preDecrementOperator = new Javascript_Punctuation("--");
		public @S(20) Javascript_Variable var;
	}
	
	public static @P(220) class Javascript_PostIncrementExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Variable var;
		public @S(20) Javascript_Punctuation postIncrementOperator = new Javascript_Punctuation("++");
	}

	public static @P(230) class Javascript_PostDecrementExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Variable var;
		public @S(20) Javascript_Punctuation postDecrementOperator = new Javascript_Punctuation("--");
	}
	
	public static @P(240) class Javascript_NegativeExpression extends PrimaryOperator
	{
		public @S(10) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("-", "+");
		public @S(20) Javascript_Expression expr;
	}

	public static @P(250) class Javascript_LogicalNotExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Punctuation logicalNotOperator = new Javascript_Punctuation('~');
		public @S(20) Javascript_Expression expr;
	}
	
	public static @P(260) class Javascript_NotExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Punctuation notOperator = new Javascript_Punctuation('!');
		public @S(20) Javascript_Expression expr;
	}
	
	public static @P(270) class Javascript_BuiltIn extends PrimaryOperator
	{
		public @S(10) Javascript_KeywordChoice logicalConstant = new Javascript_KeywordChoice(
				"false",
				"null",
				"String",
				"super",
				"this",
				"true"
			);
	}
	
	public static @P(280) class Javascript_VariableExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Variable variable;
	}
	
	public static @P(290) class Javascript_StrangeFunction extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Javascript_Number zero;
		public @S(30) PunctuationComma comma;
		public @S(40) Javascript_Variable function;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) Javascript_ParenthesizedExpression arguments;
	}

	public static @P(300) class Javascript_ParenthesizedFunction extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Javascript_Function function;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) Javascript_ParenthesizedExpression arguments;
	}

 	// Problem is (int)x and (num)/x. Ambiguous for the parser. It needs a real backtracker.
	public static @P(310) class Javascript_Parenthesized_Expression extends PrimaryOperator
	{
		public @S(10) Javascript_ParenthesizedExpression expr;
	}
	
	public static @P(320) class Javascript_SimpleArray extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT Javascript_Expression expr;
		public @S(30) @OPT TokenList<Javascript_MoreArray> more;
		public @S(40) PunctuationRightBracket rightBracket;
		
		public static class Javascript_MoreArray extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT Javascript_Expression expr;
		}
	}
	
	public static @P(330) class Javascript_CommentExpression extends PrimaryOperator
	{
		public @S(10) Javascript_Comment comment;
		public @S(20) Javascript_Expression expr;
	}
	
	public static @P(340) class Javascript_Dictionary extends PrimaryOperator
	{
		// Don't use @INDENT here. Messes up 'return' statements that return a dictionary.
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) SeparatedList<Javascript_DictionaryItem,PunctuationComma> items;
		public @S(30) @OPT PunctuationComma comma;
		public @S(40) @OPT TokenList<Javascript_Comment> comments;
		public @S(50) PunctuationRightBrace rightBrace;
		
		public static class Javascript_DictionaryItem extends TokenChooser
		{
			public @CHOICE static class Javascript_DictionaryFunction extends TokenSequence
			{
				public @S(10) @OPT TokenList<Javascript_Comment> comments;
				public @S(20) @OPT Javascript_KeywordChoice prefix = new Javascript_KeywordChoice("get", "static");
				public @S(30) Javascript_FunctionImplementation function;
			}
			
			public @CHOICE static class Javascript_DictionaryData extends TokenSequence
			{
				public @S(10) Javascript_Expression field;
				public @S(20) PunctuationColon colon;
				public @S(30) Javascript_Expression expr;
			}
		}
	}

	public static @P(350) class Javascript_FunctionExpr extends PrimaryOperator
	{
		public @S(10) Javascript_Function function;
		public @S(20) @OPT Javascript_ParenthesizedExpression args;
	}
	
	public static @P(360) class Javascript_TypeOfExpr extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword TYPEOF = new Javascript_Keyword("typeof");
		public @S(20) Javascript_Expression expr;
	}
	
	public static @P(370) class Javascript_VoidExpr extends PrimaryOperator
	{
		public @S(10) Javascript_Keyword VOID = new Javascript_Keyword("void");
		public @S(20) Javascript_Number number;
	}
	
	///////////////////////////////////////////////
	// Binary expressions

	public static @P(380) class Javascript_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) Javascript_Expression subscr = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) PunctuationRightBracket rightBracket;
	}

	public static @P(390) class Javascript_Subfield extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationPeriod dot;
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(400) class Javascript_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("*", "/", "%");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(410) class Javascript_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("+", "-");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(420) class Javascript_ShiftExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(">>>", "<<", ">>");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(430) class Javascript_RelationalExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("<", ">", "<=", ">=");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(440) class Javascript_InstanceOfExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Keyword instanceOperator = new Javascript_Keyword("instanceof");
		public @S(30) Javascript_Type type;
	}

	public static @P(450) class Javascript_InExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Keyword inOperator = new Javascript_Keyword("in");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(460) class Javascript_EqualityExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("!==", "===", "==", "!=");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) @OPT Javascript_Comment comment;
	}

	public static @P(470) class Javascript_AndExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Punctuation bitwiseAndOperator = new Javascript_Punctuation('&');
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(480) class Javascript_ExclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Punctuation bitwiseXOrOperator = new Javascript_Punctuation('^');
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(490) class Javascript_InclusiveOrExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Punctuation bitwiseOrOperator = new Javascript_Punctuation('|');
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(500) class Javascript_ConditionalAndExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Punctuation andOperator = new Javascript_Punctuation("&&");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(510) class Javascript_ConditionalOrExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Javascript_Punctuation orOperator = new Javascript_Punctuation("||");
		public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(520) class Javascript_TrueFalseExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Javascript_Punctuation questionMark = new Javascript_Punctuation('?');
		public @S(30) @OPT TokenList<Javascript_Comment> comments1;
		public @S(40) Javascript_Expression middle = new Javascript_Expression(this, AllowedPrecedence.ANY);
		public @S(50) PunctuationColon colon;
		public @S(60) @OPT TokenList<Javascript_Comment> comments2;
		public @S(70) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(530) class Javascript_AssignmentExpression extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public @S(30) Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	}
	
	public static @P(540) class Javascript_LambdaFunction extends PrecedenceOperator
	{
		public @S(10) Javascript_Expression arguments;
		public @S(20) Javascript_Punctuation arrow = new Javascript_Punctuation("=>");
		public @S(30) Javascript_FunctionBody code;
	}
}
