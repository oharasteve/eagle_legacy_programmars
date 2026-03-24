// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2024

package com.eagle.programmar.AWK.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_ConcatenationExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_ConcatPiece piece1;
	public @S(20) AWK_ConcatPiece piece2;
	public @S(30) @OPT TokenList<AWK_ConcatPiece> pieces;

	private @SKIP ArgumentsMetrics _metrics = null;

	public static class AWK_ConcatPiece extends TokenChooser
	{
		public @CHOICE AWK_String XXstring;
		public @CHOICE AWK_VariableExpression XXvariable;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, "CONCAT", this);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();

		EagleValue val1 = interpreter.getEagleValue(piece1.getWhich());
		String str1 = val1.forceStringValue();
		argTypes.add(val1.typeName());
		sb.append(str1);

		EagleValue val2 = interpreter.getEagleValue(piece2.getWhich());
		String str2 = val2.forceStringValue();
		argTypes.add(val2.typeName());
		sb.append(str2);

		if (pieces != null && pieces.isPresent())
		{
			for (AWK_ConcatPiece piece : pieces._elements)
			{
				EagleValue val = interpreter.getEagleValue(piece.getWhich());
				String str = val.forceStringValue();
				argTypes.add(val.typeName());
				sb.append(str);
			}
		}
		interpreter.pushStr(sb.toString());
		_metrics.calledWith(argTypes);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Oper2Types types = null;

		// Pick up metrics, if known
		ArrayList<String> metrics = transformer.findArgumentsMetric(this);
		if (metrics != null)
		{
			types = new Oper2Types();
		}

		AbstractExpression line = addPiece(generator, metrics, types, 0, piece1);
		AbstractExpression next = addPiece(generator, metrics, types, 1, piece2);
		line = generator.newAdditiveExpression(types, line, AdditiveEnum.PLUS, next, piece2);
		if (pieces != null && pieces.isPresent())
		{
			int i = 2;
			for (AWK_ConcatPiece piece : pieces._elements)
			{
				next = addPiece(generator, metrics, types, i, piece);
				line = generator.newAdditiveExpression(types, line, AdditiveEnum.PLUS, next, piece);
				i++;
			}
		}

		return line;
	}

	private static AbstractExpression addPiece(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, ArrayList<String> metrics,
			Oper2Types types, int i, AWK_ConcatPiece piece)
	{
		if (metrics != null)
		{
			types._type1 = EagleString.STRING;
			types._type2 = metrics.get(i);
		}

		AbstractToken which1 = piece.getWhich();
		if (which1 instanceof AWK_String)
		{
			AWK_String str = (AWK_String) which1;
			String lit = str.literal.getValue();
			if (lit.startsWith("\""))
			{
				lit = lit.substring(1, lit.length() - 1);
			}
			return generator.newLiteralExpression(lit, piece);
		}
		else if (which1 instanceof AWK_VariableExpression)
		{
			AWK_VariableExpression varExpr = (AWK_VariableExpression) which1;
			return generator.newVariableExpression(varExpr.variable.id.getValue(),
					SubscriptEnum.FIRST_IS_ZERO, null, piece);
		}
		else
		{
			throw new RuntimeException("Unable to handle: " + which1);
		}
	}
}
