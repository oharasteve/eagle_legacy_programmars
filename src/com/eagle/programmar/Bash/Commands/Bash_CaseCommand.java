// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 16, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Element;
import com.eagle.programmar.Bash.Terminals.Bash_Identifier;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Bash_CaseCommand extends TokenSequence implements AbstractStatement
{
	public @S(10) Bash_Keyword CASE = new Bash_Keyword("case");
	public @S(20) Bash_Expression expr;
	public @S(30) Bash_Keyword IN = new Bash_Keyword("in");
	public @S(40) Bash_EndOfLine eoln1;
	public @S(50) TokenList<Bash_CaseClause> clauses;
	public @S(60) @OPT Bash_CaseDefault defaultClause;
	public @S(70) Bash_Keyword ESAC = new Bash_Keyword("esac");
	public @S(80) Bash_EndOfLine eoln2;

	public static class Bash_CaseClause extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Bash_Identifier regex;
		public @S(30) PunctuationRightBracket rightBracket;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) Bash_Element stmt;
		public @S(60) PunctuationSemicolon semicolon1;
		public @S(70) @OPT PunctuationSemicolon semicolon2;
		public @S(80) Bash_EndOfLine eoln;
	}

	public static class Bash_CaseDefault extends TokenSequence
	{
		public @S(10) PunctuationStar star;
		public @S(20) PunctuationRightParen rightParen;
		public @S(30) Bash_Element stmt;
		public @S(40) PunctuationSemicolon semicolon1;
		public @S(50) @OPT PunctuationSemicolon semicolon2;
		public @S(60) Bash_EndOfLine eoln;
	}
}
