// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_IncludeStatement extends TokenSequence
{
	public @S(10) @OPT Perl_Punctuation at = new Perl_Punctuation("@");
	public @S(20) @DOC("function.include.php") Perl_KeywordChoice INCLUDE =
			new Perl_KeywordChoice("include", "include_once");
	public @S(30) Perl_Expression filename;
}
