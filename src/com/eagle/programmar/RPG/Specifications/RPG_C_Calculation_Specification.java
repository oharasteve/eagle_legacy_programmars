// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_C_Calculation_Specification extends TokenSequence
{
	public @S(10) RPG_Keyword C = new RPG_Keyword(6, 6, "C");
	public @S(20) @OPT RPG_Literal controlLevel;

	public @S(30) @OPT RPG_Keyword not1;
	public @S(40) @OPT RPG_Literal indicator1;

	public @S(50) @OPT RPG_Keyword not2;
	public @S(60) @OPT RPG_Literal indicator2;

	public @S(70) @OPT RPG_Keyword not3;
	public @S(80) @OPT RPG_Literal indicator3;

	public @S(90) @OPT RPG_Literal factor1;
	public @S(100) @OPT RPG_Literal operation; // Operations 'eval' and 'if' are very different!
	public @S(110) @OPT RPG_Literal factor2; // Rest of line is free format for them, and possibly continued
	public @S(120) @OPT RPG_Literal result;
	public @S(130) @OPT RPG_Literal length; // Really should be RPG_Number
	public @S(140) @OPT RPG_Literal decimalPositions; // Really should be RPG_Number
	public @S(150) @OPT RPG_KeywordChoice operationExtender;

	public @S(160) @OPT RPG_Literal resultIndicator1;
	public @S(170) @OPT RPG_Literal resultIndicator2;
	public @S(180) @OPT RPG_Literal resultIndicator3;

	public @S(190) @OPT RPG_Literal comments;

	public static class RPG_C_Calculation_Specification_III extends RPG_C_Calculation_Specification
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

	public static class RPG_C_Calculation_Specification_IV extends RPG_C_Calculation_Specification
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
			operationExtender = new RPG_KeywordChoice(0, 0, // Unused
					"A", "D", "E", "H", "M", "N", "P", "R", "T", "Z");
			resultIndicator1 = new RPG_Literal(71, 72);
			resultIndicator2 = new RPG_Literal(73, 74);
			resultIndicator3 = new RPG_Literal(75, 76);
			comments = new RPG_Literal(77, 80);
		}
	}
}
