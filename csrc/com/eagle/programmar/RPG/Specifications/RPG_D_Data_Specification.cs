// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Blanks = com.eagle.programmar.RPG.Terminals.RPG_Blanks;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public abstract class RPG_D_Data_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword D = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(5, 6, " D");
		public RPG_Keyword D = new RPG_Keyword(5, 6, " D");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT RPG_Literal name;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Keyword externalDescription;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_KeywordChoice entryType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_KeywordChoice definitionType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Number fromPosition;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Number toPositionOrLength;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_KeywordChoice dataType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Number decimalPosition;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank;
		public RPG_Blanks blank;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Literal keywords;
		public  OPT;

		public class RPG_D_Data_Specification_III : RPG_D_Data_Specification
		{
			// Not available in RPG III
		}

		public class RPG_D_Data_Specification_IV : RPG_D_Data_Specification
		{
			public RPG_D_Data_Specification_IV()
			{
				name = new RPG_Literal(7, 21);
				externalDescription = new RPG_Keyword(22, 22, "E");
				entryType = new RPG_KeywordChoice(23, 23, "S", "U");
				definitionType = new RPG_KeywordChoice(24, 25, "C", "DS", "PI", "PR", "S");
				fromPosition = new RPG_Number(26, 32);
				toPositionOrLength = new RPG_Number(33, 39);
				dataType = new RPG_KeywordChoice(40, 40, "A", "B", "C", "D", "F", "G", "I", "N", "O", "P", "S", "T", "U", "Z");
				decimalPosition = new RPG_Number(41, 42);
				blank = new RPG_Blanks(43, 43);
				keywords = new RPG_Literal(44, 80);
			}
		}
	}

}
