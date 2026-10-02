// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: PHP's alternative control syntax,
//   if (c): ... elseif (c): ... else: ... endif;   foreach (...): ... endforeach;   while/for likewise,
// which WordPress templates use for every block of markup.

package com.eagle.programmar.PHP;

import com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.programmar.PHP.PHP_Program.PHP_StartTag;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.programmar.Perl.Statements.Perl_ForEachStatement.Perl_ForEachAsStatement.Perl_ForEachArrow;
import com.eagle.programmar.Perl.Statements.Perl_ForStatement.Perl_ForWhat;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

/** <?php if (c): ?> markup <?php elseif (c): ?> markup <?php else: ?> markup <?php endif; ?> — across tags. */
public class PHP_AltBlock extends TokenSequence
{
	public @S(10) @SYNTAX(Perl_Syntax.class) PHP_AltOpen open;
	public @S(20) @OPT TokenList<PHP_Entry> body;
	public @S(30) @OPT TokenList<PHP_AltMiddle> middles;
	public @S(40) @SYNTAX(Perl_Syntax.class) PHP_AltClose close;

	public static class PHP_AltOpen extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) @OPT TokenList<Perl_Statement> statements;
		public @S(30) PHP_AltHead head;
		public @S(40) PunctuationColon colon;
		public @S(45) @OPT TokenList<Perl_Statement> trailing; // while ( have_posts() ) : the_post(); ?>
		public @S(50) PHP_EndTag endTag;
	}

	/** The construct that opens: if, foreach, while or for, with its parenthesised head. */
	public static class PHP_AltHead extends TokenChooser
	{
		public @CHOICE PHP_AltIf XXif;
		public @CHOICE PHP_AltForeach XXforeach;
		public @CHOICE PHP_AltWhile XXwhile;
		public @CHOICE PHP_AltFor XXfor;
	}

	public static class PHP_AltIf extends TokenSequence
	{
		public @S(10) Perl_Keyword IF = new Perl_Keyword("if");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PHP_AltForeach extends TokenSequence
	{
		public @S(10) Perl_Keyword FOREACH = new Perl_Keyword("foreach");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression expr;
		public @S(40) Perl_Keyword AS = new Perl_Keyword("as");
		public @S(50) Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(60) Perl_Variable_Definition var;
		public @S(70) @OPT Perl_ForEachArrow arrow;
		public @S(80) PunctuationRightParen rightParen;
	}

	public static class PHP_AltWhile extends TokenSequence
	{
		public @S(10) Perl_Keyword WHILE = new Perl_Keyword("while");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PHP_AltFor extends TokenSequence
	{
		public @S(10) Perl_Keyword FOR = new Perl_Keyword("for");
		public @S(20) Perl_ForWhat forWhat;
	}

	/** <?php elseif (c): ?> or <?php else: ?>, then its markup. */
	public static class PHP_AltMiddle extends TokenSequence
	{
		public @S(10) @SYNTAX(Perl_Syntax.class) PHP_AltMiddleTag tag;
		public @S(20) @OPT TokenList<PHP_Entry> body;
	}

	public static class PHP_AltMiddleTag extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) @OPT TokenList<Perl_Statement> statements;
		public @S(30) PHP_AltElse what;
		public @S(40) PunctuationColon colon;
		public @S(45) @OPT TokenList<Perl_Statement> trailing;
		public @S(50) PHP_EndTag endTag;
	}

	public static class PHP_AltElse extends TokenChooser
	{
		public @CHOICE PHP_AltElseIf XXelseif;
		public @CHOICE Perl_Keyword XXelse = new Perl_Keyword("else");
	}

	public static class PHP_AltElseIf extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice ELSEIF = new Perl_KeywordChoice("elseif", "else if");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
	}

	/** <?php endif; ?> and the other enders, with any statements that share the tag. */
	public static class PHP_AltClose extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) Perl_KeywordChoice END = new Perl_KeywordChoice("endif", "endforeach", "endwhile", "endfor", "endswitch");
		public @S(30) @OPT PunctuationSemicolon semicolon;
		public @S(40) @OPT TokenList<Perl_Statement> statements;
		public @S(50) PHP_EndTag endTag;
	}
}
