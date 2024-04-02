// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_ArgumentList;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Perl_BracesInvocation extends PrimaryOperator
{
	public @S(10) Perl_Variable methodName;
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT Perl_ArgumentList argList;
	public @S(40) PunctuationRightBrace rightBrace;
}
