// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Syntax.FSharp_Multiline_Syntax;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class FSharp_BracketBars extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) FSharp_Punctuation leftBracketBar = new FSharp_Punctuation("[|");
	public @S(20) @OPT FSharp_EndOfLine eoln;
	public @S(30) @OPT @SYNTAX(FSharp_Multiline_Syntax.class) SeparatedList<FSharp_Expression, PunctuationSemicolon> expressions;
	public @S(40) FSharp_Punctuation rightBarBracket = new FSharp_Punctuation("|]");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray values = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			FSharp_Expression expr = expressions.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			values.addValue(val);
		}

		interpreter.pushEagleValue(values);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (expressions != null && expressions.isPresent())
		{
			ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
			for (int i = 0; i < expressions.getPrimaryCount(); i++)
			{
				FSharp_Expression expr = expressions.getPrimaryElement(i);
				exprs.add(transformer.transformExpression(generator, expr));
			}
			return generator.newArrayExpression(exprs, this);
		}
		return null;
	}
}
