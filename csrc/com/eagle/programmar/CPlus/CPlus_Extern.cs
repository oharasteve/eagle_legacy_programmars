// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 24, 2022

namespace com.eagle.programmar.CPlus
{
	using C_Extern_C = com.eagle.programmar.C.C_Extern_C;
	using C_TypeDef = com.eagle.programmar.C.C_TypeDef;
	using C_StatementBlock = com.eagle.programmar.C.Statements.C_StatementBlock;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CPlus_Extern : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Extern_C externC;
		public C_Extern_C externC;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CPlus_Extern_What what;
		public CPlus_Extern_What what;

		public class CPlus_Extern_What : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_Method XXmethod;
			public CPlus_Method XXmethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_StatementBlock XXblock;
			public C_StatementBlock XXblock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_TypeDef XXtypedef;
			public C_TypeDef XXtypedef;
		}
	}

}
