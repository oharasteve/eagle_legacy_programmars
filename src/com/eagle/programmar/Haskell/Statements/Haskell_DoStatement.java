// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Syntax;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenSequence;

public class Haskell_DoStatement extends TokenSequence
		implements EagleRunnable, EagleScopeInterface
{
	public @S(10) Haskell_Keyword DO = new Haskell_Keyword("do");
	public @S(20) Haskell_EndOfLine eoln;
	public @S(30) @PYDENT Haskell_StatementBlock doBlock;

	private @SKIP EagleScope _scope = new EagleScope(this, Haskell_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(doBlock);
	}
}
