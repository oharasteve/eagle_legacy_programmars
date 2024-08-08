// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.TCL.Statements.TCL_BlockStatement;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Definition;
import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_Procedure extends TokenSequence implements AbstractFunction, EagleRunnable
{
	public @S(10) @DOC("TclCmd/proc.html") TCL_Keyword PROC = new TCL_Keyword("proc");
	public @S(20) TCL_Function_Definition name;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) TokenList<TCL_Variable_Definition> vars;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) TCL_BlockStatement block;
	
	public @SKIP CallMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, name.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}
}
