// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Go.Statements
{
	using Go_Type = com.eagle.programmar.Go.Go_Type;
	using Go_Type_Definition = com.eagle.programmar.Go.Symbols.Go_Type_Definition;
	using Go_Variable_Definition = com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
	using Go_EOLN = com.eagle.programmar.Go.Terminals.Go_EOLN;
	using Go_Keyword = com.eagle.programmar.Go.Terminals.Go_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class Go_TypeDefinition : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Terminals.Go_Keyword TYPE = new com.eagle.programmar.Go.Terminals.Go_Keyword("type");
		public Go_Keyword TYPE = new Go_Keyword("type");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Symbols.Go_Type_Definition typeName;
		public Go_Type_Definition typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @DOC("#Struct_types") com.eagle.programmar.Go.Terminals.Go_Keyword STRUCT = new com.eagle.programmar.Go.Terminals.Go_Keyword("struct");
		public @DOC("#Struct_types") Go_Keyword STRUCT = new Go_Keyword("struct");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Go.Terminals.Go_EOLN eoln1;
		public Go_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<Go_StructLine> structLine;
		public TokenList<Go_StructLine> structLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Go.Terminals.Go_EOLN eoln2;
		public Go_EOLN eoln2;

		public static class Go_StructLine extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Symbols.Go_Variable_Definition fieldName;
			public Go_Variable_Definition fieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Go_Type typeName;
			public Go_Type typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Terminals.Go_EOLN eoln;
			public Go_EOLN eoln;
		}
	}

}
