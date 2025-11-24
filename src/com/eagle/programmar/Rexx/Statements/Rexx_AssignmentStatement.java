// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Subscript;
import com.eagle.programmar.Rexx.Rexx_Variable;
import com.eagle.programmar.Rexx.Terminals.Rexx_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Rexx_AssignmentStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("concepts-assignments-symbols") Rexx_Variable variable;
	public @S(20) PunctuationEquals equals;
	public @S(30) Rexx_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue var = interpreter.findSymbol(variable.var.getValue());
		EagleValue val = interpreter.getEagleValue(expr);

		if (variable.subscript != null && variable.subscript.isPresent())
		{
			EagleHash hash = (EagleHash) var;
			if (hash == null)
			{
				hash = new EagleHash();
			}
			Rexx_Subscript sub = variable.subscript;
			Integer key = interpreter.getIntValue(sub.subscr);

			interpreter.setSymbol(variable, variable.var.getValue(), key, hash);
			hash.putValue(key, val);
		}
		else
		{
			interpreter.setSymbol(variable, variable.var.getValue(), val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String name = variable.var.getValue();
		AbstractExpression value = transformer.transformExpression(generator, expr);

		// Rexx doesn't have a Return statement. It assigns a value to the function name
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Rexx_Function)
			{
				Rexx_Function func = (Rexx_Function) parent;
				if (name.equals(func.id.getValue()))
				{
					AbstractExpression retExpr = transformer.transformExpression(generator, expr);
					return generator.newReturnStatement(retExpr, this);
				}
				break;
			}
			parent = parent.getParent();
		}

		// Normal assignment ... maybe with a subscript
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			AbstractExpression subscrExpr = null;
			AbstractToken which = variable.subscript.subscr.getWhich();
			if (which instanceof Rexx_Number)
			{
				Rexx_Number number = (Rexx_Number) which;
				subscrExpr = generator.newNumberExpression(number.getValue(),
						variable.subscript.subscr);
			}
			else if (which instanceof Rexx_Variable)
			{
				Rexx_Variable var = (Rexx_Variable) which;
				subscrExpr = generator.newVariableExpression(var.var.getValue(),
						SubscriptEnum.FIRST_IS_ZERO, null, variable.subscript.subscr);
			}
			else
			{
				throw new RuntimeException("Unexpected subscript: " + which);
			}

			AbstractExpression hashExpr = generator.newHashAssignment(name, subscrExpr, value, this);
			return generator.newExpressionStatement(hashExpr, this);
		}

		if (name.equalsIgnoreCase("true") || name.equalsIgnoreCase("false"))
		{
			// Sorry, cannot redefine true or false
			return null;
		}

		// No subscript given
		AbstractExpression asgExpr1 = generator.newAssignmentExpression(name,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, value, this);
		return generator.newExpressionStatement(asgExpr1, this);
	}
}
