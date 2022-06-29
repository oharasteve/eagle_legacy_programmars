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
	public @S(10) @NEWLINE Powershell_Keyword IF = new Powershell_Keyword("if");
	public @S(20) Powershell_Expression condition;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) TokenList<Powershell_Statement> stmts;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) @OPT Powershell_IfElseStatement elseStmt;
	
	public static class Powershell_IfElseStatement extends TokenSequence
	{
		public @S(10) Powershell_Keyword ELSE = new Powershell_Keyword("else");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) TokenList<Powershell_Statement> stmts;
		public @S(40) PunctuationRightBrace rightBrace;
	}
}
