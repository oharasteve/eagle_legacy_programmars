// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Algol68_PrintStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Algol68_Keyword PRINT = new Algol68_Keyword("PRINT");
	public @S(20) Algol68_Punctuation leftParen1 = new Algol68_Punctuation("(");
	public @S(30) Algol68_Punctuation leftParen2 = new Algol68_Punctuation("(");
	public @S(40) SeparatedList<Algol68_PrintWhat, PunctuationComma> pieces;
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
		ArrayList<String> argTypes = new ArrayList<String>();

		for (int i = 0; i < pieces.getPrimaryCount(); i++)
		{
			AbstractToken piece = pieces.getPrimaryElement(i).getWhich();
			if (piece instanceof Algol68_Expression)
			{
				EagleValue val = interpreter.getEagleValue(piece);
				String result = val.forceStringValue();
				argTypes.add(val.typeName());
				System.out.print(result);
			}
			else if (piece instanceof Algol68_PrintNewLine)
			{
				System.out.println();
			}
			else
			{
				throw new RuntimeException("Unable to print " + pieces);
			}
		}
		
		_metrics.calledWith(argTypes);
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression line = null;
		Oper2Types types = null;
		// Pick up metrics, if known
		ArrayList<String> metrics = transformer.findArgumentsMetric(PRINT);
		if (metrics != null)
		{
			types = new Oper2Types();
		}
		
		int numPieces = pieces.getPrimaryCount();
		boolean newLine = false;
		for (int i = 0; i < numPieces; i++)
		{
			Algol68_PrintWhat piece = pieces.getPrimaryElement(i);
			AbstractToken whichPiece = piece.getWhich();
			if (whichPiece instanceof Algol68_PrintNewLine)
			{
				newLine = true;
			}
			else if (whichPiece instanceof Algol68_Expression)
			{
				Algol68_Expression expr = (Algol68_Expression) whichPiece;
				if (line == null)
				{
					line = transformer.transformExpression(generator, expr);
				}
				else
				{
					if (metrics != null && i < metrics.size())
					{
						types._type1 = metrics.get(i-1);
						types._type2 = metrics.get(i);
					}
					
					AbstractExpression next = transformer.transformExpression(generator, expr);
					line = generator.newAdditiveExpression(types, line, AdditiveEnum.PLUS, next, piece);
				}
			}
			else
			{
				throw new RuntimeException("Unable to handle: " + whichPiece);
			}
		}
		return generator.newPrintStatement(line, newLine, this);
	}
}
