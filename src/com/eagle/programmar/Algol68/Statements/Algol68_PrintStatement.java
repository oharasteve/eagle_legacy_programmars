// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
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

public class Algol68_PrintStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword PRINT = new Algol68_Keyword("PRINT");
	public @S(20) Algol68_Punctuation leftParen1 = new Algol68_Punctuation("(");
	public @S(30) Algol68_Punctuation leftParen2 = new Algol68_Punctuation("(");
	public @S(40) SeparatedList<Algol68_PrintWhat, PunctuationComma> items;
	public @S(50) Algol68_Punctuation rightParen1 = new Algol68_Punctuation(")");
	public @S(60) Algol68_Punctuation rightParen2 = new Algol68_Punctuation(")");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	public static class Algol68_PrintNewLine extends TokenSequence implements AbstractStatement
	{
		public @S(10) Algol68_Keyword NEW = new Algol68_Keyword("NEW");
		public @S(20) Algol68_Keyword LINE = new Algol68_Keyword("LINE");
	}

	public static class Algol68_PrintWhat extends TokenChooser
	{
		public @CHOICE Algol68_Expression XXexpr;
		public @CHOICE Algol68_PrintNewLine XXnewLine;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, PRINT.getValue(), PRINT);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		for (int i = 0; i < items.getPrimaryCount(); i++)
		{
			AbstractToken piece = items.getPrimaryElement(i).getWhich();
			if (piece instanceof Algol68_Expression)
			{
				EagleValue val = interpreter.getEagleValue(piece);
				String result = val.forceStringValue();
				argTypes.add(val.getType());
				System.out.print(result);
			}
			else if (piece instanceof Algol68_PrintNewLine)
			{
				System.out.println();
			}
			else
			{
				throw new RuntimeException("Unable to print " + items);
			}
		}

		_metrics.calledWith(argTypes);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Pick up metrics, if known
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(PRINT);

		int numItems = items.getPrimaryCount();
		boolean newLine = false;
		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();
		for (int i = 0; i < numItems; i++)
		{
			Algol68_PrintWhat item = items.getPrimaryElement(i);
			AbstractToken whichItem = item.getWhich();
			if (i == numItems-1 && whichItem instanceof Algol68_PrintNewLine)
			{
				newLine = true;
			}
			else if (whichItem instanceof Algol68_Expression)
			{
				Algol68_Expression expr = (Algol68_Expression) whichItem;
				pieces.add(transformer.transformExpression(generator, expr));
				
				if (metrics == null)
				{
					types.add(TypeEnum.STRING);
				}
				else
				{
					types.add(metrics.get(i));
				}
			}
			else
			{
				throw new RuntimeException("Unable to handle: " + whichItem);
			}
		}
		return generator.newPrintStatement(pieces, types, newLine, false, this);
	}
}
