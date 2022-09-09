// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Statement;
import com.eagle.programmar.Bash.Symbols.Bash_Function_Definition;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Bash_Function extends TokenChooser
{
	public @CHOICE static class Bash_Function_Explicit extends TokenSequence
	{
		public @S(10) @DOC("#index-functions_002c-shell") Bash_Keyword FUNCTION = new Bash_Keyword("function");
		public @S(20) Bash_Function_Definition fnName;
		public @S(30) @OPT Bash_FunctionParams params;
		public @S(40) @OPT Bash_EndOfLine eoln1;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) Bash_EndOfLine eoln2;
		public @S(70) TokenList<Bash_Statement> statements;
		public @S(80) PunctuationRightBrace rightBrace;
		
		public static class Bash_FunctionParams extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PunctuationRightParen rightParen;
		}
	}
	
	public @CHOICE static class Bash_Function_Implicit extends TokenSequence
	{
		public @S(10) Bash_Function_Definition fnName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PunctuationRightParen rightParen;
		public @S(40) PunctuationLeftBrace leftBrace;
		public @S(50) Bash_EndOfLine eoln1;
		public @S(60) TokenList<Bash_Statement> statements;
		public @S(70) PunctuationRightBrace rightBrace;
	}
}
