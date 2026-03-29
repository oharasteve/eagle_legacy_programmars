// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using IntelASM_Identifier_Reference = com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using IntelASM_Literal = com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
	using IntelASM_Punctuation = com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class IntelASM_IncludeDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT IntelASM_Punctuation percent = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation('%');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword INCLUDE = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("include");
		public IntelASM_Keyword INCLUDE = new IntelASM_Keyword("include");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) IntelASM_Filename filename;
		public IntelASM_Filename filename;

		public class IntelASM_Filename : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE IntelASM_Literal XXliteral;
			public IntelASM_Literal XXliteral;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class IntelASM_BareFilename extends com.eagle.tokens.TokenSequence
			public class IntelASM_BareFilename : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> name;
				public SeparatedList<IntelASM_Identifier_Reference, PunctuationPeriod> name;
			}
		}
	}

}
