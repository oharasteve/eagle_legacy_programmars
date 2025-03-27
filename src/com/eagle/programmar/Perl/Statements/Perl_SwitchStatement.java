// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_SwitchStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("control-structures.switch.php") Perl_Keyword SWITCH = new Perl_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_Expression val;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<Perl_CaseClause> caseClause;
	public @S(70) @OPT Perl_DefaultClause elseClause;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class Perl_CaseClause extends TokenSequence
	{
		public @S(10) Perl_Keyword CASE = new Perl_Keyword("case");
		public @S(20) Perl_Expression expr;
		public @S(30) @OPT PunctuationColon colon;
		public @S(40) @OPT TokenList<Perl_StatementOrComment> statements;
	}

	public static class Perl_DefaultClause extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice DEFAULT = new Perl_KeywordChoice("default", "else");
		public @S(20) @OPT PunctuationColon colon;
		public @S(30) TokenList<Perl_StatementOrComment> statements;
	}
}
