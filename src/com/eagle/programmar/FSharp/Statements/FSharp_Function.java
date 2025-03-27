// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.FSharp.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Syntax;
import com.eagle.programmar.FSharp.FSharp_Type;
import com.eagle.programmar.FSharp.Symbols.FSharp_Function_Definition;
import com.eagle.programmar.FSharp.Symbols.FSharp_Variable_Definition;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class FSharp_Function extends TokenSequence implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @DOC("functions/") FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) FSharp_Function_Definition id;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<FSharp_FunctionParam, PunctuationComma> params;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) PunctuationEquals equals;
	public @S(70) FSharp_EndOfLine eoln;
	public @S(80) TokenList<FSharp_Statement> statements;

	public static class FSharp_FunctionParam extends TokenSequence
	{
		public @S(10) FSharp_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) FSharp_Type type;
	}
	
	public @SKIP CallMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, FSharp_Syntax.IS_CASE_SENSITIVE);

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
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}
}
