// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Bash.Bash_Element;
import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Syntax;
import com.eagle.programmar.Bash.Symbols.Bash_Function_Definition;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Bash_Function extends TokenChooser
{
	public @CHOICE static class Bash_Function_Explicit extends TokenSequence
				implements AbstractFunction, EagleRunnable, EagleScopeInterface
	{
		public @S(10) @DOC("#index-functions_002c-shell") Bash_Keyword FUNCTION = new Bash_Keyword("function");
		public @S(20) Bash_Function_Definition id;
		public @S(30) @OPT Bash_FunctionParams params;
		public @S(40) @OPT Bash_EndOfLine eoln1;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) Bash_EndOfLine eoln2;
		public @S(70) TokenList<Bash_Element> statements;
		public @S(80) PunctuationRightBrace rightBrace;

		public static class Bash_FunctionParams extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PunctuationRightParen rightParen;
		}
		
		public @SKIP CallMetrics _callMetrics = null;
		public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		
		// Bash has a strange way of returning values
		public @SKIP int _exitStatus = 0;
		public @SKIP String _echoOutputs = null;

		private @SKIP EagleScope _scope = new EagleScope(this, Bash_Syntax.IS_CASE_SENSITIVE);

		@Override
		public EagleScope getScope()
		{
			return _scope;
		}
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			if (_callMetrics == null)
			{
				_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
			}
			if (_argumentsMetrics == null)
			{
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
			}
		}
	}

	public @CHOICE static class Bash_Function_Implicit extends TokenSequence
	{
		public @S(10) Bash_Function_Definition fnName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) PunctuationLeftBrace leftBrace;
		public @S(50) Bash_EndOfLine eoln1;
		public @S(60) TokenList<Bash_Element> statements;
		public @S(70) PunctuationRightBrace rightBrace;
	}
}
