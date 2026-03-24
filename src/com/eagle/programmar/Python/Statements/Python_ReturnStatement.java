// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Python_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("compound_stmts.html#function-definitions") @NOSPACE Python_Keyword RETURN = new Python_Keyword(
			"return");
	public @S(20) @OPT Python_Keyword AWAIT = new Python_Keyword("await");
	public @S(30) @OPT Python_ExpressionList expressionList;
	public @S(40) @OPT Python_Comment comment;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expressionList.expressions.first());

		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Python_Function)
			{
				Python_Function func = (Python_Function) parent;
				func._returnMetrics.returned(val.typeName());
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
		if (expressionList != null && expressionList.isPresent())
		{
			if (expressionList.expressions.size() > 1)
			{
				throw new RuntimeException("Can't handle multi-value Returns yet");
			}
			Python_Expression expr = expressionList.expressions.first();
			retExpr = transformer.transformExpression(generator, expr);
		}
		return generator.newReturnStatement(retExpr, this);
	}

	public static Python_ComplexStatement generateReturn(Python_Expression ret, AbstractToken source)
	{
		Python_ReturnStatement retStmt = new Python_ReturnStatement();
		if (ret != null)
		{
			retStmt.expressionList = new Python_ExpressionList();
			retStmt.expressionList.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
			retStmt.expressionList.expressions.addPrimaryElement(ret);
			retStmt.expressionList.setPresent(true);
		}
		retStmt.setTransformationSource(source);
		return Python_Generator.wrapStatement(retStmt);
	}
}
