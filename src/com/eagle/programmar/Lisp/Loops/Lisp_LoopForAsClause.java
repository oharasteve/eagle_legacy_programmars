// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Lisp_LoopForAsClause extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice FOR = new Lisp_KeywordChoice("for", "as");
	public @S(20) Lisp_Variable var;
	public @S(30) TokenList<Lisp_LoopForClause> forClause;

	public static class Lisp_LoopForClause extends TokenChooser
	{
		public @CHOICE static class Lisp_ForArithmetic extends TokenSequence
		{
			public @S(10) Lisp_KeywordChoice direction = new Lisp_KeywordChoice("across", "below", "from", "in", "on",
					"to");
			public @S(20) Lisp_SExpr expr;
		}

		public @CHOICE static class Lisp_ForEqualsThen extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Lisp_SExpr expr;
			public @S(30) @OPT Lisp_LoopForThen thenClause;

			public static class Lisp_LoopForThen extends TokenSequence
			{
				public @S(10) Lisp_Keyword THEN = new Lisp_Keyword("then");
				public @S(20) Lisp_SExpr expr;
			}
		}
	}
}