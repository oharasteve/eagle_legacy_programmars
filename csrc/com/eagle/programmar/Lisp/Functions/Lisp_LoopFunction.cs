// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.Lisp.Functions
{
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_LoopConditional = com.eagle.programmar.Lisp.Loops.Lisp_LoopConditional;
	using Lisp_LoopForAsClause = com.eagle.programmar.Lisp.Loops.Lisp_LoopForAsClause;
	using Lisp_LoopInitialFinal = com.eagle.programmar.Lisp.Loops.Lisp_LoopInitialFinal;
	using Lisp_LoopListAccumulation = com.eagle.programmar.Lisp.Loops.Lisp_LoopListAccumulation;
	using Lisp_LoopNumericAccumulation = com.eagle.programmar.Lisp.Loops.Lisp_LoopNumericAccumulation;
	using Lisp_LoopTerminationTest = com.eagle.programmar.Lisp.Loops.Lisp_LoopTerminationTest;
	using Lisp_LoopUnconditionalDo = com.eagle.programmar.Lisp.Loops.Lisp_LoopUnconditionalDo;
	using Lisp_LoopUnconditionalReturn = com.eagle.programmar.Lisp.Loops.Lisp_LoopUnconditionalReturn;
	using Lisp_LoopWith = com.eagle.programmar.Lisp.Loops.Lisp_LoopWith;
	using Lisp_Variable_Definition = com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_LoopFunction : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("m_loop.htm") com.eagle.programmar.Lisp.Terminals.Lisp_Keyword LOOP = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("loop");
		public @DOC("m_loop.htm") Lisp_Keyword LOOP = new Lisp_Keyword("loop");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Lisp_LoopType loopType;
		public Lisp_LoopType loopType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public static class Lisp_LoopType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_Expression XXsimpleExpr;
			public Lisp_Expression XXsimpleExpr;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_LoopFancy extends com.eagle.tokens.TokenSequence
			public static class Lisp_LoopFancy extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Lisp_LoopNamed named;
				public @OPT Lisp_LoopNamed named;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Lisp_LoopVariableClause> variableClauses;
				public @OPT TokenList<Lisp_LoopVariableClause> variableClauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<Lisp_LoopMainClause> mainClauses;
				public TokenList<Lisp_LoopMainClause> mainClauses;

				public static class Lisp_LoopNamed extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword NAMED = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("named");
					public Lisp_Keyword NAMED = new Lisp_Keyword("named");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition name;
					public Lisp_Variable_Definition name;
				}

				public static class Lisp_LoopVariableClause extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopInitialFinal XXinitialFinal;
					public Lisp_LoopInitialFinal XXinitialFinal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopWith XXwith;
					public Lisp_LoopWith XXwith;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopForAsClause XXforAs;
					public Lisp_LoopForAsClause XXforAs;
				}

				public static class Lisp_LoopMainClause extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopInitialFinal XXinitialFinal;
					public Lisp_LoopInitialFinal XXinitialFinal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopUnconditionalDo XXunconditionalDo;
					public Lisp_LoopUnconditionalDo XXunconditionalDo;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopUnconditionalReturn XXunconditionalReturn;
					public Lisp_LoopUnconditionalReturn XXunconditionalReturn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopListAccumulation XXlistAccumulation;
					public Lisp_LoopListAccumulation XXlistAccumulation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopNumericAccumulation XXnumericAccumulation;
					public Lisp_LoopNumericAccumulation XXnumericAccumulation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopConditional XXconditional;
					public Lisp_LoopConditional XXconditional;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Lisp_LoopTerminationTest XXterminationTest;
					public Lisp_LoopTerminationTest XXterminationTest;
				}
			}
		}
	}

}
