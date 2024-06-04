// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.AWK.Statements.AWK_Assignment;
import com.eagle.programmar.AWK.Statements.AWK_BreakStatement;
import com.eagle.programmar.AWK.Statements.AWK_ContinueStatement;
import com.eagle.programmar.AWK.Statements.AWK_ExitStatement;
import com.eagle.programmar.AWK.Statements.AWK_ForStatement;
import com.eagle.programmar.AWK.Statements.AWK_IfStatement;
import com.eagle.programmar.AWK.Statements.AWK_NextStatement;
import com.eagle.programmar.AWK.Statements.AWK_PrintStatement;
import com.eagle.programmar.AWK.Statements.AWK_ReturnStatement;
import com.eagle.programmar.AWK.Statements.AWK_SplitStatement;
import com.eagle.programmar.AWK.Statements.AWK_SubStatement;
import com.eagle.programmar.AWK.Statements.AWK_SwitchStatement;
import com.eagle.programmar.AWK.Statements.AWK_WhileStatement;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class AWK_Statements extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) SeparatedList<AWK_Statement, PunctuationSemicolon> statements;
	public @S(20) @OPT PunctuationSemicolon semicolon;
	public @S(30) @OPT TokenList<AWK_Comment> comments;
	public @S(40) @OPT AWK_EndOfLine endOfLine;

	public static class AWK_Statement extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon semicolon; // Empty statement
		public @CHOICE AWK_Comment comment;

		public @CHOICE AWK_Assignment assignmentStatement;
		public @CHOICE AWK_BreakStatement breakStatement;
		public @CHOICE AWK_ContinueStatement continueStatement;
		public @CHOICE AWK_ExitStatement exitStatement;
		public @CHOICE AWK_ForStatement forStatement;
		public @CHOICE AWK_IfStatement ifStatement;
		public @CHOICE AWK_NextStatement nextStatement;
		public @CHOICE AWK_PrintStatement printStatement;
		public @CHOICE AWK_ReturnStatement returnStatement;
		public @CHOICE AWK_SplitStatement splitStatement;
		public @CHOICE AWK_SubStatement subStatement;
		public @CHOICE AWK_SwitchStatement switchStatement;
		public @CHOICE AWK_WhileStatement whileStatement;

		public @LAST AWK_Expression expressionStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = 0; i < statements.getPrimaryCount(); i++)
		{
			result = interpreter.tryToInterpret(statements.getPrimaryElement(i).getWhich());
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
}
