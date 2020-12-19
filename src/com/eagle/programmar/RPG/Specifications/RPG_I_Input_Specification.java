// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class RPG_I_Input_Specification extends TokenChooser
{
	public @CHOICE static class RPG_I_Input_Program_Record_Id extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_I_Input_Program_Record_Id_Piece piece;
		public @S(30) @OPT RPG_KeywordChoice number = new RPG_KeywordChoice(17, 17, "1", "N");
		public @S(40) @OPT RPG_Keyword option = new RPG_Keyword(18, 18, "O");
		public @S(50) RPG_Literal indicator = new RPG_Literal(19, 20);
		
		public @S(60) @OPT RPG_Number position1 = new RPG_Number(21, 24);
		public @S(70) @OPT RPG_Keyword not1 = new RPG_Keyword(25, 25, "N");
		public @S(80) @OPT RPG_KeywordChoice codePart1 = new RPG_KeywordChoice(26, 26, "C", "Z", "D");
		public @S(90) @OPT RPG_Literal character1 = new RPG_Literal(27, 27);
		
		public @S(100) @OPT RPG_Number position2 = new RPG_Number(28, 31);
		public @S(110) @OPT RPG_Keyword not2 = new RPG_Keyword(32, 32, "N");
		public @S(120) @OPT RPG_KeywordChoice codePart2 = new RPG_KeywordChoice(33, 33, "C", "Z", "D");
		public @S(130) @OPT RPG_Literal character2 = new RPG_Literal(34, 34);
		
		public @S(140) @OPT RPG_Number position3 = new RPG_Number(35, 38);
		public @S(150) @OPT RPG_Keyword not3 = new RPG_Keyword(39, 39, "N");
		public @S(160) @OPT RPG_KeywordChoice codePart3 = new RPG_KeywordChoice(40, 40, "C", "Z", "D");
		public @S(170) @OPT RPG_Literal character3 = new RPG_Literal(41, 41);
		
		public @S(180) RPG_Blanks blank1 = new RPG_Blanks(42, 74);
		
		public static class RPG_I_Input_Program_Record_Id_Piece extends TokenChooser
		{
			public @CHOICE static class RPG_I_Input_Program_Record_Id_Piece1 extends TokenSequence
			{
				public @S(10) RPG_Literal filename = new RPG_Literal(7, 14);
				public @S(20) RPG_Literal sequence = new RPG_Literal(15, 16);
			}

			public @CHOICE static class RPG_I_Input_Program_Record_Id_Piece2 extends TokenSequence
			{
				public @S(10) RPG_Blanks blank1 = new RPG_Blanks(7, 13);
				public @S(20) RPG_KeywordChoice logicalRelation = new RPG_KeywordChoice(14, 16,
						"AND", "OR");
			}
		}
	}

	public @CHOICE static class RPG_I_Input_Program_Field_Descr extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Blanks blank1 = new RPG_Blanks(7, 42);
		public @S(30) @OPT RPG_KeywordChoice dataFormat = new RPG_KeywordChoice(43, 43,
				"P", "B", "L", "R");
		public @S(40) RPG_Number from = new RPG_Number(44, 47);
		public @S(50) RPG_Number to = new RPG_Number(48, 51);
		public @S(60) @OPT RPG_Number decimalPositions = new RPG_Number(52, 52);
		public @S(70) RPG_Literal fieldName = new RPG_Literal(53, 58);
		public @S(80) @OPT RPG_Literal controlLevel = new RPG_Literal(59, 60);
		public @S(90) @OPT RPG_Literal matchFields = new RPG_Literal(61, 62);
		public @S(100) @OPT RPG_Literal fieldRelation = new RPG_Literal(63, 64);
		public @S(110) @OPT RPG_Literal fieldIndicators = new RPG_Literal(65, 70);
		public @S(120) RPG_Blanks blank2 = new RPG_Blanks(71, 74);
	}

	public @CHOICE static class RPG_I_Input_External_Record_Id extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Literal filename = new RPG_Literal(7, 14);
		public @S(30) RPG_Blanks blank1 = new RPG_Blanks(15, 18);
		public @S(40) @OPT RPG_Literal indicator = new RPG_Literal(19, 20);
		public @S(50) RPG_Blanks blank2 = new RPG_Blanks(21, 41);
		public @S(60) RPG_Blanks blank3 = new RPG_Blanks(42, 74);
	}

	public @CHOICE static class RPG_I_Input_External_Field_Descr extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Blanks blank1 = new RPG_Blanks(7, 20);
		public @S(30) RPG_Literal extFieldName = new RPG_Literal(21, 30);
		public @S(40) RPG_Blanks blank2 = new RPG_Blanks(31, 52);
		public @S(50) RPG_Literal rpgFieldName = new RPG_Literal(53, 58);
		public @S(60) @OPT RPG_Literal controlLevel = new RPG_Literal(59, 60);
		public @S(70) @OPT RPG_Literal matchFields = new RPG_Literal(61, 62);
		public @S(80) RPG_Blanks blank3 = new RPG_Blanks(63, 64);
		public @S(90) @OPT RPG_Literal fieldIndicators = new RPG_Literal(65, 70);
		public @S(100) RPG_Blanks blank4 = new RPG_Blanks(71, 74);
	}

	public @CHOICE static class RPG_I_Input_Data_Structure extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Literal name = new RPG_Literal(7, 12);
		public @S(30) RPG_Blanks blank1 = new RPG_Blanks(13, 16);
		public @S(40) @OPT RPG_Keyword description = new RPG_Keyword(17, 17, "E");
		public @S(50) @OPT RPG_KeywordChoice option = new RPG_KeywordChoice(18, 18, "I", "S", "U");
		public @S(60) @OPT RPG_Keyword DS = new RPG_Keyword(19, 20, "DS");
		public @S(70) RPG_Literal externalName = new RPG_Literal(21, 30);
		public @S(80) RPG_Blanks blank2 = new RPG_Blanks(31, 43);
		public @S(90) @OPT RPG_Number occurrences = new RPG_Number(44, 47);
		public @S(100) @OPT RPG_Number length = new RPG_Number(48, 51);
		public @S(110) RPG_Blanks blank3 = new RPG_Blanks(52, 74);
	}

	public @CHOICE static class RPG_I_Input_Data_Subfield extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Blanks blank1 = new RPG_Blanks(7, 7);
		public @S(30) RPG_I_Input_Data_Subfield_Init init;
		public @S(40) @OPT RPG_KeywordChoice dataFormat = new RPG_KeywordChoice(43, 43, "P", "B");
		public @S(50) RPG_I_Input_Data_Subfield_Position position;
		public @S(60) @OPT RPG_Number decimalPositions = new RPG_Number(52, 52);
		public @S(70) RPG_Literal fieldName = new RPG_Literal(53, 58);
		public @S(80) RPG_Blanks blank4 = new RPG_Blanks(59, 74);
		
		public static class RPG_I_Input_Data_Subfield_Init extends TokenChooser
		{
			public @CHOICE static class RPG_I_Input_Data_Subfield_Init1 extends TokenSequence
			{
				public @S(10) @OPT RPG_Keyword initialization = new RPG_Keyword(8, 8, "I");
				public @S(20) RPG_Blanks blank2 = new RPG_Blanks(9, 20);
				public @S(30) RPG_Literal initialValue = new RPG_Literal(21, 42);
			}

			public @CHOICE static class RPG_I_Input_Data_Subfield_Init2 extends TokenSequence
			{
				public @S(10) RPG_Blanks blank2 = new RPG_Blanks(8, 20);
				public @S(20) RPG_Literal externalName = new RPG_Literal(21, 30);
				public @S(30) RPG_Blanks blank3 = new RPG_Blanks(31, 42);
			}
		}

		public static class RPG_I_Input_Data_Subfield_Position extends TokenChooser
		{
			public @CHOICE static class RPG_I_Input_Data_Subfield_Position1 extends TokenSequence
			{
				public @S(10) RPG_Number from = new RPG_Number(44, 47);
				public @S(20) RPG_Number to = new RPG_Number(48, 51);
			}

			public @CHOICE static class RPG_I_Input_Data_Subfield_Position2 extends TokenSequence
			{
				public @S(10) RPG_KeywordChoice keyword = new RPG_KeywordChoice(44, 51,
						"*STATUS", "*PROGRAM", "*PARMS", "*ROUTINE",
						"*FILE", "*RECORD", "*OPCODE", "*STATUS");
			}
		}
	}

	public @CHOICE static class RPG_I_Input_Named_Constant extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Blanks blank1 = new RPG_Blanks(7, 20);
		public @S(30) RPG_Literal constant = new RPG_Literal(21, 42);
		public @S(40) @OPT RPG_Keyword isConstant = new RPG_Keyword(43, 43, "C");
		public @S(50) RPG_Blanks blank2 = new RPG_Blanks(44, 52);
		public @S(60) RPG_Literal name = new RPG_Literal(53, 58);
		public @S(70) RPG_Blanks blank3 = new RPG_Blanks(59, 74);
	}

	public @CHOICE static class RPG_I_Input_Constant_Continued extends TokenSequence
	{
		public @S(10) RPG_Keyword I = new RPG_Keyword(6, 6, "I");
		public @S(20) RPG_Blanks blank1 = new RPG_Blanks(7, 20);
		public @S(30) RPG_Literal constant = new RPG_Literal(21, 42);
		public @S(40) RPG_Blanks blank2 = new RPG_Blanks(43, 74);
	}
}
