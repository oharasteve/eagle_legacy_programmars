// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Rust.Rust_Statement.Rust_Block_Statement;
import com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
import com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_Function extends TokenSequence implements EagleRunnable, AbstractFunction, EagleScopeInterface
{
	public @S(10) @OPT Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) @DOC("items/functions.html") Rust_Keyword FN = new Rust_Keyword("fn");
	public @S(30) Rust_Function_Definition id;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT SeparatedList<Rust_Parameter, PunctuationComma> funcParamDefs;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT Rust_FunctionReturns returns;
	public @S(80) Rust_Block_Statement stmt;

	public static class Rust_FunctionReturns extends TokenSequence
	{
		public @S(10) Rust_Punctuation arrow = new Rust_Punctuation("->");
		public @S(20) Rust_Type returnType;
	}

	public static class Rust_Parameter extends TokenSequence
	{
		public @S(10) Rust_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) Rust_Type type;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);

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
			_metrics = new CallMetrics(interpreter._metrics, id.getValue(), this);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called

		// Except the function called 'main'
		if (id.getValue().equals("main"))
		{
			interpreter.callingFunction("main", this);
			interpreter.tryToInterpret(stmt);
			interpreter.completedFunction("main", this);
		}
	}
}
