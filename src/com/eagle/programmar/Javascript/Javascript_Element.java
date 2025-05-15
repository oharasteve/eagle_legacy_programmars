// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Javascript.Symbols.Javascript_Label_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Javascript_Element extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @OPT Javascript_Label label;
	public @S(20) Javascript_Statement statement;

	public static class Javascript_Label extends TokenSequence
	{
		public @S(10) Javascript_Label_Definition label;
		public @S(20) PunctuationColon colon;
	}

	public static class Javascript_StatementOrComment extends TokenChooser
	{
		public @CHOICE Javascript_Element XXstatement;
		public @CHOICE Javascript_Comment XXcomment;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return interpreter.tryToInterpret(statement);
	}
}
