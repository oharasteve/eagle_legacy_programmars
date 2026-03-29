// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

namespace com.eagle.programmar.C
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Literal = com.eagle.programmar.C.Terminals.C_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class C_Extern_C : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword EXTERN = new com.eagle.programmar.C.Terminals.C_Keyword("extern");
		public C_Keyword EXTERN = new C_Keyword("extern");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_Literal_C C;
		public C_Literal_C C;

		public class C_Literal_C : C_Literal
		{
			public override bool parse(EagleFileReader lines)
			{
				if (!base.parse(lines))
				{
					return false;
				}
				return _txt.Equals("\"C\"");
			}
		}
	}

}
