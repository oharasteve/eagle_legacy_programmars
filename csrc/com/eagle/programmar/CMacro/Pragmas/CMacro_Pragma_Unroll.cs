// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.CMacro.Pragmas
{
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_Number = com.eagle.programmar.CMacro.Terminals.CMacro_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Pragma_Unroll : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword UNROLL = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("unroll");
		public CMacro_Keyword UNROLL = new CMacro_Keyword("unroll");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_Number what;
		public CMacro_Number what; // 1 perhaps
	}
}
