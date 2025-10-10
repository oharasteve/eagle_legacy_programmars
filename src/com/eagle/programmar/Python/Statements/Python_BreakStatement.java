// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Python_BreakStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				EagleTransformableStatement
{
	public @S(10) @DOC("simple_stmts.html#the-break-statement") Python_Keyword BREAK = new Python_Keyword("break");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newBreakStatement(BREAK);
	}
	
	public Python_ComplexStatement generateBreak(AbstractToken source)
	{
		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
