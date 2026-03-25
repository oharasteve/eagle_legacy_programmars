// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleDouble;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Format;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_PrintfStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword PRINTF = new Algol68_Keyword("PRINTF");
	public @S(20) Algol68_Punctuation doubleLeftParen = new Algol68_Punctuation("((");
	public @S(30) Algol68_Format format;
	public @S(40) PunctuationComma comma;
	public @S(50) Algol68_Expression expr;
	public @S(60) Algol68_Punctuation doubleRightParen = new Algol68_Punctuation("))");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, PRINTF, PRINTF.getValue());
		}

		// Note that Algol68_Format is a Literal with '$' instead of ' or "
		// Hence dd instead of $dd$ below
		String fmt = interpreter.getStrValue(format);
		EagleValue val = interpreter.getEagleValue(expr);

		if (val instanceof EagleInteger)
		{
			Integer ival = Integer.valueOf(val.forceIntegerValue());
			switch (fmt)
			{
			case "d":
				System.out.format("%1d", ival);
				break;
			case "dd":
				System.out.format("%2d", ival);
				break;
			case "ddd":
				System.out.format("%3d", ival);
				break;
			case "dddd":
				System.out.format("%4d", ival);
				break;
			default:
				throw new RuntimeException("Unable to printf " + ival + " using $" + fmt + "$");
			}
		}

		if (val instanceof EagleDouble)
		{
			double dval = val.forceDoubleValue();
			switch (fmt)
			{
			case "dd.d":
				System.out.format("%4.1f", Double.valueOf(dval));
				break;
			default:
				throw new RuntimeException("Unable to printf " + dval + " using $" + fmt + "$");
			}
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper1Types metric = transformer.findOperator1Metric(PRINTF);
		TypeEnum type = null;
		if (metric != null)
		{
			type = metric._type1;
		}

		String fmt = format.getValue();
		int width = 0;
		int decimals = 0;
		switch (fmt)
		{
		case "$d$":
			width = 1;
			break;
		case "$dd$":
			width = 2;
			break;
		case "$ddd$":
			width = 3;
			break;
		case "$dddd$":
			width = 4;
			break;
		case "$dd.d$":
			decimals = 1;
			break;
		default:
			throw new RuntimeException("Unable to printf " + expr + " using " + fmt);
		}
		AbstractExpression numExpr = transformer.transformExpression(generator, expr);
		AbstractExpression line;
		if (decimals == 0)
		{
			line = generator.newFormatNumber(numExpr, width, this);
		}
		else
		{
			line = generator.newFormatDecimal(numExpr, decimals, this);
		}
		return generator.newPrintStatement(line, type, false, false, this);
	}
}
