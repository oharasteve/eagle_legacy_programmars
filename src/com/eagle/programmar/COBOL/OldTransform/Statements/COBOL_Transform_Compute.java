// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

package com.eagle.programmar.COBOL.OldTransform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.Statements.COBOL_ComputeStatement;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator.AssignmentEnum;

public class COBOL_Transform_Compute<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_ComputeStatement computeStatement)
	{
		Expr expr = trans._transCobolExpr.transformExpression(computeStatement.expr);
		String targetName = trans._transCobolData.getFullVariableName(computeStatement.var, null);
		Stmt asgStatement = trans._target._createStatement.createAssignment(targetName, null, AssignmentEnum.EQUALS,
				expr,
				null, computeStatement);
		return asgStatement;
	}
}
