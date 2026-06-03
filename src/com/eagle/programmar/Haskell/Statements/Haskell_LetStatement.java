// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 18, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Haskell_LetStatement extends TokenSequence
{
	public @S(10) Haskell_Keyword LET = new Haskell_Keyword("let");
	public @S(20) Haskell_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Haskell_Expression expr;
	public @S(50) @OPT @PYDENT Haskell_LetBlock block;
	public @S(60) @OPT Haskell_EndOfLine eoln;
	
	public static class Haskell_LetBlock extends TokenSequence
	{
		public @S(10) @OPT Haskell_EndOfLine eoln;
		public @S(20) TokenList<Haskell_LetMore> more;
	}
	
	public static class Haskell_LetMore extends TokenSequence
	{
		public @S(10) Haskell_Variable var;
		public @S(20) PunctuationEquals equals;
		public @S(30) Haskell_Expression expr;
		public @S(40) Haskell_EndOfLine eoln;
	}
}
