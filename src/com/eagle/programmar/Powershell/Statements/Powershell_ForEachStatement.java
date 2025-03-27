// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_ForEachStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#844-the-foreach-statement") Powershell_ForEach foreach;
	public @S(20) @OPT Powershell_ForEachParams params;
	public @S(30) @OPT Powershell_EndOfLine eoln1;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) @OPT Powershell_EndOfLine eoln2;
	public @S(60) TokenList<Powershell_Statement> stmts;
	public @S(70) PunctuationRightBrace rightBrace;

	public static class Powershell_ForEach extends TokenChooser
	{
		public @CHOICE Powershell_Punctuation XXpercent = new Powershell_Punctuation("%");
		public @CHOICE Powershell_KeywordChoice XXFOREACH = new Powershell_KeywordChoice("ForEach", "ForEach-Object");
	}

	public static class Powershell_ForEachParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Powershell_Variable var;
		public @S(30) Powershell_Keyword IN = new Powershell_Keyword("in");
		public @S(40) Powershell_Expression expr;
		public @S(50) PunctuationRightParen rightParen;
	}
}
