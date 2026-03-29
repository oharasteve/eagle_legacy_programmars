// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class IntelASM_CpuDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword CPU = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("cpu");
		public IntelASM_Keyword CPU = new IntelASM_Keyword("cpu");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<IntelASM_CPUValue, com.eagle.tokens.punctuation.PunctuationComma> values;
		public SeparatedList<IntelASM_CPUValue, PunctuationComma> values;

		public class IntelASM_CPUValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice cpu = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("MMX", "SSE", "SSE2", "SSE3", "SSSE3");
			public IntelASM_KeywordChoice cpu = new IntelASM_KeywordChoice("MMX", "SSE", "SSE2", "SSE3", "SSSE3");
		}
	}

}
