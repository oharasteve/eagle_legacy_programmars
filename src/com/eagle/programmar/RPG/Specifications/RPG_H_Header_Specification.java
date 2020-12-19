// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_H_Header_Specification extends TokenSequence
{
	public static class RPG_H_Header_Specification_III extends RPG_H_Header_Specification
	{
		public @S(10) RPG_Keyword H = new RPG_Keyword(6, 6, "H");
		public @S(20) @OPT RPG_Keyword debug = new RPG_Keyword(15, 15, "1");
		public @S(30) @OPT RPG_Literal currency = new RPG_Literal(18, 18);
		public @S(40) @OPT RPG_KeywordChoice dateFormat = new RPG_KeywordChoice(19, 19, "M", "D", "Y");
		public @S(50) @OPT RPG_Literal dateEditCode = new RPG_Literal(20, 20);
		public @S(60) @OPT RPG_KeywordChoice decimalNotation = new RPG_KeywordChoice(21, 21, "I", "J", "D");
		public @S(70) @OPT RPG_Keyword collcatingSequence = new RPG_Keyword(26, 26, "S");
		public @S(80) @OPT RPG_Keyword formsAlignment = new RPG_Keyword(41, 41, "1");
		public @S(90) @OPT RPG_Keyword fileTranslation = new RPG_Keyword(43, 43, "F");
		public @S(100) @OPT RPG_Keyword transparencyCheck = new RPG_Keyword(57, 57, "1");
		public @S(110) @OPT RPG_Literal programIdentification = new RPG_Literal(75, 80);
	}
	
	public static class RPG_H_Header_Specification_IV extends RPG_H_Header_Specification
	{
		public @S(10) RPG_Keyword H = new RPG_Keyword(6, 6, "H");
		public @S(20) @OPT RPG_Literal keywords = new RPG_Literal(7, 80);
	}
}
