// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Rexx.Rexx_Statement;
import com.eagle.programmar.Rexx.Rexx_Syntax;
import com.eagle.programmar.Rexx.Symbols.Rexx_Function_Definition;
import com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Rexx_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) Rexx_Function_Definition name;
	public @S(20) PunctuationColon colon;
	public @S(30) Rexx_EndOfLine eoln;
	public @S(40) @OPT Rexx_Parameters params;
	public @S(50) TokenList<Rexx_Statement> stmts;
	
	private @SKIP EagleScope _scope = new EagleScope(this, Rexx_Syntax.IS_CASE_SENSITIVE);

	public static class Rexx_Parameters extends TokenSequence
	{
		public @S(10) Rexx_Keyword PARSE = new Rexx_Keyword("PARSE");
		public @S(20) Rexx_Keyword ARG = new Rexx_Keyword("ARG");
		public @S(30) SeparatedList<Rexx_Variable_Definition, PunctuationComma> params;
		public @S(40) Rexx_EndOfLine eoln;
	}
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
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
