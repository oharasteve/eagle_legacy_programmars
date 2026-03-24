// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 16, 2024

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_BeginBlock extends TokenSequence implements AbstractStatement
{
	public @S(10) Powershell_Keyword BEGIN = new Powershell_Keyword("Begin");
	public @S(20) @OPT Powershell_EndOfLine eoln1;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Powershell_EndOfLine eoln2;
	public @S(50) TokenList<Powershell_Element> stmts;
	public @S(60) PunctuationRightBrace rightBrace;
}
