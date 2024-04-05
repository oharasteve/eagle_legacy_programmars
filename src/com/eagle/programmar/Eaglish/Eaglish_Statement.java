// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Add_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Array_Data;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Break_For;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Call_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_For_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Function_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_If_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Integer_Data;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Main_Block;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Print_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Return_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Set_Statement;
import com.eagle.programmar.Eaglish.Statements.Eaglish_String_Data;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln;
import com.eagle.tokens.TokenChooser;

public class Eaglish_Statement extends TokenChooser implements EagleRunnable
{
	public @CHOICE Eaglish_Add_Statement addStatement;
	public @CHOICE Eaglish_Array_Data arrayStatement;
	public @CHOICE Eaglish_Break_For breakFor;
	public @CHOICE Eaglish_Call_Statement calLStatemen;
	public @CHOICE Eaglish_CommentEoln comment;
	public @CHOICE Eaglish_For_Block forBlock;
	public @CHOICE Eaglish_Function_Block functionBlock;
	public @CHOICE Eaglish_If_Block ifBlock;
	public @CHOICE Eaglish_Integer_Data integerData;
	public @CHOICE Eaglish_Main_Block mainBlock;
	public @CHOICE Eaglish_Print_Statement printStatement;
	public @CHOICE Eaglish_Return_Statement returnStatement;
	public @CHOICE Eaglish_Set_Statement setStatement;
	public @CHOICE Eaglish_String_Data stringData;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(this.getWhich());
	}
}
