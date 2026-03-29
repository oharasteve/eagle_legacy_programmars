// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2022

namespace com.eagle.programmar.COBOL
{
	using COBOL_ObjectReference = com.eagle.programmar.COBOL.Picture.COBOL_ObjectReference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ObjectReference XXobjectReference;
		public COBOL_ObjectReference XXobjectReference;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_KeywordChoice XXtype = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("1-RECTL", "2SIZE", "BINARY-LONG", "BYTE", "CLIPFORMAT", "CLSID", "DATA-POINTER", "DWORD", "FILETIME", "FLOAT-LONG", "FORMATETC", "IID", "LONG", "POINT", "POINTER", "PROCEDURE-POINTER", "TAGMSG", "TAGPOINT", "TAGRECT", "TAGSIZE", "UINT", "ULARGE-INTEGER", "ULONG", "USHORT", "VARTYPE", "WORD", "WRAPPED-BYTE");
		public COBOL_KeywordChoice XXtype = new COBOL_KeywordChoice("1-RECTL", "2SIZE", "BINARY-LONG", "BYTE", "CLIPFORMAT", "CLSID", "DATA-POINTER", "DWORD", "FILETIME", "FLOAT-LONG", "FORMATETC", "IID", "LONG", "POINT", "POINTER", "PROCEDURE-POINTER", "TAGMSG", "TAGPOINT", "TAGRECT", "TAGSIZE", "UINT", "ULARGE-INTEGER", "ULONG", "USHORT", "VARTYPE", "WORD", "WRAPPED-BYTE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_TypeType extends com.eagle.tokens.TokenSequence
		public class COBOL_TypeType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TYPE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TYPE");
			public COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Literal typename;
			public COBOL_Literal typename;
		}
	}

}
