// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_BackQuote;
import com.eagle.programmar.Python.Terminals.Python_BinaryNumber;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.programmar.Python.Terminals.Python_OctalNumber;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Python_BinaryNumber binary;
	public @P(20) Python_OctalNumber octal;
	public @P(30) Python_HexNumber hex;
	public @P(40) Python_Number number;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Python_Expression()
	{
	    super(_operators);
	}

	public Python_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions
	
	public static @P(100) class Python_Funny_Constructor extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen1;
		public @S(20) Python_Type type;
		public @S(30) PunctuationStar star;
		public @S(40) Python_Expression size;
		public @S(50) PunctuationRightParen rightParen1;
		public @S(60) PunctuationLeftParen leftParen2;
		public @S(70) PunctuationRightParen rightParen2;
	}
	
	public static @P(110) class Python_Parens extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT @SYNTAX(Python_Multiline_Syntax.class) TokenList<Python_CommentEoln> comments;
		public @S(30) @OPT @NOSPACE @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public @S(40) @NOSPACE PunctuationRightParen rightParen;
		
		public static class Python_CommentEoln extends TokenSequence
		{
			public @S(10) Python_Comment comment;
			public @S(20) @OPT Python_EndOfLine eoln;
		}
	}
	
	public static @P(120) class Python_BracesColons extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT Python_EndOfLine eoln1;
		public @S(30) @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Dictionary dictionary;
		public @S(40) @OPT Python_EndOfLine eoln2;
		public @S(50) PunctuationRightBrace rightBrace;
		
		public static class Python_Dictionary extends TokenSequence
		{
			public @S(10) @OPT TokenList<Python_Comment> comment1;
			public @S(20) @OPT Python_DictionaryElement element;
			public @S(30) @OPT TokenList<Python_MoreDictionaryElement> nextElement;
			public @S(40) @OPT PunctuationComma comma;
			public @S(50) @OPT TokenList<Python_Comment> comment2;
			
			public static class Python_DictionaryElement extends TokenSequence
			{
				public @S(10) Python_Expression key;
				public @S(20) PunctuationColon colon;
				public @S(30) @OPT Python_EndOfLine eoln;
				public @S(40) @OPT Python_Comment comment;
				public @S(50) Python_Expression value;
			}
			
			public static class Python_MoreDictionaryElement extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) @OPT TokenList<Python_Comment> comment;
				public @S(30) Python_DictionaryElement element;
			}
		}
	}

	public static @P(130) class Python_BracesNoColons extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT Python_EndOfLine eoln1;
		public @S(30) @SYNTAX(Python_Multiline_Syntax.class) Python_Set set;
		public @S(40) @OPT Python_EndOfLine eoln2;
		public @S(50) PunctuationRightBrace rightBrace;
		
		public static class Python_Set extends TokenSequence
		{
			public @S(10) @OPT TokenList<Python_Comment> comment1;
			public @S(20) Python_Expression element;
			public @S(30) @OPT TokenList<Python_MoreSetElement> nextElement;
			public @S(40) @OPT PunctuationComma comma;
			public @S(50) @OPT TokenList<Python_Comment> comment2;
			
			public static class Python_MoreSetElement extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) @OPT TokenList<Python_Comment> comment;
				public @S(30) Python_Expression element;
			}
		}
	}
	
	public static @P(140) class Python_Brackets extends PrimaryOperator
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT TokenList<Python_Comment> comment;
		public @S(30) @OPT Python_EndOfLine eoln;
		public @S(40) @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
		public @S(50) PunctuationRightBracket rightBracket;
	}
	
	public static @P(150) class Python_UnarySign extends PrimaryOperator
	{
		public @S(10) Python_PunctuationChoice sign = new Python_PunctuationChoice("*", "-", "+", "~");
		public @S(20) Python_Expression expr;
	}

	public static @P(160) class Python_Not_Expression extends PrimaryOperator 
	{
		public @S(10) Python_Keyword NOT = new Python_Keyword("not");
		public @S(20) Python_Expression expr;
	}
	
	public static @P(170) class Python_Literals extends PrimaryOperator
	{
		public @S(10) TokenList<Python_Literal> literals;
	}
	
	public static @P(180) class Python_BackQuotes extends PrimaryOperator
	{
		// These are obsolete as of Python 3.
		public @S(10) @CURIOUS("Obsolete backquotes") TokenList<Python_BackQuote> quotes;
	}
	
	public static @P(190) class Python_Function_Call extends PrimaryOperator
	{
		public @S(10) Python_Variable name;
		public @S(20) @NOSPACE TokenList<Python_Parameter_List> args;
	}

	public static @P(200) class Python_BuiltIn extends PrimaryOperator
	{
		public @S(10) Python_KeywordChoice builtins = new Python_KeywordChoice("None", "False", "True");
	}
	
	public static @P(210) class Python_VariableExpression extends PrimaryOperator
	{
		public @S(10) Python_Variable variable;
	}
	
	public static @P(220) class Python_Star_Expression extends PrimaryOperator
	{
		public @S(10) PunctuationStar star;
		public @S(20) Python_Expression expr;
	}

	public static @P(230) class Python_StarStar_Expression extends PrimaryOperator
	{
		public @S(10) Python_Punctuation starStar = new Python_Punctuation("**");
		public @S(20) Python_Expression expr;
	}
	
	public static @P(240) class Python_Lambda_Expression extends PrimaryOperator 
	{
		public @S(10) Python_Keyword LAMBDA = new Python_Keyword("lambda");
		public @S(20) @OPT PunctuationLeftParen leftParen;
		public @S(30) @OPT Python_Variable_List parameters;
		public @S(40) @OPT PunctuationRightParen rightParen;
		public @S(50) PunctuationColon colon;
		public @S(60) Python_Expression expr;
		
		public static class Python_Variable_List extends TokenSequence
		{
			public @S(10) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
			public @S(20) Python_Variable var;
			public @S(30) @OPT Python_Variable_Default defaultValue;
			public @S(40) @OPT TokenList<Python_MoreVariablesInList> moreVars;

			public static class Python_MoreVariablesInList extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) @OPT TokenList<Python_Comment> comments;
				public @S(30) @OPT Python_PunctuationChoice star = new Python_PunctuationChoice("*", "**");
				public @S(40) Python_Variable var;
				public @S(50) @OPT Python_Variable_Default defaultValue;
			}
			
			public static class Python_Variable_Default extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) Python_Expression defaultValue;
			}
		}
		
	}
	
	public static @P(250) class Python_Yield extends PrimaryOperator
	{
		public @S(10) Python_Keyword YIELD = new Python_Keyword("yield");
		public @S(20) Python_Expression expr;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions
	
	public static @P(500) class Python_SubscriptExpression extends PrecedenceOperator
	{
		public @S(10) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) PunctuationLeftBracket leftBracket;
		public @S(30) @OPT Python_EndOfLine eoln;
		public @S(40) @SYNTAX(Python_Multiline_Syntax.class) Python_SubscrExpr subscr;
		public @S(50) PunctuationRightBracket rightBracket;
		public @S(60) @OPT Python_Parameter_List moreArguments;
		
		public static class Python_SubscrExpr extends TokenSequence
		{
			public @S(10) @OPT Python_Expression subscr;
			public @S(20) @OPT Python_ColonSubscript subscriptStop;
			public @S(30) @OPT Python_ColonSubscript subscriptStep;
		}

		public static class Python_ColonSubscript extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) @OPT Python_EndOfLine eoln;
			public @S(30) @OPT Python_Expression expr;
		}
	}
	
	public static @P(510) class Python_Subfield extends PrecedenceOperator
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @NOSPACE PunctuationPeriod dot;
		public @S(30) @NOSPACE Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(520) class Python_Power_Expression extends PrecedenceOperator
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Punctuation stars = new Python_Punctuation("**");
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(530) class Python_Multiplicative_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("//", "*", "/", "%");
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(540) class Python_Additive_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(550) class Python_Shift_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("<<", ">>");
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(560) class Python_Bitwise_And_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Punctuation and = new Python_Punctuation('&');
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(570) class Python_Bitwise_Xor_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Punctuation xor = new Python_Punctuation('^');
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(580) class Python_Bitwise_Or_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Punctuation or = new Python_Punctuation('|');
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(590) class Python_Relational_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Relational_Operator relOp;
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

		public static class Python_Relational_Operator extends TokenChooser
		{
			public @CHOICE Python_PunctuationChoice operator = new Python_PunctuationChoice(
					"==", "!=", "<>", "<=", ">=", "<", ">");
			
			public @CHOICE static class Python_IN_Operator extends TokenSequence
			{
				public @S(10) @OPT Python_Keyword NOT = new Python_Keyword("not");
				public @S(20) Python_Keyword IN = new Python_Keyword("in");
			}
			
			public @CHOICE static class Python_IS_Operator extends TokenSequence
			{
				public @S(10) Python_Keyword IS = new Python_Keyword("is");
				public @S(20) @OPT Python_Keyword NOT = new Python_Keyword("not");
			}
		}
	}

	public static @P(600) class Python_And_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Keyword AND = new Python_Keyword("and");
		public @S(30) @OPT TokenList<Python_Comment> comment;
		public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(610) class Python_Or_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Keyword OR = new Python_Keyword("or");
		public @S(30) @OPT TokenList<Python_Comment> comment;
		public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(620) class Python_For_In_Expression extends PrecedenceOperator
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Keyword FOR = new Python_Keyword("for");
		public @S(30) Python_VariableList varList;
		public @S(40) Python_Keyword IN = new Python_Keyword("in");
		public @S(50) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(630) class Python_If_Else_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Keyword IF = new Python_Keyword("if");
		public @S(30) Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
		public @S(40) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(50) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(640) class Python_If_Expression extends PrecedenceOperator 
	{
		public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_Keyword IF = new Python_Keyword("if");
		public @S(30) Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(650) class Python_Assignment_Expression extends PrecedenceOperator
	{
		public @S(10) Python_Expression leftVar = new Python_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Python_PunctuationChoice equals = new Python_PunctuationChoice("=", "+=", "-=", ":=");
		public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
}
