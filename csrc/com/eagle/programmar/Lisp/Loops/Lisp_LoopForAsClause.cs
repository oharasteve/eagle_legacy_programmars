// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.Lisp.Loops
{
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_Variable = com.eagle.programmar.Lisp.Lisp_Variable;
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Lisp_LoopForAsClause : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice FOR = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("for", "as");
		public Lisp_KeywordChoice FOR = new Lisp_KeywordChoice("for", "as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Variable var;
		public Lisp_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<Lisp_LoopForClause> forClause;
		public TokenList<Lisp_LoopForClause> forClause;

		public class Lisp_LoopForClause : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_ForArithmetic extends com.eagle.tokens.TokenSequence
			public class Lisp_ForArithmetic : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice direction = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("across", "below", "from", "in", "on", "to");
				public Lisp_KeywordChoice direction = new Lisp_KeywordChoice("across", "below", "from", "in", "on", "to");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression expr;
				public Lisp_Expression expr;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Lisp_ForEqualsThen extends com.eagle.tokens.TokenSequence
			public class Lisp_ForEqualsThen : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression expr;
				public Lisp_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Lisp_LoopForThen thenClause;
				public  OPT;

				public class Lisp_LoopForThen : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword THEN = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("then");
					public Lisp_Keyword THEN = new Lisp_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression expr;
					public Lisp_Expression expr;
				}
			}
		}
	}
}
