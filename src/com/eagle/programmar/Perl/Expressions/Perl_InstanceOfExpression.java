// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_InstanceOfExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression expr = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_Keyword instanceOperator = new Perl_Keyword("instanceof");
	public @S(30) @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
	public @S(40) Perl_Identifier_Reference type;
	public @S(50) @OPT TokenList<Perl_MoreInstanceOf> more;
	
	public static class Perl_MoreInstanceOf extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference type;
	}
}
