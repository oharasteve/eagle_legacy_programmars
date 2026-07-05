// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

package com.eagle.programmar.Python.Methods;

import com.eagle.generate.SubstringSCEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Find_Method extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Python_Variable string;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Keyword FIND = new Python_Keyword("find");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Python_Expression pattern;
	public @S(60) @OPT @NOSPACE Python_Find_SC scExpr;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;

	public static class Python_Find_SC extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Python_Expression start;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(string);
		String patt = interpreter.getStrValue(pattern);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushInt(str.indexOf(patt, sc));
		}
		else
		{
			interpreter.pushInt(str.indexOf(patt));
		}
	}

	public static Python_Expression generateIndexOf(Python_Variable str, Python_Expression patt,
			Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Python_Find_Method indexFn = new Python_Find_Method();
		indexFn.string = str;
		indexFn.dot = new PunctuationPeriod();
		indexFn.leftParen = new PunctuationLeftParen();
		indexFn.pattern = patt;
		if (sc != null)
		{
			indexFn.scExpr = new Python_Find_SC();
			indexFn.scExpr.setPresent(true);
			indexFn.scExpr.comma = new PunctuationComma();
			indexFn.scExpr.start = sc;
		}
		indexFn.rightParen = new PunctuationRightParen();

		indexFn.setTransformationSource(source);
		return Python_Generator.wrapExpression(indexFn);
	}
}
