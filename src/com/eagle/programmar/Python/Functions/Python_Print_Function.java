// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Print_Function extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE SeparatedList<Python_Expression,PunctuationComma> exprs;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		int numExpr = exprs.getPrimaryCount();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numExpr; i++)
		{
			if (i > 0)
			{
				sb.append(' ');
			}
			EagleValue piece = interpreter.getEagleValue(exprs.getPrimaryElement(i));
			argTypes.add(piece.typeName());
			sb.append(piece.forceStringValue());
		}

		_metrics.calledWith(argTypes);
		System.out.println(sb.toString());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINT);
		Oper2Types types = new Oper2Types();
		types._type1 = EagleString.STRING;

		int numExpr = exprs.getPrimaryCount();
		AbstractExpression space = null;
		if (numExpr > 1)
		{
			space = generator.newLiteralExpression(" ", null);
		}
		AbstractExpression result = null;
		for (int i = 0; i < numExpr; i++)
		{
			AbstractExpression piece = transformer.transformExpression(generator, exprs.getPrimaryElement(i));
			
			if (i == 0)
			{
				result = piece;
			}
			else
			{
				types._type2 = EagleString.STRING;
				result = generator.newAppendExpression(types, result, space, PRINT);
				types._type2 = metrics.get(i);
				result = generator.newAppendExpression(types, result, piece, PRINT);
			}
		}
		
		return generator.newPrintFunction(result, true, false, this);
	}

	public Python_Expression generatePrintFunc(Python_Expression line,
			boolean newLine, AbstractToken source)
	{
		leftParen = new PunctuationLeftParen();
		exprs = new SeparatedList<Python_Expression, PunctuationComma>();
		exprs.addPrimaryElement(line);
		
		if (! newLine)
		{
			exprs.addSecondaryElement(new PunctuationComma());
			Python_Expression emptyExpr1 = Python_Literal.generateLiteralExpression("", null);
			Python_Variable end = Python_Variable.newVariable("end");
			Python_Assignment_Expression asg1 = new Python_Assignment_Expression();
			Python_Expression asgExpr1 = asg1.generateAssignment(end, null,
					AssignmentEnum.EQUALS, emptyExpr1, source);
			exprs.addPrimaryElement(asgExpr1);
		}
		
		rightParen = new PunctuationRightParen();
		setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
