// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2025

package com.eagle.programmar.Python.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class Python_StartsWith_Function extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Python_Variable string;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Keyword STARTSWITH = new Python_Keyword("startswith");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Python_Expression pattern;
	public @S(60) @OPT @NOSPACE Python_Index_SC scExpr;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;

	public static class Python_Index_SC extends TokenSequence
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
			interpreter.pushBool(str.startsWith(patt, sc));
		}
		else
		{
			interpreter.pushBool(str.startsWith(patt));
		}
	}
	
	public Python_Expression generateStartsWith(Python_Expression expr, Python_Expression patt,
			Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		AbstractToken token = expr.getWhich();
		if (! (token instanceof Python_VariableExpression))
		{
			throw new RuntimeException("Python startswith must be a variable, not " + token);
		}
		
		Python_VariableExpression varExpr = (Python_VariableExpression) token;
		this.string = varExpr.variable;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.pattern = patt;
		if (sc != null)
		{
			this.scExpr = new Python_Index_SC();
			this.scExpr.setPresent(true);
			this.scExpr.comma = new PunctuationComma();
			this.scExpr.start = sc;
		}
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
