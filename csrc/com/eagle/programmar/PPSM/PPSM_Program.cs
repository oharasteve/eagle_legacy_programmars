// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 22, 2015

namespace com.eagle.programmar.PPSM
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using PPSM_KeywordChoice = com.eagle.programmar.PPSM.Terminals.PPSM_KeywordChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class PPSM_Program : AbstractLanguage
	{
		public const string PPSM = "PPSM";

		public PPSM_Program() : base(PPSM, new PPSM_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Not Applicable";
			}
		}

		public class PPSM_Element : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PPSM.Terminals.PPSM_KeywordChoice x = new com.eagle.programmar.PPSM.Terminals.PPSM_KeywordChoice("a", "b");
			public PPSM_KeywordChoice x = new PPSM_KeywordChoice("a", "b");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PPSM_KeywordChoice y = new com.eagle.programmar.PPSM.Terminals.PPSM_KeywordChoice("c", "d", "e");
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<PPSM_Element> elements;
		public TokenList<PPSM_Element> elements;
	}

}
