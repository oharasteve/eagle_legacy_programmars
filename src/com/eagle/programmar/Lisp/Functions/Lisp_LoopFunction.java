// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_LoopFunction extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("m_loop.htm") Lisp_Keyword LOOP = new Lisp_Keyword("loop");
	public @S(30) Lisp_LoopType loopType;
	public @S(40) PunctuationRightParen rightParen;
	
	public static class Lisp_LoopType extends TokenChooser
	{
		public @CHOICE Lisp_SExpr simpleExpr;
		
		public @CHOICE static class Lisp_LoopFancy extends TokenSequence
		{
			public @S(10) @OPT Lisp_LoopNamed named;
			public @S(20) @OPT TokenList<Lisp_LoopVariableClause> variableClauses;
			public @S(30) TokenList<Lisp_LoopMainClause> mainClauses;
			
			public static class Lisp_LoopNamed extends TokenSequence
			{
				public @S(10) Lisp_Keyword NAMED = new Lisp_Keyword("named");
				public @S(20) Lisp_Variable_Definition name;
			}
			
			public static class Lisp_LoopVariableClause extends TokenChooser
			{
				public @CHOICE Lisp_LoopInitialFinal initialFinal;
		
				public @CHOICE static class Lisp_LoopWith extends TokenSequence
				{
					public @S(10) Lisp_LoopWithElement element;
					public @S(20) @OPT TokenList<Lisp_LoopMoreWith> more;
					
					public static class Lisp_LoopMoreWith extends TokenSequence
					{
						public @S(10) Lisp_Keyword AND = new Lisp_Keyword("and");
						public @S(20) Lisp_LoopWithElement element;
					}
					
					public static class Lisp_LoopWithElement extends TokenSequence
					{
						public @S(10) Lisp_Keyword WITH = new Lisp_Keyword("with");
						public @S(20) @OPT Lisp_SExpr typeSpec;
						public @S(30) @OPT Lisp_LoopWithValue equalsValue;
						
						public static class Lisp_LoopWithValue extends TokenSequence
						{
							public @S(10) PunctuationEquals equals;
							public @S(20) Lisp_SExpr value;
						}
					}
				}
				
				public @CHOICE static class Lisp_LoopForAsClause extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice FOR = new Lisp_KeywordChoice("for", "as");
					public @S(20) Lisp_Variable var;
					public @S(30) TokenList<Lisp_LoopForClause> forClause;
					
					public static class Lisp_LoopForClause extends TokenChooser
					{
						public @CHOICE static class Lisp_ForArithmetic extends TokenSequence
						{
							public @S(10) Lisp_KeywordChoice direction = new Lisp_KeywordChoice(
									"across", "below", "from", "in", "on", "to");
							public @S(20) Lisp_SExpr expr;
						}
		
						public @CHOICE static class Lisp_ForEqualsThen extends TokenSequence
						{
							public @S(10) PunctuationEquals equals;
							public @S(20) Lisp_SExpr expr;
							public @S(30) @OPT Lisp_LoopForThen thenClause;
							
							public static class Lisp_LoopForThen extends TokenSequence
							{
								public @S(10) Lisp_Keyword THEN = new Lisp_Keyword("then");
								public @S(20) Lisp_SExpr expr;
							}
						}
					}
				}
			}
			
			public static class Lisp_LoopMainClause extends TokenChooser
			{
				public @CHOICE Lisp_LoopInitialFinal initialFinal;
				
				public @CHOICE static class Lisp_LoopUnconditionalDo extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice DO = new Lisp_KeywordChoice("do", "doing");
					public @S(20) TokenList<Lisp_SExpr> actions;
				}
				
				public @CHOICE static class Lisp_LoopUnconditionalReturn extends TokenSequence
				{
					public @S(10) Lisp_Keyword RETURN = new Lisp_Keyword("return");
					public @S(20) Lisp_SExpr value;
				}
				
				public @CHOICE static class Lisp_LoopListAccumulation extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice operation = new Lisp_KeywordChoice(
							"append",
							"appenging",
							"collect",
							"collecting",
							"nconc",
							"nconcing");
					public @S(20) Lisp_SExpr expr;
				}
				
				public @CHOICE static class Lisp_LoopNumericAccumulation extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice operation = new Lisp_KeywordChoice(
							"count",
							"counting",
							"maximize",
							"maximizing",
							"minimize",
							"minimizing",
							"sum",
							"summing");
					public @S(20) Lisp_SExpr value;
					public @S(30) @OPT Lisp_LoopAccumulateInto accumulateInto;
					
					public static class Lisp_LoopAccumulateInto extends TokenSequence
					{
						public @S(10) Lisp_Keyword INTO = new Lisp_Keyword("into");
						public @S(20) Lisp_Variable variable;
					}
				}
		
				public @CHOICE static class Lisp_LoopConditional extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice when = new Lisp_KeywordChoice("if", "when", "unless");
					public @S(20) Lisp_SExpr value;
					public @S(30) @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
					public @S(40) @OPT Lisp_LoopConditionalElse conditionalElse;
					public @S(50) @OPT Lisp_Keyword END = new Lisp_Keyword("end");
					
					public static class Lisp_LoopMoreSelectableClause extends TokenSequence
					{
						public @S(10) Lisp_Keyword AND = new Lisp_Keyword("and");
						public @S(20) Lisp_SExpr value;
					}
					
					public static class Lisp_LoopConditionalElse extends TokenSequence
					{
						public @S(10) Lisp_Keyword ELSE = new Lisp_Keyword("else");
						public @S(20) Lisp_SExpr clause;
						public @S(30) @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
					}
				}
		
				public @CHOICE static class Lisp_LoopTerminationTest extends TokenSequence
				{
					public @S(10) Lisp_KeywordChoice when = new Lisp_KeywordChoice(
							"while", "until", "repeat", "always", "never", "thereis");
					public @S(20) Lisp_SExpr condition;
				}
			}
			
			public static class Lisp_LoopInitialFinal extends TokenSequence
			{
				public @S(10) Lisp_KeywordChoice when = new Lisp_KeywordChoice("initially", "finally");
				public @S(20) Lisp_SExpr expr;
			}
		}
	}
}
