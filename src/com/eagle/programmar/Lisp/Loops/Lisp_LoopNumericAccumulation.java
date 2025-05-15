// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Lisp_Variable;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Lisp_LoopNumericAccumulation extends TokenSequence
{
	public @S(10) Lisp_KeywordChoice operation = new Lisp_KeywordChoice("count", "counting", "maximize", "maximizing",
			"minimize", "minimizing", "sum", "summing");
	public @S(20) Lisp_Expression value;
	public @S(30) @OPT Lisp_LoopAccumulateInto accumulateInto;

	public static class Lisp_LoopAccumulateInto extends TokenSequence
	{
		public @S(10) Lisp_Keyword INTO = new Lisp_Keyword("into");
		public @S(20) Lisp_Variable variable;
	}
}