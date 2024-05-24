// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Symbols.Perl_Use_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_UseStatement extends TokenSequence
{
	public @S(10) Perl_Keyword USE = new Perl_Keyword("use");
	public @S(20) Perl_UseWhat what;
	public @S(30) @OPT Perl_UseAs as;

	public static class Perl_UseWhat extends TokenChooser
	{
		public @FIRST static class Perl_UseRemote extends TokenSequence
		{
			public @S(10) Perl_KeywordChoice NET = new Perl_KeywordChoice("File", "Getopt", "IPC", "List", "MIME",
					"Net", "Time", "Win32");
			public @S(20) @OPT Perl_Punctuation colonColon = new Perl_Punctuation("::");
			public @S(30) @OPT Perl_Identifier_Reference id;
		}

		public @CHOICE static class Perl_UseLocal extends TokenSequence
		{
			public @S(10) @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public @S(20) Perl_Identifier_Reference id;
			public @S(30) @OPT TokenList<Perl_MoreUse> more;

			public static class Perl_MoreUse extends TokenSequence
			{
				public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
				public @S(20) Perl_Identifier_Reference id;
			}
		}
	}

	public static class Perl_UseAs extends TokenSequence
	{
		public @S(10) Perl_Keyword AS = new Perl_Keyword("as");
		public @S(20) Perl_Use_Definition id;
	}
}
