// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.Lisp.Loops;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Lisp_LoopWith extends TokenSequence
{
	public @S(10) Lisp_LoopWithElement element;
	public @S(20) @OPT TokenList<Lisp_LoopMoreWith> more;
	
	public static class Lisp_LoopMoreWith extends TokenSequence
	{
		public @S(10) Lisp_Keyword AND = new Lisp_Keyword("and");
		public @S(20) Lisp_LoopWithElement element;
	}
	
	public static class Lisp_LoopWithElement extends TokenSequence
	{
		public @S(10) Lisp_Keyword WITH = new Lisp_Keyword("with");
		public @S(20) @OPT Lisp_SExpr typeSpec;
		public @S(30) @OPT Lisp_LoopWithValue equalsValue;
		
		public static class Lisp_LoopWithValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Lisp_SExpr value;
		}
	}
}