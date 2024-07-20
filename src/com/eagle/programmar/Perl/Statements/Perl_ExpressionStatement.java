// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Perl.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_StatementSuffixModifier;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Perl_Expression expr;
	public @S(20) @OPT Perl_StatementSuffixModifier modifier;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) @OPT TokenList<Perl_Comment> comments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
