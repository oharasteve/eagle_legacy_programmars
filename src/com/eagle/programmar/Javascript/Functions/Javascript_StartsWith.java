// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_StartsWith extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable variable;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword STARTSWITH = new Javascript_Keyword("startsWith");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression pattExpr;
	public @S(60) @OPT PunctuationComma comma;
	public @S(70) @OPT Javascript_Expression scExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = variable.firstId.getWhich();
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
		EagleValue val = interpreter.findSymbol(id.getValue());
		String str = val.forceStringValue();
		String patt = interpreter.getStrValue(pattExpr);
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

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof Javascript_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		Javascript_Identifier_Reference idRef = (Javascript_Identifier_Reference) which;
		AbstractExpression theExpr = generator.newVariableExpression(idRef.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, this);
		AbstractExpression thePattern = transformer.transformExpression(generator, pattExpr);
		AbstractExpression theSC = null;
		if (scExpr != null && scExpr.isPresent())
		{
			theSC = transformer.transformExpression(generator, scExpr);
		}

		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}
}
