// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_Statement;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenSequence;

public class Haskell_IfStatement extends TokenSequence
{
	public @S(10) Haskell_Keyword IF = new Haskell_Keyword("if");
	public @S(20) Haskell_Expression condition;
	public @S(30) Haskell_EndOfLine eoln;
	public @S(40) Haskell_Keyword THEN = new Haskell_Keyword("then");
	public @S(50) Haskell_Statement thenStatement;
	public @S(60) Haskell_Keyword ELSE = new Haskell_Keyword("else");
	public @S(70) Haskell_Statement elseStatement;
}
