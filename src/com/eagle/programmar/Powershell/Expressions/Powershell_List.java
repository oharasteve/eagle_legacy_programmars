// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Powershell_ExpressionList.Powershell_MoreExpression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
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

public class Powershell_List extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @OPT Powershell_Punctuation at = new Powershell_Punctuation("@");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT Powershell_ExpressionList expressions;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		if (expressions != null && expressions.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expressions.expr);
			array.addValue(val);
			for (int i = 0; i < expressions.more.size(); i++)
			{
				Powershell_MoreExpression expr = expressions.more._elements.get(i);
				val = interpreter.getEagleValue(expr.expr);
				array.addValue(val);
			}
		}
		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> values = new ArrayList<AbstractExpression>();
		if (expressions != null && expressions.isPresent())
		{
			Powershell_Expression expr1 = expressions.expr;
			AbstractExpression newExpr1 = transformer.transformExpression(generator, expr1);
			values.add(newExpr1);
			for (int i = 0; i < expressions.more.size(); i++)
			{
				Powershell_MoreExpression expr2 = expressions.more._elements.get(i);
				AbstractExpression newExpr2 = transformer.transformExpression(generator, expr2.expr);
				values.add(newExpr2);
			}
		}
		return generator.newArrayExpression(values, this);
	}
}
