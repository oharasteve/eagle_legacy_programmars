// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.C
{
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class C_DataModifiers : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_KeywordChoice XXscope = new com.eagle.programmar.C.Terminals.C_KeywordChoice(C_Program.getModifiers());
		public C_KeywordChoice XXscope = new C_KeywordChoice(C_Program.Modifiers);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Declaration XXdeclaration;
		public C_Declaration XXdeclaration;
	}

}
