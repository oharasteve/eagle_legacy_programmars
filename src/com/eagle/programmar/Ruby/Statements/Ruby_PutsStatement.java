// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ruby.Ruby_Format;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ruby_PutsStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ruby_Keyword PUTS = new Ruby_Keyword("puts");
	public @S(20) Ruby_Literal format;
	public @S(30) Ruby_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String formatted = Ruby_Format.format(interpreter, format.getValue());
		System.out.println(formatted);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression newLine = Ruby_Format.compile(generator, format.getValue(), this);
		return generator.newPrintStatement(newLine, true, this);
	}
}
