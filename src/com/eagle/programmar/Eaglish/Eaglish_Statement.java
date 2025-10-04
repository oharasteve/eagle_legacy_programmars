// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.programmar.Eaglish.Statements.Eaglish_Add_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Array_Data;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Break_For;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Call_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_For_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Function;
import com.eagle.programmar.Eaglish.Statements.Eaglish_If_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Integer_Data;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Print_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Return_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Set_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_String_Data;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Subtract_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_While_Block;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln;
import com.eagle.tokens.TokenChooser;

public class Eaglish_Statement extends TokenChooser
{
	public @CHOICE Eaglish_Add_Statement XXaddStatement;
	public @CHOICE Eaglish_Array_Data XXarrayStatement;
	public @CHOICE Eaglish_Break_For XXbreakFor;
	public @CHOICE Eaglish_Call_Statement XXcalLStatemen;
	public @CHOICE Eaglish_CommentEoln XXcomment;
	public @CHOICE Eaglish_For_Block XXforBlock;
	public @CHOICE Eaglish_Function XXfunctionBlock;
	public @CHOICE Eaglish_If_Block XXifBlock;
	public @CHOICE Eaglish_Integer_Data XXintegerData;
	public @CHOICE Eaglish_Print_Statement XXprintStatement;
	public @CHOICE Eaglish_Return_Statement XXreturnStatement;
	public @CHOICE Eaglish_Set_Statement XXsetStatement;
	public @CHOICE Eaglish_String_Data XXstringData;
	public @CHOICE Eaglish_Subtract_Statement XXsubtractStatement;
	public @CHOICE Eaglish_While_Block XXwhileBlock;
}
