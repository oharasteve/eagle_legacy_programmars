// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.generate.Statements.Eagle_Generate_Break;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Python_BreakStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				Eagle_Generate_Break<Python_Statement>
{
	public @S(10) @DOC("simple_stmts.html#the-break-statement") Python_Keyword BREAK = new Python_Keyword("break");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
	
	@Override
	public Python_Statement generateBreak(AbstractToken source)
	{
		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
