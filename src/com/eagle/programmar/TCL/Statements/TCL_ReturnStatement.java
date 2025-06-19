// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Procedure;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("TclCmd/return.html") TCL_Keyword RETURN = new TCL_Keyword("return");
	public @S(20) TCL_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
		
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof TCL_Procedure)
			{
				TCL_Procedure proc = (TCL_Procedure) parent;
				proc._returnMetrics.returned(val.typeName());
				break;
			}
			parent = parent.getParent();
		}

		return Eagle_Statement_Result.RETURN;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression retExpr = null;
		if (expr != null && expr.isPresent())
		{
			retExpr = transformer.transformExpression(generator, expr);
		}
		return generator.newReturnStatement(retExpr, this);
	}
}
