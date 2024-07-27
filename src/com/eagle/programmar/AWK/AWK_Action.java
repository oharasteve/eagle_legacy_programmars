// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class AWK_Action extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT AWK_EndOfLine eoln1;
	public @S(30) @OPT TokenList<AWK_StatementOrComment> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT AWK_EndOfLine eoln2;

	public static class AWK_StatementOrComment extends TokenChooser
	{
		public @CHOICE AWK_Statements XXstatements;
		public @CHOICE AWK_Comment XXcomment;
		public @CHOICE AWK_Action XXaction;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (AWK_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}