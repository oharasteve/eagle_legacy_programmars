// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

package com.eagle.programmar.COBOL.OldTransform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.AssignmentEnum;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Initialize<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_InitializeStatement initializeStatement)
	{
		Expr zero = trans._target._createExpression.createNumber(0);
		String varName = trans._transCobolData.getFullVariableName(initializeStatement.what, null);
		Stmt asgStatement = trans._target._createStatement.createAssignment(varName,
				null, AssignmentEnum.EQUALS, zero, null, initializeStatement);
		return asgStatement;
	}
}
