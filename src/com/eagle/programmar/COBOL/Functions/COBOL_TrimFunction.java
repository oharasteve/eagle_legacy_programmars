// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_TrimFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_Keyword FUNCTION = new COBOL_Keyword("FUNCTION");
	public @S(20) COBOL_Keyword TRIM = new COBOL_Keyword("TRIM");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) COBOL_Expression expr;
	public @S(50) @OPT COBOL_KeywordChoice LEADING = new COBOL_KeywordChoice("LEADING", "TRAILING");
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		boolean leading = true;
		if (LEADING != null && LEADING.isPresent())
		{
			if (LEADING.getValue().equals("TRAILING")) leading = false;
		}
		String trimmed;
		if (leading)
		{
			trimmed = str.stripLeading();
		}
		else
		{
			trimmed = str.stripTrailing();
		}
		interpreter.pushStr(trimmed);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newTrimFunction(theExpr, this);
	}
}
