// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Symbols.Go_Identifier;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_Package extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("#Package_clause") Go_Keyword PACKAGE = new Go_Keyword("package");
	public @S(20) Go_Identifier id;
	public @S(30) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		return null; // Nothing to do here
	}
}
