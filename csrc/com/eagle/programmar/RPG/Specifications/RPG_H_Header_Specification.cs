// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public abstract class RPG_H_Header_Specification : TokenSequence
	{
		public class RPG_H_Header_Specification_III : RPG_H_Header_Specification
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword H = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "H");
			public RPG_Keyword H = new RPG_Keyword(6, 6, "H");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT RPG_Keyword debug = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(15, 15, "1");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Literal currency = new com.eagle.programmar.RPG.Terminals.RPG_Literal(18, 18);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_KeywordChoice dateFormat = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(19, 19, "M", "D", "Y");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Literal dateEditCode = new com.eagle.programmar.RPG.Terminals.RPG_Literal(20, 20);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_KeywordChoice decimalNotation = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(21, 21, "I", "J", "D");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Keyword collcatingSequence = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(26, 26, "S");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_Keyword formsAlignment = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(41, 41, "1");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Keyword fileTranslation = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(43, 43, "F");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Keyword transparencyCheck = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(57, 57, "1");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Literal programIdentification = new com.eagle.programmar.RPG.Terminals.RPG_Literal(75, 80);
			public  OPT;
		}

		public class RPG_H_Header_Specification_IV : RPG_H_Header_Specification
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword H = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "H");
			public RPG_Keyword H = new RPG_Keyword(6, 6, "H");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT RPG_Literal keywords = new com.eagle.programmar.RPG.Terminals.RPG_Literal(7, 80);
			public  OPT;
		}
	}

}
