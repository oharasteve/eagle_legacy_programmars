// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Statements.Delphi_Assignment;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Statements.Delphi_Break_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Case_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Close_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_ExpressionStatement;
import com.eagle.programmar.Delphi.Statements.Delphi_For_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_GetDateTime_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Halt_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_If_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Inherited_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Raise_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Readln_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Repeat_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Rewrite_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Try_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_While_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_With_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Writeln_Statement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Statement extends TokenChooser implements AbstractStatement
{
	public @CHOICE PunctuationSemicolon XXsemicolon;

	public @CHOICE Delphi_Assignment XXassignment;
	public @CHOICE Delphi_BeginEnd XXbeginEnd;
	public @CHOICE Delphi_Break_Statement XXbreakStatement;
	public @CHOICE Delphi_Case_Statement XXcaseStatement;
	public @CHOICE Delphi_Close_Statement XXcloseStatement;
	public @CHOICE Delphi_For_Statement XXforStatement;
	public @CHOICE Delphi_GetDateTime_Statement XXgetDateTimeStatement;
	public @CHOICE Delphi_Halt_Statement XXhaltStatement;
	public @CHOICE Delphi_If_Statement XXifStatement;
	public @CHOICE Delphi_Inherited_Statement XXinheritedStatement;
	public @CHOICE Delphi_Raise_Statement XXraiseStatement;
	public @CHOICE Delphi_Readln_Statement XXreadlnStatement;
	public @CHOICE Delphi_Repeat_Statement XXrepeat_Statement;
	public @CHOICE Delphi_Rewrite_Statement XXrewriteStatement;
	public @CHOICE Delphi_Try_Statement XXtryStatement;
	public @CHOICE Delphi_While_Statement XXwhile_Statement;
	public @CHOICE Delphi_With_Statement XXwith_Statement;
	public @CHOICE Delphi_Writeln_Statement XXwritelnStatement;

	// This guy has to be last
	public @LAST Delphi_ExpressionStatement XXexpressionStatement;
}
