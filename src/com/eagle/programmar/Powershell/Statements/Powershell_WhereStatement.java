// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Powershell_WhereStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Powershell_Where where;
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT Powershell_EndOfLine eoln;
	public @S(40) TokenList<Powershell_Element> stmts;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Powershell_Where extends TokenChooser
	{
		public @CHOICE Powershell_Punctuation XXpercent = new Powershell_Punctuation("?");
		public @CHOICE Powershell_KeywordChoice XXWHERE = new Powershell_KeywordChoice("Where", "Where-Object");
	}
}
