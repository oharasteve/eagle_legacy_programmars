// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_Syntax;
import com.eagle.programmar.Powershell.Powershell_Type;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Definition;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_FunctionStatement extends TokenSequence implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#810-function-definitions") Powershell_Keyword FUNCTION = new Powershell_Keyword(
			"Function");
	public @S(20) Powershell_Function_Definition name;
	public @S(30) @OPT Powershell_FunctionParams params;
	public @S(40) @OPT Powershell_EndOfLine eoln1;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT Powershell_EndOfLine eoln2;
	public @S(70) @OPT TokenList<Powershell_Element> stmts;
	public @S(80) PunctuationRightBrace rightBrace;

	public @SKIP CallMetrics _metrics = null;

	public static class Powershell_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Powershell_FunctionParam, PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Powershell_FunctionParam extends TokenSequence
	{
		public @S(10) @OPT Powershell_CastParameter cast;
		public @S(20) Powershell_Variable var;

		public static class Powershell_CastParameter extends PrimaryOperator
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Powershell_Type type;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Powershell_Syntax.IS_CASE_SENSITIVE);

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
			_metrics = new CallMetrics(interpreter._metrics, name.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the function in a preliminary pass
		// And we only evaluate when it is called
	}

}
