// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_BracketStringFirst extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword STRING = new TCL_Keyword("string");
	public @S(30) TCL_Keyword FIRST = new TCL_Keyword("first");
	public @S(40) TCL_Expression pattern;
	public @S(50) TCL_Variable string;
	public @S(60) @OPT TCL_Expression start;
	public @S(70) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String patt = interpreter.getStrValue(pattern);
		String str = interpreter.getStrValue(string);
		if (start != null && start.isPresent())
		{
			int sc = interpreter.getIntValue(start);
			interpreter.pushInt(str.indexOf(patt, sc));
		}
		else
		{
			interpreter.pushInt(str.indexOf(patt));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractVariable strVar = generator.newVariable(string.id.getValue());
		AbstractExpression pattExpr = transformer.transformExpression(generator, pattern);
		AbstractExpression startExpr = null;
		if (start != null && start.isPresent())
		{
			startExpr = transformer.transformExpression(generator, start);
		}
		return generator.newIndexOfFunction(strVar, pattExpr, startExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}
}
