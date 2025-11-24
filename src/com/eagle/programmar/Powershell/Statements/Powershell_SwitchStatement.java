// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2024

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_SwitchStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Powershell_Keyword SWITCH = new Powershell_Keyword("Switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT Powershell_EndOfLine eoln1;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT Powershell_EndOfLine eoln2;
	public @S(80) @OPT TokenList<Powershell_SwitchCases> cases;
	public @S(90) PunctuationRightBrace rightBrace;
	public @S(100) @OPT Powershell_EndOfLine eoln3;

	public static class Powershell_SwitchCases extends TokenSequence
	{
		public @S(10) Powershell_SwitchCondition switchCondition;
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) @OPT Powershell_EndOfLine eoln1;
		public @S(40) @OPT TokenList<Powershell_Element> stmts;
		public @S(50) PunctuationRightBrace rightBrace;
		public @S(60) @OPT Powershell_EndOfLine eoln2;
	}

	public static class Powershell_SwitchCondition extends TokenChooser
	{
		public @FIRST Powershell_Keyword XXDEFAULT = new Powershell_Keyword("Default");
		public @CHOICE Powershell_Expression XXexpr;
	}
}
