// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#854-the-return-statement") Powershell_Keyword RETURN = new Powershell_Keyword(
			"Return");
	public @S(20) @OPT Powershell_Expression expression;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof Powershell_Function)
				{
					Powershell_Function func = (Powershell_Function) parent;
					func._returnMetrics.returned(val.typeName());
					break;
				}
				parent = parent.getParent();
			}

			interpreter.pushEagleValue(val);
		}
		return Eagle_Statement_Result.RETURN;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression retExpr = null;
		if (expression != null && expression.isPresent())
		{
			retExpr = transformer.transformExpression(generator, expression);
		}
		return generator.newReturnStatement(retExpr, this);
	}
}
