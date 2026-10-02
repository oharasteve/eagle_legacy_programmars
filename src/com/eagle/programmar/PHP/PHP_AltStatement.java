// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: PHP's alternative control syntax,
//   if (c): ... elseif (c): ... else: ... endif;   foreach (...): ... endforeach;   while/for likewise,
// which WordPress templates use for every block of markup.

package com.eagle.programmar.PHP;

import com.eagle.programmar.PHP.PHP_AltBlock.PHP_AltElse;
import com.eagle.programmar.PHP.PHP_AltBlock.PHP_AltHead;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

/** The same syntax inside one PHP block: if (c): statements elseif (c): statements else: statements endif; */
public class PHP_AltStatement extends TokenSequence
{
	public @S(10) PHP_AltHead head;
	public @S(20) PunctuationColon colon;
	public @S(30) @OPT TokenList<Perl_StatementOrComment> statements;
	public @S(40) @OPT TokenList<PHP_AltMiddleStatements> middles;
	public @S(50) Perl_KeywordChoice END = new Perl_KeywordChoice("endif", "endforeach", "endwhile", "endfor", "endswitch");
	public @S(60) @OPT PunctuationSemicolon semicolon;

	public static class PHP_AltMiddleStatements extends TokenSequence
	{
		public @S(10) PHP_AltElse what;
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT TokenList<Perl_StatementOrComment> statements;
	}
}
