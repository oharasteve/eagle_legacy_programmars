// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_ForEachStatement extends TokenChooser
{
	public @CHOICE static class Perl_ForEachAsStatement extends TokenSequence
	{
		public @S(10) @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression expr;
		public @S(40) Perl_Keyword AS = new Perl_Keyword("as");
		public @S(50) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(60) Perl_Variable_Definition var;
		public @S(70) @OPT Perl_ForEachArrow arrow;
		public @S(80) PunctuationRightParen rightParen;
		public @S(90) Perl_Statement stmt;

		public static class Perl_ForEachArrow extends TokenSequence
		{
			public @S(10) Perl_Punctuation equalsGreater = new Perl_Punctuation("=>");
			public @S(20) Perl_Variable var;
		}
	}

	public @CHOICE static class Perl_ForEachNoAsStatement extends TokenSequence
	{
		public @S(10) @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
		public @S(20) @OPT Perl_ForEachVariable var;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) Perl_Expression expr;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) Perl_Statement stmt;

		public static class Perl_ForEachVariable extends TokenSequence
		{
			public @S(10) @OPT Perl_Keyword MY = new Perl_Keyword("my");
			public @S(20) Perl_Punctuation dollar = new Perl_Punctuation('$');
			public @S(30) Perl_Variable_Definition var;
		}
	}
}
