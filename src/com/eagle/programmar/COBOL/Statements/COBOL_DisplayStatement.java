// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayOptions.COBOL_DisplayWithNoAdvancing;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_DisplayStatement extends COBOL_AbstractStatement
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("rlpsdisp.htm") COBOL_Keyword DISPLAY = new COBOL_Keyword("DISPLAY");
	public @S(20) @OPT COBOL_DisplayPosition position;
	public @S(30) TokenList<COBOL_DisplayClause> clauses;

	public static class COBOL_DisplayPosition extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT COBOL_Expression x;
		public @S(30) PunctuationComma comma;
		public @S(40) COBOL_Expression y;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class COBOL_DisplayClause extends TokenSequence
	{
		public @S(10) COBOL_DisplayWhat what;
		public @S(20) @OPT TokenList<COBOL_DisplayOptions> options;
	}

	public static class COBOL_DisplayLine extends TokenSequence
	{
		public @S(10) COBOL_Keyword LINE = new COBOL_Keyword("LINE");
		public @S(20) COBOL_Expression line;
	}

	public static class COBOL_DisplayColumn extends TokenSequence
	{
		public @S(10) COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
		public @S(20) COBOL_Expression column;
	}

	public static class COBOL_DisplayWhat extends TokenSequence
	{
		public @S(10) SeparatedList<COBOL_Expression, PunctuationComma> exprs;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, DISPLAY.getValue(), DISPLAY);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		for (COBOL_DisplayClause clause : clauses._elements)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < clause.what.exprs.getPrimaryCount(); i++)
			{
				COBOL_Expression expr = clause.what.exprs.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				String result = val.forceStringValue();
				argTypes.add(val.getType());
				sb.append(result);
			}
			_metrics.calledWith(argTypes);
			System.out.println(sb.toString());
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (clauses.size() == 1)
		{
			COBOL_DisplayClause clause = clauses.first();
			ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
			ArrayList<TypeEnum> types = new ArrayList<TypeEnum>();

			// Pick up metrics, if known
			ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(DISPLAY);

			boolean newline = true;
			for (COBOL_DisplayOptions opt : clause.options._elements)
			{
				if (opt.getWhich() instanceof COBOL_DisplayWithNoAdvancing)
				{
					newline = false;
				}
			}
			
			int numPieces = clause.what.exprs.getPrimaryCount();
			for (int i = 0; i < numPieces; i++)
			{
				COBOL_Expression expr = clause.what.exprs.getPrimaryElement(i);
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

			return generator.newPrintStatement(pieces, types, newline, false, this);
		}
		throw new RuntimeException("Unable to handle DISPLAY: " + this);
	}
}
