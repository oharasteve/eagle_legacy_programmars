// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Literals extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) TokenList<Python_Literal> literals;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (literals._elements.size() == 1)
		{
			Python_Literal literal = literals._elements.get(0);
			interpreter.pushStr(literal.getValue());
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			for (Python_Literal literal : literals._elements)
			{
				sb.append(literal.getValue());
			}
			interpreter.pushStr(sb.toString());
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (literals._elements.size() != 1)
		{
			throw new RuntimeException("Cannot handle multiple literals yet");
		}
		Python_Literal literal = literals._elements.get(0);
		return generator.newLiteralExpression(literal.getValue().replaceAll("['\"]", ""), this);
	}

	public static Python_Literals generateLiterals(String txt, AbstractToken source)
	{
		String val = txt;
		Python_Literal lit1 = Python_Literal.generateLiteral(val, source);
		Python_Literals lits = new Python_Literals();
		lits.literals = new TokenList<Python_Literal>();
		lits.literals.addToken(lit1);
		lits.setTransformationSource(source);
		return lits;
	}

	public static Python_Expression generateLiteralsExpression(String txt, AbstractToken source)
	{
		Python_Literals lit = generateLiterals(txt, source);
		return Python_Generator.wrapExpression(lit);
	}
}
