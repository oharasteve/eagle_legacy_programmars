// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement;
import com.eagle.programmar.Haskell.Haskell_Syntax;
import com.eagle.programmar.Haskell.Haskell_Type;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Haskell_MainFunction extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface
{
	public @S(10) @OPT Haskell_MainPrototype prototype;
	public @S(20) Haskell_Keyword MAIN = new Haskell_Keyword("main");
	public @S(30) PunctuationEquals equals;
	public @S(40) Haskell_ComplexStatement statement;

	public static class Haskell_MainPrototype extends TokenSequence
	{
		public @S(10) Haskell_Keyword MAIN = new Haskell_Keyword("main");
		public @S(20) Haskell_Punctuation colonColon = new Haskell_Punctuation("::");
		public @S(30) Haskell_Keyword IO = new Haskell_Keyword("IO");
		public @S(40) Haskell_Type type;
		public @S(50) Haskell_EndOfLine eoln;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Haskell_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.callingFunction("main", this);
		interpreter.tryToInterpret(statement);
		interpreter.completedFunction("main", this);
	}
}
