// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.SQL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.17") SQL_Keyword RETURN = new SQL_Keyword("return");
	public @S(20) @OPT SQL_Expression expression;
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);

			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof SQL_CreateFunctionStatement)
				{
					SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) parent;
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
			EagleGenerator generator)
	{
		AbstractExpression expr = transformer.transformExpression(generator, expression);
		return generator.newReturnStatement(expr, this);
	}
}
