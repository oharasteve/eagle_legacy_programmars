// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_StopStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpsstop.htm") COBOL_Keyword STOP = new COBOL_Keyword("STOP");
	public @S(20) COBOL_Keyword RUN = new COBOL_Keyword("RUN");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Not sure what to do here, if anything
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// STOP RUN doesn't give us a return code to use
		AbstractExpression zero = generator.newNumberExpression("0", null);
		return generator.newExitStatement(zero, null);
	}
}
