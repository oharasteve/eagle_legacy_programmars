// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopConditional extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice when = new Lisp_KeywordChoice("if", "when", "unless");
	public @S(20) Lisp_Expression value;
	public @S(30) @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
	public @S(40) @OPT Lisp_LoopConditionalElse conditionalElse;
	public @S(50) @OPT Lisp_Keyword END = new Lisp_Keyword("end");

	public static class Lisp_LoopMoreSelectableClause extends TokenSequence
	{
		public @S(10) Lisp_Keyword AND = new Lisp_Keyword("and");
		public @S(20) Lisp_Expression value;
	}

	public static class Lisp_LoopConditionalElse extends TokenSequence
	{
		public @S(10) Lisp_Keyword ELSE = new Lisp_Keyword("else");
		public @S(20) Lisp_Expression clause;
		public @S(30) @OPT TokenList<Lisp_LoopMoreSelectableClause> more;
	}
}