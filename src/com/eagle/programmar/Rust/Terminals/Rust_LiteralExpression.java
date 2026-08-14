// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2026

package com.eagle.programmar.Rust.Terminals;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
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

public class Rust_LiteralExpression extends TerminalLiteralExpression
{
	public static String interpret(EagleInterpreter interpreter,
			SeparatedList<Rust_Expression, PunctuationComma> argList, ArgumentsMetrics metrics)
	{
		Rust_Expression expr = argList.first();
		String fmt = interpreter.getStrValue(expr);
		
		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (int i = 1; i < argList.getPrimaryCount(); i++)
		{
			args.add(argList.getPrimaryElement(i));
		}

		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '{', "}", args);
		return evaluateLiteral(interpreter, metrics, Rust_Expression.class, pieces);
	}
	
	public static AbstractExpression transform(EagleTransformer transformer,
		EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator,
		SeparatedList<Rust_Expression, PunctuationComma> argList, ArrayList<TypeEnum> metrics, AbstractToken source)
	{
		Rust_Expression expr = argList.first();
		AbstractToken which = expr.getWhich();
		if (!(which instanceof Rust_Literal))
		{
			throw new RuntimeException("Format must be a literal, not " + which);
		}
		Rust_Literal str = (Rust_Literal) which;
		String fmt = str.removeQuotes();

		ArrayList<PrecedenceChooser> args = new ArrayList<PrecedenceChooser>();
		for (int i = 1; i < argList.getPrimaryCount(); i++)
		{
			args.add(argList.getPrimaryElement(i));
		}

		ArrayList<LiteralPiece> pieces = parsePercent(fmt, '{', "}", args);
		AbstractExpression result = TerminalLiteralExpression.compileLiteral(transformer, generator, metrics,
				Rust_Expression.class, pieces, source);
		return result;
	}
}
