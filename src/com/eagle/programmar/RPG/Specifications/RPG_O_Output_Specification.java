// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_O_Output_Specification extends TokenSequence
{
	public @S(10) RPG_O_Output_Spec spec;

	public static class RPG_O_Output_Spec extends TokenChooser
	{
		public @CHOICE RPG_O_Output_Program_Record_Id recordId;
		public @CHOICE RPG_O_Output_Program_Field_Descr fieldDescr;
		public @CHOICE RPG_O_Output_External_Record_Id externalRecordId;
		public @CHOICE RPG_O_Output_External_Field_Descr externalFieldDescr;
	}

	public abstract static class RPG_O_Output_Program_Record_Id extends TokenSequence
	{
		public @S(10) RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public @S(20) RPG_O_Output_Program_Record_Id_Piece piece;
		public @S(30) @OPT RPG_Literal skipBefore;
		public @S(40) @OPT RPG_Literal skipAfter;
		public @S(50) @OPT RPG_Literal indicators;
		public @S(60) @OPT RPG_Literal exceptName;
		public @S(70) RPG_Blanks blank1;
	}

	public abstract static class RPG_O_Output_Program_Field_Descr extends TokenSequence
	{
		public @S(10) RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public @S(20) RPG_Blanks blank1;
		public @S(30) @OPT RPG_Literal indicators;
		public @S(40) RPG_Literal fieldName;
		public @S(50) @OPT RPG_Literal editCodes;
		public @S(60) @OPT RPG_Keyword blankAfter;
		public @S(70) @OPT RPG_Literal endPosition;
		public @S(80) @OPT RPG_KeywordChoice dataFormat;
		public @S(90) @OPT RPG_Literal constantOrEditWord;
		public @S(100) RPG_Blanks blank2;
	}

	public abstract static class RPG_O_Output_External_Record_Id extends TokenSequence
	{
		public @S(10) RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public @S(20) RPG_Literal recordName;
		public @S(30) RPG_O_Output_External_Record_Id_Piece piece;
		public @S(40) @OPT RPG_Literal indicators;
		public @S(50) @OPT RPG_Literal exceptName;
		public @S(60) RPG_Blanks blank1;
	}

	public abstract static class RPG_O_Output_External_Field_Descr extends TokenSequence
	{
		public @S(10) RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public @S(20) RPG_Blanks blank1;
		public @S(30) @OPT RPG_Literal indicators;
		public @S(40) RPG_Literal fieldName;
		public @S(50) RPG_Blanks blank2;
		public @S(60) @OPT RPG_Keyword blankAfter;
		public @S(70) RPG_Blanks blank3;
	}
}
