// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public class RPG_L_LineCounter_Specification extends TokenSequence
{
	public @S(10) RPG_Keyword L = new RPG_Keyword(6, 6, "L");
	public @S(20) RPG_Literal fileName = new RPG_Literal(7, 14);
	public @S(30) RPG_Number linesPerPage = new RPG_Number(15, 17);
	public @S(40) @OPT RPG_Keyword FL = new RPG_Keyword(18, 19, "FL");
	public @S(50) RPG_Number overflowLineNumber = new RPG_Number(20, 22);
	public @S(60) @OPT RPG_Keyword OL = new RPG_Keyword(23, 24, "OL");
	public @S(70) RPG_Blanks blank1 = new RPG_Blanks(25, 74);
}
