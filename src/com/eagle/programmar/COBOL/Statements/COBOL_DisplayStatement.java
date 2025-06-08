// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
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

	public static class COBOL_DisplayWhat extends TokenSequence implements EagleRunnable
	{
		public @S(10) SeparatedList<COBOL_Expression, PunctuationComma> exprs;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < exprs.getPrimaryCount(); i++)
			{
				COBOL_Expression expr = exprs.getPrimaryElement(i);
				String val = interpreter.getStrValue(expr);
				sb.append(val);
			}
			System.out.println(sb.toString());
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_DisplayClause clause : clauses._elements)
		{
			interpreter.tryToInterpret(clause.what);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		if (clauses.size() == 1)
		{
			COBOL_DisplayClause clause = clauses.first();
			if (clause.what.exprs.getPrimaryCount() == 1)
			{
				COBOL_Expression expr = clause.what.exprs.first();
				AbstractExpression line = transformer.transformExpression(generator, expr);
				return generator.newPrintStatement1(line, true, this);
			}
		}
		throw new RuntimeException("Unable to handle DISPLAY: " + this);
	}
}
