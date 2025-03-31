// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.SQL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class SQL_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT SQL_Punctuation AT = new SQL_Punctuation("@");
	public @S(20) SeparatedList<SQL_Identifier_Reference, PunctuationPeriod> ids;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		SQL_Identifier_Reference id = ids.first();
		EagleValue value = interpreter.findSymbol(id.toString());
		if (value == null)
		{
			throw new RuntimeException("Unable to find a value for " + id.toString());
		}
		interpreter.pushEagleValue(value);
	}
}
