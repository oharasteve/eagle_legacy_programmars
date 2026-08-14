// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopConditional;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopForAsClause;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopInitialFinal;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopListAccumulation;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopNumericAccumulation;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopTerminationTest;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopUnconditionalDo;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopUnconditionalReturn;
import com.eagle.programmar.Lisp.Loops.Lisp_LoopWith;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_LoopFunction extends PrimaryOperator implements AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("m_loop.htm") Lisp_Keyword LOOP = new Lisp_Keyword("loop");
	public @S(30) Lisp_LoopType loopType;
	public @S(40) PunctuationRightParen rightParen;

	public static class Lisp_LoopType extends TokenChooser
	{
		public @CHOICE Lisp_Expression XXsimpleExpr;

		public @CHOICE static class Lisp_LoopFancy extends TokenSequence
		{
			public @S(10) @OPT Lisp_LoopNamed named;
			public @S(20) @OPT TokenList<Lisp_LoopVariableClause> variableClauses;
			public @S(30) TokenList<Lisp_LoopMainClause> mainClauses;

			public static class Lisp_LoopNamed extends TokenSequence
			{
				public @S(10) Lisp_Keyword NAMED = new Lisp_Keyword("named");
				public @S(20) Lisp_Variable_Definition name;
			}

			public static class Lisp_LoopVariableClause extends TokenChooser
			{
				public @CHOICE Lisp_LoopInitialFinal XXinitialFinal;
				public @CHOICE Lisp_LoopWith XXwith;
				public @CHOICE Lisp_LoopForAsClause XXforAs;
			}

			public static class Lisp_LoopMainClause extends TokenChooser
			{
				public @CHOICE Lisp_LoopInitialFinal XXinitialFinal;
				public @CHOICE Lisp_LoopUnconditionalDo XXunconditionalDo;
				public @CHOICE Lisp_LoopUnconditionalReturn XXunconditionalReturn;
				public @CHOICE Lisp_LoopListAccumulation XXlistAccumulation;
				public @CHOICE Lisp_LoopNumericAccumulation XXnumericAccumulation;
				public @CHOICE Lisp_LoopConditional XXconditional;
				public @CHOICE Lisp_LoopTerminationTest XXterminationTest;
			}
		}
	}
}
