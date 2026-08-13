// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2026

package com.eagle.programmar.C.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Expressions.C_Literals;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class C_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter, SeparatedList<C_Expression, PunctuationComma> argList,
			ArgumentsMetrics metrics)
	{
		String fmt = interpreter.getStrValue(argList.first());
		fmt = fmt.replaceAll("\\\\n", "\n");
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.getPrimaryCount();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList.getPrimaryElement(i));
		}
		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '%', "ds", args);
		return evaluateLiteral(interpreter, metrics, C_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		SeparatedList<C_Expression, PunctuationComma> argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		C_Expression expr = argList.first();
		AbstractToken which = expr.getWhich();
		if (!(which instanceof C_Literals))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		C_Literals str = (C_Literals) which;
		String fmt = str.literals.first().removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.getPrimaryCount();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList.getPrimaryElement(i));
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '%', "ds", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				C_Expression.class, pieces, source);
		return result;
	}
}
