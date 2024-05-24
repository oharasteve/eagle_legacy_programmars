// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_FunctionBlock;
import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_Function_Parameters;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_FunctionExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword FUNCTION = new Perl_Keyword("function");
	public @S(20) Perl_Function_Parameters params;
	public @S(30) @OPT Perl_FunctionUse use;
	public @S(40) Perl_FunctionBlock block;

	public static class Perl_FunctionUse extends TokenSequence
	{
		public @S(10) Perl_Keyword USE = new Perl_Keyword("use");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Punctuation ampersand = new Perl_Punctuation('&');
		public @S(40) Perl_Variable var;
		public @S(50) PunctuationRightParen rightParen;
	}
}
