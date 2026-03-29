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
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_I_Input_Specification : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Program_Record_Id extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Program_Record_Id : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) RPG_I_Input_Program_Record_Id_Piece piece;
			public RPG_I_Input_Program_Record_Id_Piece piece;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_KeywordChoice number = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(17, 17, "1", "N");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Keyword option = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(18, 18, "O");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Literal indicator = new com.eagle.programmar.RPG.Terminals.RPG_Literal(19, 20);
			public RPG_Literal indicator = new RPG_Literal(19, 20);

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Number position1 = new com.eagle.programmar.RPG.Terminals.RPG_Number(21, 24);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Keyword not1 = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(25, 25, "N");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_KeywordChoice codePart1 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(26, 26, "C", "Z", "D");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Literal character1 = new com.eagle.programmar.RPG.Terminals.RPG_Literal(27, 27);
			public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Number position2 = new com.eagle.programmar.RPG.Terminals.RPG_Number(28, 31);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Keyword not2 = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(32, 32, "N");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT RPG_KeywordChoice codePart2 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(33, 33, "C", "Z", "D");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT RPG_Literal character2 = new com.eagle.programmar.RPG.Terminals.RPG_Literal(34, 34);
			public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT RPG_Number position3 = new com.eagle.programmar.RPG.Terminals.RPG_Number(35, 38);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) @OPT RPG_Keyword not3 = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(39, 39, "N");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) @OPT RPG_KeywordChoice codePart3 = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(40, 40, "C", "Z", "D");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) @OPT RPG_Literal character3 = new com.eagle.programmar.RPG.Terminals.RPG_Literal(41, 41);
			public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(42, 74);
			public RPG_Blanks blank1 = new RPG_Blanks(42, 74);

			public class RPG_I_Input_Program_Record_Id_Piece : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Program_Record_Id_Piece1 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Program_Record_Id_Piece1 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Literal filename = new com.eagle.programmar.RPG.Terminals.RPG_Literal(7, 14);
					public RPG_Literal filename = new RPG_Literal(7, 14);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal sequence = new com.eagle.programmar.RPG.Terminals.RPG_Literal(15, 16);
					public RPG_Literal sequence = new RPG_Literal(15, 16);
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Program_Record_Id_Piece2 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Program_Record_Id_Piece2 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 13);
					public RPG_Blanks blank1 = new RPG_Blanks(7, 13);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice logicalRelation = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(14, 16, "AND", "OR");
					public RPG_KeywordChoice logicalRelation = new RPG_KeywordChoice(14, 16, "AND", "OR");
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Program_Field_Descr extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Program_Field_Descr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 42);
			public RPG_Blanks blank1 = new RPG_Blanks(7, 42);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_KeywordChoice dataFormat = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(43, 43, "P", "B", "L", "R");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPG.Terminals.RPG_Number from = new com.eagle.programmar.RPG.Terminals.RPG_Number(44, 47);
			public RPG_Number from = new RPG_Number(44, 47);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Number to = new com.eagle.programmar.RPG.Terminals.RPG_Number(48, 51);
			public RPG_Number to = new RPG_Number(48, 51);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Number decimalPositions = new com.eagle.programmar.RPG.Terminals.RPG_Number(52, 52);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Literal fieldName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(53, 58);
			public RPG_Literal fieldName = new RPG_Literal(53, 58);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT RPG_Literal controlLevel = new com.eagle.programmar.RPG.Terminals.RPG_Literal(59, 60);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Literal matchFields = new com.eagle.programmar.RPG.Terminals.RPG_Literal(61, 62);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Literal fieldRelation = new com.eagle.programmar.RPG.Terminals.RPG_Literal(63, 64);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT RPG_Literal fieldIndicators = new com.eagle.programmar.RPG.Terminals.RPG_Literal(65, 70);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(71, 74);
			public RPG_Blanks blank2 = new RPG_Blanks(71, 74);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_External_Record_Id extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_External_Record_Id : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal filename = new com.eagle.programmar.RPG.Terminals.RPG_Literal(7, 14);
			public RPG_Literal filename = new RPG_Literal(7, 14);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(15, 18);
			public RPG_Blanks blank1 = new RPG_Blanks(15, 18);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Literal indicator = new com.eagle.programmar.RPG.Terminals.RPG_Literal(19, 20);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(21, 41);
			public RPG_Blanks blank2 = new RPG_Blanks(21, 41);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(42, 74);
			public RPG_Blanks blank3 = new RPG_Blanks(42, 74);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_External_Field_Descr extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_External_Field_Descr : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 20);
			public RPG_Blanks blank1 = new RPG_Blanks(7, 20);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Literal extFieldName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 30);
			public RPG_Literal extFieldName = new RPG_Literal(21, 30);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(31, 52);
			public RPG_Blanks blank2 = new RPG_Blanks(31, 52);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Literal rpgFieldName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(53, 58);
			public RPG_Literal rpgFieldName = new RPG_Literal(53, 58);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Literal controlLevel = new com.eagle.programmar.RPG.Terminals.RPG_Literal(59, 60);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Literal matchFields = new com.eagle.programmar.RPG.Terminals.RPG_Literal(61, 62);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(63, 64);
			public RPG_Blanks blank3 = new RPG_Blanks(63, 64);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Literal fieldIndicators = new com.eagle.programmar.RPG.Terminals.RPG_Literal(65, 70);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank4 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(71, 74);
			public RPG_Blanks blank4 = new RPG_Blanks(71, 74);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Structure extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Data_Structure : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal name = new com.eagle.programmar.RPG.Terminals.RPG_Literal(7, 12);
			public RPG_Literal name = new RPG_Literal(7, 12);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(13, 16);
			public RPG_Blanks blank1 = new RPG_Blanks(13, 16);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Keyword description = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(17, 17, "E");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_KeywordChoice option = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(18, 18, "I", "S", "U");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Keyword DS = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(19, 20, "DS");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Literal externalName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 30);
			public RPG_Literal externalName = new RPG_Literal(21, 30);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(31, 43);
			public RPG_Blanks blank2 = new RPG_Blanks(31, 43);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT RPG_Number occurrences = new com.eagle.programmar.RPG.Terminals.RPG_Number(44, 47);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT RPG_Number length = new com.eagle.programmar.RPG.Terminals.RPG_Number(48, 51);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(52, 74);
			public RPG_Blanks blank3 = new RPG_Blanks(52, 74);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Subfield extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Data_Subfield : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 7);
			public RPG_Blanks blank1 = new RPG_Blanks(7, 7);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) RPG_I_Input_Data_Subfield_Init init;
			public RPG_I_Input_Data_Subfield_Init init;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_KeywordChoice dataFormat = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(43, 43, "P", "B");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) RPG_I_Input_Data_Subfield_Position position;
			public RPG_I_Input_Data_Subfield_Position position;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Number decimalPositions = new com.eagle.programmar.RPG.Terminals.RPG_Number(52, 52);
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Literal fieldName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(53, 58);
			public RPG_Literal fieldName = new RPG_Literal(53, 58);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank4 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(59, 74);
			public RPG_Blanks blank4 = new RPG_Blanks(59, 74);

			public class RPG_I_Input_Data_Subfield_Init : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Subfield_Init1 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Data_Subfield_Init1 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT RPG_Keyword initialization = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(8, 8, "I");
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(9, 20);
					public RPG_Blanks blank2 = new RPG_Blanks(9, 20);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Literal initialValue = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 42);
					public RPG_Literal initialValue = new RPG_Literal(21, 42);
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Subfield_Init2 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Data_Subfield_Init2 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(8, 20);
					public RPG_Blanks blank2 = new RPG_Blanks(8, 20);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal externalName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 30);
					public RPG_Literal externalName = new RPG_Literal(21, 30);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(31, 42);
					public RPG_Blanks blank3 = new RPG_Blanks(31, 42);
				}
			}

			public class RPG_I_Input_Data_Subfield_Position : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Subfield_Position1 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Data_Subfield_Position1 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Number from = new com.eagle.programmar.RPG.Terminals.RPG_Number(44, 47);
					public RPG_Number from = new RPG_Number(44, 47);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Number to = new com.eagle.programmar.RPG.Terminals.RPG_Number(48, 51);
					public RPG_Number to = new RPG_Number(48, 51);
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Data_Subfield_Position2 extends com.eagle.tokens.TokenSequence
				public class RPG_I_Input_Data_Subfield_Position2 : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice keyword = new com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice(44, 51, "*STATUS", "*PROGRAM", "*PARMS", "*ROUTINE", "*FILE", "*RECORD", "*OPCODE", "*STATUS");
					public RPG_KeywordChoice keyword = new RPG_KeywordChoice(44, 51, "*STATUS", "*PROGRAM", "*PARMS", "*ROUTINE", "*FILE", "*RECORD", "*OPCODE", "*STATUS");
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Named_Constant extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Named_Constant : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 20);
			public RPG_Blanks blank1 = new RPG_Blanks(7, 20);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Literal constant = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 42);
			public RPG_Literal constant = new RPG_Literal(21, 42);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Keyword isConstant = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(43, 43, "C");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(44, 52);
			public RPG_Blanks blank2 = new RPG_Blanks(44, 52);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.RPG.Terminals.RPG_Literal name = new com.eagle.programmar.RPG.Terminals.RPG_Literal(53, 58);
			public RPG_Literal name = new RPG_Literal(53, 58);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank3 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(59, 74);
			public RPG_Blanks blank3 = new RPG_Blanks(59, 74);
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class RPG_I_Input_Constant_Continued extends com.eagle.tokens.TokenSequence
		public class RPG_I_Input_Constant_Continued : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword I = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "I");
			public RPG_Keyword I = new RPG_Keyword(6, 6, "I");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(7, 20);
			public RPG_Blanks blank1 = new RPG_Blanks(7, 20);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Literal constant = new com.eagle.programmar.RPG.Terminals.RPG_Literal(21, 42);
			public RPG_Literal constant = new RPG_Literal(21, 42);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank2 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(43, 74);
			public RPG_Blanks blank2 = new RPG_Blanks(43, 74);
		}
	}

}
