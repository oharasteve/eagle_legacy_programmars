// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.CMacro.Pragmas
{
	using CMacro_KeywordChoice = com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Pragma_Export : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice EXPORT = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("export", "import");
		public CMacro_KeywordChoice EXPORT = new CMacro_KeywordChoice("export", "import");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice ON = new com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice("on", "off");
		public CMacro_KeywordChoice ON = new CMacro_KeywordChoice("on", "off");
	}

}
