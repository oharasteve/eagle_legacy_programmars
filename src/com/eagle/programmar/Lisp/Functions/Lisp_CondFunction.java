// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_CondFunction extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_cond.htm") Lisp_KeywordChoice COND = new Lisp_KeywordChoice("cond");
	public @S(50) TokenList<Lisp_CondPair> pairs;
	public @S(60) PunctuationRightParen rightParen;

	public static class Lisp_CondPair extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Lisp_SExpr condition;
		public @S(30) TokenList<Lisp_SExpr> values;
		public @S(40) PunctuationRightParen rightParen;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		// Perform action
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Lisp_CondPair condPair : pairs._elements)
		{
			boolean cond = interpreter.getBoolValue(condPair.condition);
			if (cond)
			{
				// Rarely will there be more than one value per pair
				for (Lisp_SExpr expr : condPair.values._elements)
				{
					result = interpreter.tryToInterpret(expr);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
				break;
			}
		}
		return result;
	}
}
