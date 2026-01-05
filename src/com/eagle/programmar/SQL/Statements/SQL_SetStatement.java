// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_SetStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("sql_set.asp") SQL_Keyword SET = new SQL_Keyword("SET");
	public @S(20) SQL_VariableExpression var;
	public @S(30) PunctuationEquals equals;
	public @S(40) SQL_Expression expr;
	public @S(50) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		SQL_Identifier_Reference id = var.variable.ids.first();
		EagleValue val = interpreter.getEagleValue(expr);
		
		if (var.variable.AT != null && var.variable.AT.isPresent())
		{
			// Session variables (like @result) need to have global scope
			EagleScope saveScope = interpreter._symbolTable.getScope();
			interpreter._symbolTable.setScope(interpreter._lang.getScope());
			interpreter.setSymbol(var, id.getValue(), val);
			interpreter._symbolTable.setScope(saveScope);
		}
		else
		{
			interpreter.setSymbol(var, id.getValue(), val);
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscrExpr = null;
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.variable.ids.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, AssignmentEnum.EQUALS, value, this);
		AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
		return exprStmt;
	}
}
