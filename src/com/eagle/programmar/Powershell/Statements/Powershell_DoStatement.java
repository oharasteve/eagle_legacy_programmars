// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 18, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_DoStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#842-the-do-statement") Powershell_Keyword DO = new Powershell_Keyword("Do");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT Powershell_EndOfLine eoln1;
	public @S(40) TokenList<Powershell_Statement> stmts;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) @OPT Powershell_EndOfLine eoln2;
	public @S(70) Powershell_KeywordChoice WHILE = new Powershell_KeywordChoice("Until", "While");
	public @S(80) Powershell_Expression condition;
}
