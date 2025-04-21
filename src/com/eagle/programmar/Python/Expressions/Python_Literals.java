// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;

public class Python_Literals extends PrimaryOperator implements EagleRunnable
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
	
	public static Python_Literals generateExpression(String txt, AbstractToken source)
	{
		Python_Literal lit = new Python_Literal();
		Python_Literal lit1 = lit.generateLiteral(txt, source);
		Python_Literals lits = new Python_Literals();
		lits.literals = new TokenList<Python_Literal>();
		lits.literals.addToken(lit1);
		lits.setTransformationSource(source);
		return lits;
	}
}
