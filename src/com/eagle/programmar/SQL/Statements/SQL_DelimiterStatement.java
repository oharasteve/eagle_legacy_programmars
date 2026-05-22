// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_DelimiterStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) SQL_Keyword DELIMITER = new SQL_Keyword("DELIMITER");
	public @S(20) SQL_PunctuationChoice what = new SQL_PunctuationChoice(";", "//");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Need some sort of State variable to store the delimiter
		// And it needs to be processed DURING the Parse phase.
		// We don't have anything like this, yet.
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return null; // Ignore for now
	}
}
