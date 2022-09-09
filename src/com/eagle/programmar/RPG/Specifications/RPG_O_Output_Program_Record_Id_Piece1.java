// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public class RPG_O_Output_Program_Record_Id_Piece1 extends TokenSequence
{
	public @S(10) RPG_Literal recordName;
	public @S(20) RPG_KeywordChoice type;
	public @S(30) @OPT RPG_KeywordChoice overflowRelease;
	public @S(40) @OPT RPG_Number spaceBefore;
	public @S(50) @OPT RPG_Number spaceAfter;
	public @S(60) @OPT RPG_Literal skipBefore;
	public @S(70) @OPT RPG_Literal skipAfter;
}