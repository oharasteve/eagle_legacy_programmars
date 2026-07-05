// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Procedure;
import com.eagle.programmar.PLI.Expressions.PLI_ParenthesizedExpression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class PLI_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("7.48") PLI_Keyword RETURN = new PLI_Keyword("RETURN");
	public @S(20) @OPT PLI_Expression expression;
	public @S(30) PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);

		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof PLI_Procedure)
			{
				PLI_Procedure proc = (PLI_Procedure) parent;
				proc._returnMetrics.returned(val.getType());
				break;
			}
			parent = parent.getParent();
		}

		interpreter.pushEagleValue(val);
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
			AbstractToken which1 = expression.getWhich();
			if (which1 instanceof PLI_ParenthesizedExpression)
			{
				PLI_ParenthesizedExpression paren = (PLI_ParenthesizedExpression) which1;
				AbstractToken which2 = paren.expr.getWhich();
				if (which2 instanceof PLI_Literal)
				{
					retExpr = generator.newStringFunction(TypeEnum.STRING, retExpr, this);
				}
			}
		}
		return generator.newReturnStatement(retExpr, this);
	}
}
