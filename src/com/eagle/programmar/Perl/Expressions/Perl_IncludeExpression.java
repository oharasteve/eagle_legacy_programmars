// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: include/require as an expression, as in
// if ( ! include_once WPCACHEHOME . 'file.php' ) { ... }

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Perl_IncludeExpression extends PrimaryOperator
{
	public @S(10) Perl_KeywordChoice INCLUDE = new Perl_KeywordChoice("include_once", "require_once", "include", "require");
	public @S(20) Perl_Expression what;
}
