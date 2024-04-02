// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Perl_FileIO extends PrimaryOperator
{
	public @S(10) Perl_Punctuation lessThan = new Perl_Punctuation('<');
	public @S(20) @OPT Perl_Identifier_Reference channel;
	public @S(30) Perl_Punctuation greaterThan = new Perl_Punctuation('>');
}
