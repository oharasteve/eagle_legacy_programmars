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

	public abstract class RPG_C_Calculation_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword C = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "C");
		public RPG_Keyword C = new RPG_Keyword(6, 6, "C");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT RPG_Literal controlLevel;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Keyword not1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Literal indicator1;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Keyword not2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Literal indicator2;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Keyword not3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_Literal indicator3;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Literal factor1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Literal operation;
		public  OPT; // Operations 'eval' and 'if' are very different!
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Literal factor2;
		public  OPT; // Rest of line is free format for them, and possibly continued
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT RPG_Literal result;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT RPG_Literal length;
		public  OPT; // Really should be RPG_Number
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT RPG_Literal decimalPositions;
		public  OPT; // Really should be RPG_Number
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) @OPT RPG_KeywordChoice operationExtender;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) @OPT RPG_Literal resultIndicator1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) @OPT RPG_Literal resultIndicator2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) @OPT RPG_Literal resultIndicator3;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(190) @OPT RPG_Literal comments;
		public  OPT;

		public class RPG_C_Calculation_Specification_III : RPG_C_Calculation_Specification
		{
			public RPG_C_Calculation_Specification_III()
			{
				controlLevel = new RPG_Literal(7, 8);
				not1 = new RPG_Keyword(9, 9, "N");
				indicator1 = new RPG_Literal(10, 11);
				not2 = new RPG_Keyword(12, 12, "N");
				indicator2 = new RPG_Literal(13, 14);
				not3 = new RPG_Keyword(15, 15, "N");
				indicator3 = new RPG_Literal(16, 17);
				factor1 = new RPG_Literal(18, 27);
				operation = new RPG_Literal(28, 32);
				factor2 = new RPG_Literal(33, 42);
				result = new RPG_Literal(43, 48);
				length = new RPG_Literal(49, 51);
				decimalPositions = new RPG_Literal(52, 52);
				operationExtender = new RPG_KeywordChoice(53, 53, "H", "N", "P", "R");
				resultIndicator1 = new RPG_Literal(54, 55);
				resultIndicator2 = new RPG_Literal(56, 57);
				resultIndicator3 = new RPG_Literal(58, 59);
				comments = new RPG_Literal(60, 74);
			}
		}

		public class RPG_C_Calculation_Specification_IV : RPG_C_Calculation_Specification
		{
			public RPG_C_Calculation_Specification_IV()
			{
				controlLevel = new RPG_Literal(7, 8);
				not1 = new RPG_Keyword(9, 9, "N");
				indicator1 = new RPG_Literal(10, 11);
				not2 = new RPG_Keyword(0, 0, "N"); // Unused
				indicator2 = new RPG_Literal(0, 0); // Unused
				not3 = new RPG_Keyword(0, 0, "N"); // Unused
				indicator3 = new RPG_Literal(0, 0); // Unused
				factor1 = new RPG_Literal(12, 25);
				operation = new RPG_Literal(26, 35);
				factor2 = new RPG_Literal(36, 49);
				result = new RPG_Literal(50, 63);
				length = new RPG_Literal(64, 68);
				decimalPositions = new RPG_Literal(69, 70);
				operationExtender = new RPG_KeywordChoice(0, 0, "A", "D", "E", "H", "M", "N", "P", "R", "T", "Z");
				resultIndicator1 = new RPG_Literal(71, 72);
				resultIndicator2 = new RPG_Literal(73, 74);
				resultIndicator3 = new RPG_Literal(75, 76);
				comments = new RPG_Literal(77, 80);
			}
		}
	}

}
