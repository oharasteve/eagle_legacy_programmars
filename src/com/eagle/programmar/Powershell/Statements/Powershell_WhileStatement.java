// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 18, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_WhileStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#841-the-while-statement") Powershell_Keyword WHILE = new Powershell_Keyword(
			"While");
	public @S(20) Powershell_Expression condition;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Powershell_EndOfLine eoln;
	public @S(50) TokenList<Powershell_Statement> stmts;
	public @S(60) PunctuationRightBrace rightBrace;
}
