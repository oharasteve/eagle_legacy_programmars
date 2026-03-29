// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using IntelASM_Identifier_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
	using IntelASM_KeywordChoice = com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
	using IntelASM_Punctuation = com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_IfDefDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation percent = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation('%');
		public IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice IFDEF = new com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice("ifdef", "ifndef");
		public IntelASM_KeywordChoice IFDEF = new IntelASM_KeywordChoice("ifdef", "ifndef");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference name;
		public IntelASM_Identifier_Reference name;
	}

}
