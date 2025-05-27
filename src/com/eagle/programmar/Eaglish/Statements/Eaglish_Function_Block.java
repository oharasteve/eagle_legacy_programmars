// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Eaglish_Syntax;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Function_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Function_Block extends TokenSequence implements EagleRunnable, AbstractFunction, EagleScopeInterface
{
	public @S(10) Eaglish_Keyword FUNCTION = new Eaglish_Keyword("FUNCTION");
	public @S(20) Eaglish_Function_Definition var;
	public @S(30) Eaglish_EndOfLine eoln1;

	public @S(40) @OPT TokenList<Eaglish_Parameter_Statement> parameterStatements;
	public @S(50) @OPT Eaglish_Returns_Statement returnsStatement;
	public @S(60) @OPT TokenList<Eaglish_Statement> statements;

	public @S(70) Eaglish_Keyword END_FUNCTION = new Eaglish_Keyword("END_FUNCTION");
	public @S(80) Eaglish_EndOfLine eoln2;

	public @SKIP CallMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Eaglish_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new CallMetrics(interpreter._metrics, var.getValue(), var);
		}

		// Don't do anything here.
		// We searched for all the function in a preliminary pass
		// And we only evaluate when it is called
	}
}
