// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Function;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class AWK_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
{
	public @S(10) AWK_Keyword RETURN = new AWK_Keyword("RETURN");
	public @S(20) @OPT AWK_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expr != null && expr.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expr);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof AWK_Function)
				{
					AWK_Function func = (AWK_Function) parent;
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
		if (expr != null && expr.isPresent())
		{
			retExpr = transformer.transformExpression(generator, expr);
		}
		return generator.newReturnStatement(retExpr, this);
	}
}
