// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Symbols.Lisp_Variable_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Lisp_LetFunction extends PrimaryOperator
		implements EagleRunnable, AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_let.htm") Lisp_KeywordChoice LET = new Lisp_KeywordChoice("let", "prog");
	public @S(30) @OPT PunctuationStar star;
	public @S(40) Lisp_LetVariables variables;
	public @S(50) TokenList<Lisp_Expression> values;
	public @S(60) PunctuationRightParen rightParen;

	public static class Lisp_LetVar extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT PunctuationComma comma;
		public @S(30) Lisp_Variable_Definition var;
		public @S(40) Lisp_Expression value;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class Lisp_LetVariables extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT TokenList<Lisp_LetVar> valuePairs;
		public @S(30) PunctuationRightParen rightParen;
	}

//	private @SKIP EagleScope _scope = new EagleScope(this, Lisp_Syntax.IS_CASE_SENSITIVE);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Set local variables
		for (Lisp_LetVar var : variables.valuePairs._elements)
		{
			EagleValue val = interpreter.getEagleValue(var.value);
			interpreter.setSymbol(var, var.var.toString(), val);
		}

		// Perform actions
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Lisp_Expression elt : values._elements)
		{
			result = interpreter.tryToInterpret(elt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
	}
}
