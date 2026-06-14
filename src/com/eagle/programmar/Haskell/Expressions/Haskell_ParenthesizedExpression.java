// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 2, 2026

package com.eagle.programmar.Haskell.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Haskell_ParenthesizedExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT TokenList<Haskell_Expression> expressions;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// See if we are calling a "prefix notation" function, like (myfunc 1 2 3)
		int nValues = expressions.size();
		Haskell_Expression first = null;
		if (nValues >= 1)
		{
			first = expressions.first();
			if (first.getWhich() instanceof Haskell_Identifier_Reference)
			{
				Haskell_Identifier_Reference ref = (Haskell_Identifier_Reference) first.getWhich();
				String name = ref.getValue();
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn != null)
				{
					Haskell_Function func = (Haskell_Function) fn;
					ArrayList<Haskell_Expression> args = new ArrayList<Haskell_Expression>();
					for (int i = 1; i < nValues; i++)   // Toss first arg which is the function name
					{
						args.add(expressions._elements.get(i));
					}
					func.call(interpreter, name, args);
					return;
				}
			}
		}
		
		if (nValues == 0)
		{
			interpreter.pushBool(false);	// () is NIL
		}
		else if (nValues == 1)
		{
			interpreter.tryToInterpret(first);
		}
		else
		{
			throw new RuntimeException("Unknown parentheses expression: " + this + " nValues=" + nValues);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (expressions.size() == 1)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expressions.first());
			return generator.newParenthesizedExpression(theExpr, this);
		}
		throw new RuntimeException("Unknown parenthese expression");
	}
}
