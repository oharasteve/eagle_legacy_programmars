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
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Lisp_LoopWith : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Lisp_LoopWithElement element;
		public Lisp_LoopWithElement element;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Lisp_LoopMoreWith> more;
		public  OPT;

		public class Lisp_LoopMoreWith : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword AND = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("and");
			public Lisp_Keyword AND = new Lisp_Keyword("and");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Lisp_LoopWithElement element;
			public Lisp_LoopWithElement element;
		}

		public class Lisp_LoopWithElement : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword WITH = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("with");
			public Lisp_Keyword WITH = new Lisp_Keyword("with");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Lisp_Expression typeSpec;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Lisp_LoopWithValue equalsValue;
			public  OPT;

			public class Lisp_LoopWithValue : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression value;
				public Lisp_Expression value;
			}
		}
	}
}
