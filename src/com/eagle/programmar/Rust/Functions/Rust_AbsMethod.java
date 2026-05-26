// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 6, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Expressions.Rust_CastExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_AbsMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression expression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword ABS = new Rust_Keyword("abs");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getIntValue(expression);
		interpreter.pushDouble(Math.abs(num));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression numExpr = transformer.transformExpression(generator, expression);
		return generator.newAbsFunction(numExpr, this);
	}

	public static Rust_Expression generateAbsFunc(Rust_Expression expression, AbstractToken source)
	{
		// Don't bother if it is a constant
		AbstractToken which = expression.getWhich();
		if (which instanceof Rust_Number)
		{
			Rust_Number num = (Rust_Number) which;
			try
			{
				int n = Integer.parseInt(num.getValue());
				if (n < 0) num.setValue(Integer.toString(-n));
				return expression;
			}
			catch (Exception ex)
			{
				// Ignore errors
			}
		}

		// Negative numbers might be given as "(-3)"
		if (which instanceof Rust_ParenthesizedExpression)
		{
			Rust_ParenthesizedExpression paren = (Rust_ParenthesizedExpression) which;
			if (paren.expressions.size() == 1)
			{
				Rust_Expression expr = paren.expressions.first();
				if (expr.getWhich() instanceof Rust_Number)
				{
					Rust_Number num = (Rust_Number) expr.getWhich();
					try
					{
						int n = Integer.parseInt(num.getValue());
						num.setValue(Integer.toString(Math.abs(n)));
						return Rust_Generator.wrapExpression(num);
					}
					catch (Exception ex)
					{
						// Ignore errors
					}
				}
			}
		}
		
		Rust_AbsMethod abs = new Rust_AbsMethod();
		abs.dot = new PunctuationPeriod();
		abs.leftParen = new PunctuationLeftParen();
		abs.rightParen = new PunctuationRightParen();
		if (expression.getWhich() instanceof Rust_ParenthesizedExpression)
		{
			// Don't create a second set of parens
			Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) expression.getWhich();
			abs.expression = parens.expressions.first();
		}
		else
		{
			abs.expression = Rust_CastExpression.newCastExpression("f64", expression, null);
		}

		abs.setTransformationSource(source);
		return Rust_Generator.wrapExpression(abs);
	}
}
