// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_IfStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#83-the-if-statement") Powershell_Keyword IF = new Powershell_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT Powershell_EndOfLine eoln1;
	public @S(70) TokenList<Powershell_Statement> stmts;
	public @S(80) PunctuationRightBrace rightBrace;
	public @S(90) @OPT Powershell_EndOfLine eoln2;
	public @S(100) @OPT TokenList<Powershell_IfElseIfStatement> elseIfStmts;
	public @S(110) @OPT Powershell_IfElseStatement elseStmt;
	
	public static class Powershell_IfElseIfStatement extends TokenSequence
	{
		public @S(10) Powershell_Keyword ELSEIF = new Powershell_Keyword("elseif");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Powershell_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) @OPT Powershell_EndOfLine eoln;
		public @S(70) TokenList<Powershell_Statement> stmts;
		public @S(80) PunctuationRightBrace rightBrace;
		public @S(90) @OPT Powershell_EndOfLine eoln2;
	}
	
	public static class Powershell_IfElseStatement extends TokenSequence
	{
		public @S(10) Powershell_Keyword ELSE = new Powershell_Keyword("else");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) TokenList<Powershell_Statement> stmts;
		public @S(50) PunctuationRightBrace rightBrace;
	}
}
