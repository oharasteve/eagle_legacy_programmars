// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2026

package com.eagle.programmar.Delphi.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalLiteralExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter,
			Delphi_Argument_List argList, ArgumentsMetrics metrics)
	{
		String fmt = interpreter.getStrValue(argList.exprs.first());
		fmt = fmt.replaceAll("\\\\n", "\n");
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.exprs.getPrimaryCount();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList.exprs.getPrimaryElement(i));
		}
		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '%', "ds", args);
		return evaluateLiteral(interpreter, metrics, Delphi_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		Delphi_Argument_List argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		Delphi_Expression expr = argList.exprs.first();
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Delphi_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Delphi_Literal str = (Delphi_Literal) which;
		String fmt = str.removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		int numArgs = argList.exprs.getPrimaryCount();
		for (int i = 1; i < numArgs; i++)
		{
			args.add(argList.exprs.getPrimaryElement(i));
		}
		ArrayList<LiteralPiece> pieces = TerminalLiteralExpression.parsePercent(fmt, '%', "ds", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Delphi_Expression.class, pieces, source);
		return result;
	}
}
