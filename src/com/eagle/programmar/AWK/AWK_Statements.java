// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.AWK.Statements.AWK_Assignment;
import com.eagle.programmar.AWK.Statements.AWK_BreakStatement;
import com.eagle.programmar.AWK.Statements.AWK_ContinueStatement;
import com.eagle.programmar.AWK.Statements.AWK_ExitStatement;
import com.eagle.programmar.AWK.Statements.AWK_ExpressionStatement;
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
		public @CHOICE PunctuationSemicolon XXsemicolon; // Empty statement
		public @CHOICE AWK_Comment XXcomment;

		public @CHOICE AWK_Assignment XXassignmentStatement;
		public @CHOICE AWK_BreakStatement XXbreakStatement;
		public @CHOICE AWK_ContinueStatement XXcontinueStatement;
		public @CHOICE AWK_ExitStatement XXexitStatement;
		public @CHOICE AWK_ForStatement XXforStatement;
		public @CHOICE AWK_IfStatement XXifStatement;
		public @CHOICE AWK_NextStatement XXnextStatement;
		public @CHOICE AWK_PrintStatement XXprintStatement;
		public @CHOICE AWK_ReturnStatement XXreturnStatement;
		public @CHOICE AWK_SplitStatement XXsplitStatement;
		public @CHOICE AWK_SubStatement XXsubStatement;
		public @CHOICE AWK_SwitchStatement XXswitchStatement;
		public @CHOICE AWK_WhileStatement XXwhileStatement;

		public @LAST AWK_ExpressionStatement XXexpressionStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = 0; i < statements.getPrimaryCount(); i++)
		{
			result = interpreter.tryToInterpret(statements.getPrimaryElement(i));
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
}
