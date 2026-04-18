// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Print_Function extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Keyword PRINT = new Python_Keyword("print");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE SeparatedList<Python_Expression, PunctuationComma> exprs;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		int numExpr = exprs.getPrimaryCount();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numExpr; i++)
		{
			if (i > 0)
			{
				sb.append(' ');
			}
			EagleValue piece = interpreter.getEagleValue(exprs.getPrimaryElement(i));
			argTypes.add(piece.getType());
			sb.append(piece.forceStringValue());
		}

		_metrics.calledWith(argTypes);
		System.out.println(sb.toString());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);
		Oper2Types types = new Oper2Types();
		types._type1 = TypeEnum.STRING;

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
				types._type2 = TypeEnum.STRING;
				result = generator.newAppendExpression(types, result, space, PRINT);
				types._type2 = metrics.get(i);
				result = generator.newAppendExpression(types, result, piece, PRINT);
			}
		}

		return generator.newPrintFunction1(result, TypeEnum.STRING, true, false, this);
	}

	public static Python_Expression generatePrintFunc1(Python_Expression line, TypeEnum type,
			boolean newLine, AbstractToken source)
	{
		Python_Print_Function prtFunc = new Python_Print_Function();
		prtFunc.leftParen = new PunctuationLeftParen();
		prtFunc.exprs = new SeparatedList<Python_Expression, PunctuationComma>();
		prtFunc.exprs.addPrimaryElement(line);

		if (!newLine)
		{
			prtFunc.exprs.addSecondaryElement(new PunctuationComma());
			Python_Expression emptyExpr1 = Python_Literal.generateLiteralExpression("", null);
			Python_Variable end = Python_Variable.newVariable("end");
			Python_Expression asgExpr1 = Python_Assignment_Expression.generateAssignment(end, null,
					AssignmentEnum.EQUALS, emptyExpr1, source);
			prtFunc.exprs.addPrimaryElement(asgExpr1);
		}

		prtFunc.rightParen = new PunctuationRightParen();
		prtFunc.setTransformationSource(source);
		return Python_Generator.wrapExpression(prtFunc);
	}

	public static Python_Expression generatePrintFunc(ArrayList<Python_Expression> pieces,
			ArrayList<TypeEnum> types, boolean newLine, AbstractToken source)
	{
		Python_Expression line;
		if (pieces.size() == 0)
		{
			line = Python_Literal.generateLiteralExpression("", null);
		}
		else
		{
			line = pieces.get(0);
			Oper2Types pair = new Oper2Types();
			pair._type1 = types.get(0);
			for (int i = 1; i < pieces.size(); i++)
			{
				Python_Expression piece = pieces.get(i);
				pair._type2 = types.get(i);
				line = Python_Additive_Expression.generateAdditive(pair, line, AdditiveEnum.PLUS, piece, source);
				pair._type1 = TypeEnum.STRING;
			}
		}
		
		return generatePrintFunc1(line, TypeEnum.STRING, newLine, source);
	}
}
