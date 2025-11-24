// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPG;

import com.eagle.programmar.RPG.Terminals.RPG_EndOfLine;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class RPG_CTDATA extends TokenSequence
{
	public @S(10) RPG_Keyword CTDATA = new RPG_Keyword(1, 9, "** CTDATA");
	public @S(20) RPG_Literal array = new RPG_Literal(11, 16);
	public @S(30) RPG_EndOfLine eoln;
	public @S(40) TokenList<RPG_CTDATA_item> items;

	public static class RPG_CTDATA_item extends TokenSequence
	{
		public @S(10) RPG_Number number = new RPG_Number(1, 10);
		public @S(20) RPG_EndOfLine eoln;
	}
}
