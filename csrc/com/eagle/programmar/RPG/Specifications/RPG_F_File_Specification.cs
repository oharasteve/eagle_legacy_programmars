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

	public abstract class RPG_F_File_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword F = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "F");
		public RPG_Keyword F = new RPG_Keyword(6, 6, "F");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT RPG_Literal fileName;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_KeywordChoice fileType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_KeywordChoice fileDesignation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Keyword endOfFile;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_KeywordChoice sequence;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_KeywordChoice fileFormat;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1;
		public RPG_Blanks blank1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Number recordLength;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Keyword limitsProcessing;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Number fieldLength;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT RPG_KeywordChoice recordAddressType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT RPG_KeywordChoice fileOrganization;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT RPG_Literal overflowIndicator;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) @OPT RPG_Number keyStart;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) @OPT RPG_KeywordChoice extensionCode;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) @OPT RPG_KeywordChoice device;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2;
		public RPG_Blanks blank2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(190) @OPT RPG_Keyword continuationLines;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(200) @OPT RPG_Literal routine;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(210) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3;
		public RPG_Blanks blank3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(220) @OPT RPG_Keyword fileAddition;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(230) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank4;
		public RPG_Blanks blank4;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(240) @OPT RPG_Literal fileCondition;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(250) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank5;
		public RPG_Blanks blank5;

		public class RPG_F_File_Specification_III : RPG_F_File_Specification
		{
			public RPG_F_File_Specification_III()
			{
				fileName = new RPG_Literal(7, 14);
				fileType = new RPG_KeywordChoice(15, 15, "I", "O", "U", "C");
				fileDesignation = new RPG_KeywordChoice(16, 16, "P", "S", "R", "T", "F");
				endOfFile = new RPG_Keyword(17, 17, "E");
				sequence = new RPG_KeywordChoice(18, 18, "A", "D");
				fileFormat = new RPG_KeywordChoice(19, 19, "F", "E");
				blank1 = new RPG_Blanks(20, 23);
				recordLength = new RPG_Number(24, 27);
				limitsProcessing = new RPG_Keyword(28, 28, "L");
				fieldLength = new RPG_Number(29, 30);
				recordAddressType = new RPG_KeywordChoice(31, 31, "A", "P", "K");
				fileOrganization = new RPG_KeywordChoice(32, 32, "I", "T");
				overflowIndicator = new RPG_Literal(33, 34);
				keyStart = new RPG_Number(35, 38);
				extensionCode = new RPG_KeywordChoice(39, 39, "E", "L");
				device = new RPG_KeywordChoice(40, 46, "PRINTER", "DISK", "WORKSTN", "SPECIAL", "SEQ");
				blank2 = new RPG_Blanks(47, 52);
				continuationLines = new RPG_Keyword(53, 53, "K");
				routine = new RPG_Literal(54, 59);
				blank3 = new RPG_Blanks(60, 65);
				fileAddition = new RPG_Keyword(66, 66, "A");
				blank4 = new RPG_Blanks(67, 70);
				fileCondition = new RPG_Literal(71, 72);
				blank5 = new RPG_Blanks(73, 74);
			}
		}

		public class RPG_F_File_Specification_IV : RPG_F_File_Specification
		{
			public RPG_F_File_Specification_IV()
			{
				fileName = new RPG_Literal(7, 16);
				fileType = new RPG_KeywordChoice(17, 17, "I", "O", "U", "C");
				fileDesignation = new RPG_KeywordChoice(18, 18, "P", "S", "R", "T", "F");
				endOfFile = new RPG_Keyword(19, 19, "E");
				sequence = new RPG_KeywordChoice(21, 21, "A", "D");
				fileFormat = new RPG_KeywordChoice(22, 22, "F", "E");
				blank1 = new RPG_Blanks(0, 0); // Not Applicable
				recordLength = new RPG_Number(23, 27);
				limitsProcessing = new RPG_Keyword(28, 28, "L");
				fieldLength = new RPG_Number(29, 33);
				recordAddressType = new RPG_KeywordChoice(34, 34, "A", "P", "K");
				fileOrganization = new RPG_KeywordChoice(35, 35, "I", "T");
				overflowIndicator = new RPG_Literal(0, 0); // ??
				keyStart = new RPG_Number(0, 0); // ??
				extensionCode = new RPG_KeywordChoice(0, 0, "E", "L");
				device = new RPG_KeywordChoice(36, 42, "PRINTER", "DISK", "WORKSTN", "SPECIAL", "SEQ");
				blank2 = new RPG_Blanks(43, 43);
				continuationLines = new RPG_Keyword(0, 0, "K"); // Not Applicable
				routine = new RPG_Literal(0, 0); // ??
				blank3 = new RPG_Blanks(0, 0); // Not Applicable
				fileAddition = new RPG_Keyword(20, 20, "A");
				blank4 = new RPG_Blanks(0, 0); // ??
				fileCondition = new RPG_Literal(0, 0); // ??
				blank5 = new RPG_Blanks(0, 0); // ??
			}
		}
	}

}
