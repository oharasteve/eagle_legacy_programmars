// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_ForEachStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#844-the-foreach-statement") Powershell_Keyword FOREACH = new Powershell_Keyword("foreach");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Variable var;
	public @S(40) Powershell_Keyword IN = new Powershell_Keyword("in");
	public @S(50) Powershell_Expression expr;
	public @S(60) PunctuationRightParen rightParen;
	
	public @S(70) PunctuationLeftBrace leftBrace;
	public @S(80) @OPT Powershell_EndOfLine eoln;
	public @S(90) TokenList<Powershell_Statement> stmts;
	public @S(100) PunctuationRightBrace rightBrace;
}
