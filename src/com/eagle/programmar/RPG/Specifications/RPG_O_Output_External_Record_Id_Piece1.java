// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.tokens.TokenSequence;

public class RPG_O_Output_External_Record_Id_Piece1 extends TokenSequence
{
	public @S(10) RPG_Literal filename;
	public @S(20) RPG_KeywordChoice type;
	public @S(30) RPG_Keyword release;
	public @S(40) RPG_Blanks blank2;
}