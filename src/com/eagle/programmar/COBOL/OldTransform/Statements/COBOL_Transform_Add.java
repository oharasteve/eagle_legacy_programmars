// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

package com.eagle.programmar.COBOL.OldTransform.Statements;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.RelationalEnum;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL_Data;
import com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_AddStatement.COBOL_AddNoGiving;
import com.eagle.programmar.COBOL.Statements.COBOL_AddStatement.COBOL_AddOnSizeError;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Add<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_AddStatement addStatement)
	{
		AbstractToken which = addStatement.type.getWhich();
		if (!(which instanceof COBOL_AddNoGiving)) return null;

		COBOL_AddNoGiving noGiving = (COBOL_AddNoGiving) which;
		AbstractToken variable = noGiving.addTo.var.getWhich();
		Stmt asgStatement = null;
		String targetName = null;
		if (variable instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable userVar = (COBOL_UserVariable) variable;
			Expr expr = trans._transCobolExpr.transformExpression(noGiving.expr);
			targetName = trans._transCobolData.getFullVariableName(userVar.id, null);
			asgStatement = trans._target._createStatement.createAssignment(targetName,
					null, AssignmentEnum.PLUS_EQUALS, expr, null, addStatement);
		}

		if (addStatement.onErrorList == null || addStatement.onErrorList.size() == 0)
		{
			return asgStatement;
		}

		if (addStatement.onErrorList._elements.size() > 1)
		{
			throw new RuntimeException("Can only handle one ON SIZE ERROR clause now");
		}

		variable = noGiving.addTo.var.getWhich();
		int max = 0;
		COBOL_AddOnSizeError err = null;
		if (variable instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable userVar = (COBOL_UserVariable) variable;
			err = addStatement.onErrorList.first();
			max = Transform_COBOL_Data.getMaximumValue(userVar.id);
		}

		Expr targetVar = trans._target._createExpression.createVariableExpression(targetName, null);
		Expr minVar = trans._target._createExpression.createNumber(-max);
		Expr maxVar = trans._target._createExpression.createNumber(max);
		Expr ifCondition;

		if (err.NOT.isPresent())
		{
			// (targetName > -max && targetName < max)
			Expr left = trans._target._createExpression.createRelational(targetVar, RelationalEnum.GREATER_THAN, minVar,
					variable);
			Expr right = trans._target._createExpression.createRelational(targetVar, RelationalEnum.LESS_THAN, maxVar,
					variable);
			ifCondition = trans._target._createExpression.createAnd(left, right, null);
		}
		else
		{
			// (targetName <= -max || targetName >= max)
			Expr left = trans._target._createExpression.createRelational(targetVar, RelationalEnum.LESS_EQUALS, minVar,
					variable);
			Expr right = trans._target._createExpression.createRelational(targetVar, RelationalEnum.GREATER_EQUALS,
					maxVar, variable);
			ifCondition = trans._target._createExpression.createOr(left, right, null);
		}

		ArrayList<Stmt> errorActions = new ArrayList<Stmt>();
		for (COBOL_Statement oldStatement : err.actions._elements)
		{
			Stmt stmt = trans._transCobolStmt.transformStatement(oldStatement);
			errorActions.add(stmt);
		}
		Stmt thenBlock = trans._target._createStatement.createStatementBlock(errorActions, err);

		ArrayList<Stmt> elseStatement = new ArrayList<Stmt>();
		elseStatement.add(asgStatement);
		Stmt elseBlock = trans._target._createStatement.createStatementBlock(elseStatement, addStatement);

		Stmt ifStatement = trans._target._createStatement.createIfStatement1(
				ifCondition, thenBlock, elseBlock, err);
		return ifStatement;
	}
}
