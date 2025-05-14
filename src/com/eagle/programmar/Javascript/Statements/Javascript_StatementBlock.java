// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Javascript.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_Label;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_StatementBlock extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @OPT Javascript_Label label;
	public @S(20) @INDENT PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Javascript_StatementOrComment> statements;
	public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon1;
	public @S(50) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Javascript_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
}
