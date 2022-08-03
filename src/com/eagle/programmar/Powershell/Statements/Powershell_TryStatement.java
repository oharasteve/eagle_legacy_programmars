// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_TryStatement extends TokenSequence
{
	public @S(10) Powershell_Keyword TRY = new Powershell_Keyword("Try");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) Powershell_EndOfLine eoln;
	public @S(40) @OPT TokenList<Powershell_Statement> statements;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) @OPT TokenList<Powershell_CatchBlock> catchBlocks;
	public @S(70) @OPT Powershell_FinallyBlock finallyBlock;
	
	public static class Powershell_CatchBlock extends TokenSequence
	{
		public @S(10) Powershell_Keyword CATCH = new Powershell_Keyword("Catch");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) Powershell_EndOfLine eoln;
		public @S(40) @OPT TokenList<Powershell_Statement> statements;
		public @S(50) PunctuationRightBrace rightBrace;
	}
	
	public static class Powershell_FinallyBlock extends TokenSequence
	{
		public @S(10) Powershell_Keyword FINALLY = new Powershell_Keyword("Finally");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) Powershell_EndOfLine eoln;
		public @S(40) @OPT TokenList<Powershell_Statement> statements;
		public @S(50) PunctuationRightBrace rightBrace;
	}
}
