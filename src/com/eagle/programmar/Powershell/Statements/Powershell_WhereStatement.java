// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_WhereStatement extends TokenSequence
{
	public @S(10) Powershell_Where where;
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT Powershell_EndOfLine eoln;
	public @S(40) TokenList<Powershell_Statement> stmts;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Powershell_Where extends TokenChooser
	{
		public @CHOICE Powershell_Punctuation percent = new Powershell_Punctuation("?");
		public @CHOICE Powershell_KeywordChoice WHERE = new Powershell_KeywordChoice("Where", "Where-Object");
	}
}
