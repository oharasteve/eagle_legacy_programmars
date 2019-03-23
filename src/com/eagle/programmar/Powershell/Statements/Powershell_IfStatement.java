// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_IfStatement extends TokenSequence
{
	public @NEWLINE Powershell_Keyword IF = new Powershell_Keyword("if");
	public Powershell_Expression condition;
	public PunctuationLeftBrace leftBrace;
	public @OPT TokenList<Powershell_Statement> stmts;
	public PunctuationRightBrace rightBrace;
}
