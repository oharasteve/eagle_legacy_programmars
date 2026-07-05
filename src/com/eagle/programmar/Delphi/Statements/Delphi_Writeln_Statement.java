// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_Writeln_Statement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("System.Writeln") Delphi_KeywordChoice WRITELN = new Delphi_KeywordChoice("Write", "WriteLn");
	public @S(20) @OPT Delphi_WriteLn_Something something;

	public static class Delphi_WriteLn_Piece extends TokenSequence
	{
		public @S(10) Delphi_Expression expr;
		public @S(20) @OPT Delphi_Writeln_ColonWidth width;
		public @S(30) @OPT Delphi_Writeln_ColonWidth precision;

		public static class Delphi_Writeln_ColonWidth extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) Delphi_Expression width;
		}
	}

	public static class Delphi_WriteLn_Something extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Delphi_WriteLn_Piece, PunctuationComma> pieces;
		public @S(30) PunctuationRightParen rightParen;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, WRITELN.getValue(), WRITELN);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		for (int i = 0; i < something.pieces.getPrimaryCount(); i++)
		{
			Delphi_WriteLn_Piece piece = something.pieces.getPrimaryElement(i);
			if (piece.width != null && piece.width.isPresent())
			{
				throw new RuntimeException("Can't handle field widths");
			}
			EagleValue val = interpreter.getEagleValue(piece.expr);
			String result = val.forceStringValue();
			argTypes.add(val.getType());
			System.out.print(result);
		}
		_metrics.calledWith(argTypes);
		System.out.println();
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();

		if (something != null && something.isPresent())
		{
			// Pick up metrics, if known
			ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(WRITELN);

			int numPieces = something.pieces.getPrimaryCount();
			for (int i = 0; i < numPieces; i++)
			{
				Delphi_WriteLn_Piece piece = something.pieces.getPrimaryElement(i);
				if (piece.width != null && piece.width.isPresent())
				{
					throw new RuntimeException("Can't handle field widths");
				}
				pieces.add(transformer.transformExpression(generator, piece.expr));
				
				if (metrics == null)
				{
					types.add(TypeEnum.STRING);
				}
				else
				{
					types.add(metrics.get(i));
				}
			}
		}

		boolean newLine;
		switch (WRITELN.getValue().toLowerCase())
		{
		case "write":
			newLine = false;
			break;
		case "writeln":
			newLine = true;
			break;
		default:
			throw new RuntimeException("Unexpected write command: " + WRITELN.getValue());
		}

		return generator.newPrintStatement(pieces, types, newLine, false, this);
	}
}
