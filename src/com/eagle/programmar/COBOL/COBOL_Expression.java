// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_Expression.COBOL_RelationCondition.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceChooser.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) COBOL_Literal literal;
	public @P(20) COBOL_Number number;
	public @P(30) COBOL_HexNumber hex;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public COBOL_Expression()
	{
	    super(_operators);
	}

	public COBOL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}

	///////////////////////////////////////////////
	// Primary expressions
	
	public static @P(100) class COBOOL_LengthExpression extends PrimaryOperator
	{
		public @S(10) COBOL_Keyword LENGTH = new COBOL_Keyword("LENGTH");
		public @S(20) @OPT COBOL_Keyword OF = new COBOL_Keyword("OF");
		public @S(30) COBOL_Expression expression;
	}
	
	public static @P(110) class COBOOL_AddressExpression extends PrimaryOperator
	{
		public @S(10) COBOL_Keyword ADDRESS = new COBOL_Keyword("ADDRESS");
		public @S(20) @OPT COBOL_Keyword OF = new COBOL_Keyword("OF");
		public @S(30) COBOL_Variable variable;
	}
	
	public static @P(120) class COBOOL_LinageCounterExpression extends PrimaryOperator
	{
		public @S(10) COBOL_Keyword LINAGECOUNTER = new COBOL_Keyword("LINAGE-COUNTER");
		public @S(20) COBOL_Keyword OF = new COBOL_Keyword("OF");
		public @S(30) COBOL_Expression expression;
	}
	
	public static @P(130) class COBOL_BuiltIn extends PrimaryOperator
	{
		public @S(10) COBOL_KeywordChoice logicalConstant = new COBOL_KeywordChoice(
				"ANY",
				"FALSE",
				"HIGH-VALUES",
				"LINAGE-COUNTER",
				"LOW-VALUES",
				"QUOTE",
				"RETURN-CODE",
				"SPACE",
				"SPACES",
				"TRUE",
				"ZERO",
				"ZEROES",
				"ZEROS"
		);
	}
	
	public static @P(140) class COBOL_ParenthesizedExpression extends PrimaryOperator
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) COBOL_Expression expression;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static @P(150) class COBOL_ExpressionFunction extends PrimaryOperator
	{
		public @S(10) COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
		public @S(20) COBOL_FunctionName func;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) SeparatedList<COBOL_FunctionParameter,PunctuationComma> parameters;
		public @S(50) PunctuationRightParen rightParen;
		
		public static class COBOL_FunctionName extends TokenChooser
		{
			public @FIRST COBOL_KeywordChoice builtins = new COBOL_KeywordChoice(
					"CURRENT-DATE",
					"INTEGER-OF-DATE",
					"LENGTH",
					"REM",
					"REVERSE"
			);
			
			public @CHOICE COBOL_Variable userFunc;
		}
		
		public static class COBOL_FunctionParameter extends TokenSequence
		{
			public @S(10) COBOL_Expression parameter;
			public @S(20) @OPT COBOL_ExpressionFunctionRange range;
			public @S(30) @OPT COBOL_KeywordChoice LEADING = new COBOL_KeywordChoice("LEADING", "TRAILING");
			
			public static class COBOL_ExpressionFunctionRange extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) COBOL_Expression parameter;
			}
		}
	}

	public static @P(160) class COBOL_ClassCondition extends PrimaryOperator
	{
		public @S(10) COBOL_Variable var;
		public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(40) COBOL_KeywordChoice type = new COBOL_KeywordChoice(
				"POSITIVE", "NEGATIVE", "ZERO", "NUMERIC", 
				"ALPHABETIC", "ALPHABETIC-LOWER", "ALPHABETIC-UPPER");
	}
	
	public static @P(170) class COBOL_NotCondition extends PrimaryOperator
	{
		public @S(10) COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Expression cond;
	}
	
	public static @P(180) class COBOL_VariableExpression extends PrimaryOperator
	{
		public @S(10) COBOL_VariableRef variable;

		public static class COBOL_VariableRef extends TokenSequence
		{
			public @S(10) COBOL_Identifier_Reference id;
			public @S(20) @OPT TokenList<COBOL_Subscript> subscript;
			public @S(30) @OPT TokenList<COBOL_OfVariableRef> ofList;

			public static class COBOL_OfVariableRef extends TokenSequence
			{
				public @S(10) COBOL_Keyword OF = new COBOL_Keyword("OF");
				public @S(20) COBOL_Identifier_Reference id;
			}
		}
	}

	///////////////////////////////////////////////
	// Binary expressions
	
	public static @P(500) class COBOL_ThroughExpression extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_Keyword THRU = new COBOL_Keyword("THRU");
		public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(510) class COBOL_ConcatenateExpression extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_Punctuation ampersand = new COBOL_Punctuation('&');
		public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(520) class COBOL_MultiplicativeExpression extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_PunctuationChoice timesDivide = new COBOL_PunctuationChoice("*", "/");
		public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
	
	public static @P(530) class COBOL_AdditiveExpression extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_PunctuationChoice plusMinus = new COBOL_PunctuationChoice("+", "-");
		public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(540) class COBOL_RelationCondition extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(40) COBOL_RelationalOperator relationalOperator;
		public @S(50) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

		public static class COBOL_RelationalOperator extends TokenChooser
		{
			public @CHOICE COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("<=", "<", "=", ">=", ">");
			
			public @CHOICE static class COBOL_Greater extends TokenSequence
			{
				public @S(10) COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
				public @S(20) @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @S(30) @OPT COBOL_OrEqual orEqual;
			}
			
			public @CHOICE static class COBOL_Equal extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice EQUAL = new COBOL_KeywordChoice("EQUAL", "EQUALS");
				public @S(20) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
			
			public @CHOICE static class COBOL_Less extends TokenSequence
			{
				public @S(10) COBOL_Keyword LESS = new COBOL_Keyword("LESS");
				public @S(20) @OPT COBOL_Keyword THAN = new COBOL_Keyword("THAN");
				public @S(30) @OPT COBOL_OrEqual orEqual;
			}

			public @CHOICE static class COBOL_OrEqual extends TokenSequence
			{
				public @S(10) COBOL_Keyword OR = new COBOL_Keyword("OR");
				public @S(20) COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
				public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
		}
	}

	public static @P(550) class COBOL_AndCondition extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_Keyword AND = new COBOL_Keyword("AND");
		public @S(30) @OPT COBOL_RelationalOperator relationalOperator;
		public @S(40) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}

	public static @P(560) class COBOL_OrCondition extends PrecedenceOperator
	{
		public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) COBOL_Keyword OR = new COBOL_Keyword("OR");
		public @S(30) @OPT COBOL_RelationalOperator relationalOperator;
		public @S(40) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);
	}
}
