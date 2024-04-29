// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2024

package com.eagle.programmar.PHP;

import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.tokens.TokenChooser;

public class PHP_Element extends TokenChooser
{
	public @CHOICE @SYNTAX(Perl_Syntax.class) Perl_StatementOrComment statement;
}
