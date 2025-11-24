// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.OldTransform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.Statements.COBOL_MoveStatement;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator.AssignmentEnum;

public class COBOL_Transform_Move<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_MoveStatement moveStatement)
	{
		AbstractToken variable = moveStatement.var.getWhich();
		if (variable instanceof COBOL_UserVariable)
		{
			COBOL_UserVariable userVar = (COBOL_UserVariable) variable;
			String targetName = trans._transCobolData.getFullVariableName(userVar.id, null);
			Expr source = trans._transCobolExpr.transformExpression(moveStatement.expr);
			Stmt asgStatement = trans._target._createStatement.createAssignment(targetName,
					null, AssignmentEnum.EQUALS, source, null, moveStatement);
			return asgStatement;
		}
		return null;
	}
}
