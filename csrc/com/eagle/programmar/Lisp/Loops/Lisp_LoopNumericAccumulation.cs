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
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Lisp_LoopNumericAccumulation : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice operation = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("count", "counting", "maximize", "maximizing", "minimize", "minimizing", "sum", "summing");
		public Lisp_KeywordChoice operation = new Lisp_KeywordChoice("count", "counting", "maximize", "maximizing", "minimize", "minimizing", "sum", "summing");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Expression value;
		public Lisp_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Lisp_LoopAccumulateInto accumulateInto;
		public  OPT;

		public class Lisp_LoopAccumulateInto : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword INTO = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("into");
			public Lisp_Keyword INTO = new Lisp_Keyword("into");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Lisp_Variable variable;
			public Lisp_Variable variable;
		}
	}
}
