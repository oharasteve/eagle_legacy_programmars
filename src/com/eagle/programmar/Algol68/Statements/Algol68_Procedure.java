// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Syntax;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Procedure_Definition;
import com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
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
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_Procedure extends TokenSequence implements EagleRunnable, AbstractFunction, EagleScopeInterface
{
	public @S(10) Algol68_Keyword PROCEDURE = new Algol68_Keyword("PROC");
	public @S(20) Algol68_Procedure_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) @OPT Algol68_ProcedureParams params;
	public @S(50) @OPT Algol68_ProcedureReturns returns;
	public @S(60) PunctuationLeftParen leftParen;
	public @S(70) TokenList<Algol68_Statement> statements;
	public @S(80) @OPT Algol68_Variable returnValue;
	public @S(90) PunctuationRightParen rightParen;
	public @S(100) PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _metrics = null;

	public static class Algol68_ProcedureParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Algol68_Parameter, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Algol68_Parameter extends TokenSequence
	{
		public @S(10) Algol68_Type type;
		public @S(20) Algol68_Variable_Definition param;
	}

	public static class Algol68_ProcedureReturns extends TokenSequence
	{
		public @S(10) Algol68_Type type;
		public @S(20) PunctuationColon colon;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Algol68_Syntax.IS_CASE_SENSITIVE);

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
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		
		// Nothing to do here -- just defining the procedure
	}
}
