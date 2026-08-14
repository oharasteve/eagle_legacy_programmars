// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
import com.eagle.programmar.Ruby.Terminals.Ruby_LiteralExpression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ruby_PutsStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ruby_Keyword PUTS = new Ruby_Keyword("puts");
	public @S(20) Ruby_Expression expr;
	public @S(30) Ruby_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (expr.getWhich() instanceof Ruby_Literal)
		{
			ArgumentsMetrics metrics = null;
			String formatted = Ruby_LiteralExpression.interpret(interpreter, expr, metrics);
			System.out.println(formatted);
		}
		else
		{
			String line = interpreter.getStrValue(expr);
			System.out.println(line);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (expr.getWhich() instanceof Ruby_Literal)
		{
			ArrayList<TypeEnum> metrics = null;
			AbstractExpression newLine = Ruby_LiteralExpression.transform(transformer,
					generator, expr, metrics, this);
			return generator.newPrintStatement1(newLine, TypeEnum.STRING, true, false, this);
		}

		AbstractExpression line = transformer.transformExpression(generator, expr);
		return generator.newPrintStatement1(line, TypeEnum.STRING, true, false, this);
	}
}
