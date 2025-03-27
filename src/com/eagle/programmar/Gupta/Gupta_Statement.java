// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Statements.Gupta_Call_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Comment_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_If_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Return_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Set_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_While_Statement;
import com.eagle.tokens.TokenChooser;

public class Gupta_Statement extends TokenChooser
{
	public @CHOICE Gupta_Call_Statement XXcallStatement;
	public @CHOICE Gupta_Comment_Statement XXcommentStatement;
	public @CHOICE Gupta_If_Statement XXifStatement;
	public @CHOICE Gupta_Return_Statement XXreturnStatement;
	public @CHOICE Gupta_Set_Statement XXsetStatement;
	public @CHOICE Gupta_While_Statement XXwhileStatement;
}
