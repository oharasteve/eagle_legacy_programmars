// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using TokenList = com.eagle.tokens.TokenList;

	public class BNF_Program : AbstractLanguage
	{
		public const string BNF = "BNF";

		public BNF_Program() : base(BNF, new BNF_Syntax())
		{
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<BNF_Rule> rules;
		public TokenList<BNF_Rule> rules;
	}

}
