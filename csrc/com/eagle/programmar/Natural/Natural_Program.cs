// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenList = com.eagle.tokens.TokenList;

	public class Natural_Program : AbstractLanguage
	{
		public const string NATURAL = "Natural";

		public Natural_Program() : base(NATURAL, new Natural_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://documentation.softwareag.com/natural/nat627unx/";
			}
		}

		// Components of a Natural Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Natural_Statement> statements;
		public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword END = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END");
		public  OPT;
	}

}
