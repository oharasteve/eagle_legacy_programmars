// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_OptionStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("statements/option-keyword-statement") VB_Keyword OPTION = new VB_Keyword("option");
	public @S(20) VB_Keyword EXPLICIT = new VB_Keyword("explicit");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Ignore for now
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Nothing worth doing
		return null;
	}
}
