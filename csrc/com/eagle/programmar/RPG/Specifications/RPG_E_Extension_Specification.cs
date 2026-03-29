// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Blanks = com.eagle.programmar.RPG.Terminals.RPG_Blanks;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_E_Extension_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword E = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "E");
		public RPG_Keyword E = new RPG_Keyword(6, 6, "E");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 10);
		public RPG_Blanks blank1 = new RPG_Blanks(7, 10);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_Literal fromFileName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(11, 18);
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Literal toFileName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(19, 26);
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Literal arrayTable1 = new com.eagle.programmar.RPG.Terminals.RPG_Literal(27, 32);
		public RPG_Literal arrayTable1 = new RPG_Literal(27, 32);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.RPG.Terminals.RPG_Number entries = new com.eagle.programmar.RPG.Terminals.RPG_Number(36, 39);
		public RPG_Number entries = new RPG_Number(36, 39);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Number length1 = new com.eagle.programmar.RPG.Terminals.RPG_Number(40, 42);
		public RPG_Number length1 = new RPG_Number(40, 42);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_KeywordChoice dataFormat1 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(43, 43, "P", "B", "L", "R");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Number positions1 = new com.eagle.programmar.RPG.Terminals.RPG_Number(44, 44);
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_KeywordChoice sequence1 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(45, 45, "A", "D");
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.RPG.Terminals.RPG_Literal arrayTable2 = new com.eagle.programmar.RPG.Terminals.RPG_Literal(46, 51);
		public RPG_Literal arrayTable2 = new RPG_Literal(46, 51);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.RPG.Terminals.RPG_Number length2 = new com.eagle.programmar.RPG.Terminals.RPG_Number(52, 54);
		public RPG_Number length2 = new RPG_Number(52, 54);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT RPG_KeywordChoice dataFormat2 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(55, 55, "P", "B", "L", "R");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT RPG_Number positions2 = new com.eagle.programmar.RPG.Terminals.RPG_Number(56, 56);
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) @OPT RPG_KeywordChoice sequence2 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(57, 57, "A", "D");
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) @OPT RPG_Literal comments = new com.eagle.programmar.RPG.Terminals.RPG_Literal(58, 74);
		public  OPT;
	}

}
