// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 23, 2024

package com.eagle.programmar.Perl.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Perl_StatementBlock extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Perl_StatementOrComment> statements;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Perl_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt.getWhich());
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}
