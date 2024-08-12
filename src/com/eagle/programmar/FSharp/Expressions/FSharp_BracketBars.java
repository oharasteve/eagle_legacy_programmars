// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class FSharp_BracketBars extends PrimaryOperator implements EagleRunnable
{
	public @S(10) FSharp_Punctuation leftBracketBar = new FSharp_Punctuation("[|");
	public @S(20) @OPT FSharp_EndOfLine eoln;
	public @S(30) @OPT @SYNTAX(FSharp_Multiline_Syntax.class) SeparatedList<FSharp_Expression, PunctuationSemicolon> exprs;
	public @S(40) FSharp_Punctuation rightBarBracket = new FSharp_Punctuation("|]");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray values = new EagleArray();
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			FSharp_Expression expr = exprs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			values.addValue(val);
		}

		interpreter.pushEagleValue(values);
	}
}
