// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.programmar.PHP;

import com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.programmar.PHP.PHP_Program.PHP_StartTag;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

// <?php stmts; if(cond) { ?> xxx <?php } else { ?> xxx <?php } ?>

public class PHP_IfBlock extends TokenSequence
{
	public @S(10) @SYNTAX(Perl_Syntax.class) PHP_IfCondition condition;
	public @S(20) TokenList<PHP_Entry> ifPart;
	public @S(30) @SYNTAX(Perl_Syntax.class) PHP_IfElse elseBlock;
	public @S(40) TokenList<PHP_Entry> elsePart;
	public @S(50) @SYNTAX(Perl_Syntax.class) PHP_EndIf endIf;

	public static class PHP_IfCondition extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) TokenList<Perl_Statement> statements;
		public @S(30) Perl_Keyword IF = new Perl_Keyword("if");
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) Perl_Expression condition;
		public @S(60) PunctuationRightParen rightParen;
		public @S(70) PunctuationLeftBrace leftBrace;
		public @S(80) PHP_EndTag endTag;
	}

	public static class PHP_IfElse extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) PunctuationRightBrace rightBrace;
		public @S(30) Perl_Keyword ELSE = new Perl_Keyword("else");
		public @S(40) PunctuationLeftBrace leftBrace;
		public @S(50) PHP_EndTag endTag;
	}

	public static class PHP_EndIf extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) PunctuationRightBrace rightBrace;
		public @S(30) PHP_EndTag endTag;
	}
}
