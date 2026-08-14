// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Lisp.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Lisp_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, TokenList<Lisp_Expression> argList,
			ArgumentsMetrics metrics)
	{
		String fmt = interpreter.getStrValue(argList.first());
		fmt = fmt.replaceAll("~%", "\n");
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.size();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList._elements.get(i));
		}
		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '~', "Ad", args);
		return evaluateLiteral(interpreter, metrics, Lisp_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		TokenList<Lisp_Expression> argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		Lisp_Expression expr = argList.first();
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Lisp_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Lisp_Literal str = (Lisp_Literal) which;
		String fmt = str.removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.size();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList._elements.get(i));
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '~', "Ad", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Lisp_Expression.class, pieces, source);
		return result;
	}
}
