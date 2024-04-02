// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_FunctionCall extends PrimaryOperator
{
	public @S(10) Perl_Identifier_Reference fnName;
	public @S(20) @OPT TokenList<Perl_MoreFunctionName> more;
	public @S(30) @OPT TokenList<Perl_Method> perlMethods;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
	public @S(60) @OPT Perl_Expression parameter;
	public @S(70) @OPT TokenList<Perl_MoreParameters> moreExpr;
	public @S(80) PunctuationRightParen rightParen;
	
	public static class Perl_MoreFunctionName extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference fnName;
	}
	public static class Perl_Method extends TokenSequence
	{
		public @S(10) Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @S(20) Perl_Identifier_Reference fnName;
	}
	
	public static class Perl_MoreParameters extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Perl_Comment comment;
		public @S(30) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(40) Perl_Expression parameter;
	}
}
