// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Definition;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_FunctionStatement extends TokenSequence
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#810-function-definitions") Powershell_Keyword FUNCTION = new Powershell_Keyword("Function");
	public @S(20) Powershell_Function_Definition name;
	public @S(30) @OPT Powershell_FunctionParams params;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) @OPT Powershell_EndOfLine eoln;
	public @S(60) @OPT TokenList<Powershell_Statement> stmts;
	public @S(70) PunctuationRightBrace rightBrace;
	
	public static class Powershell_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Powershell_Variable, PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}
}
