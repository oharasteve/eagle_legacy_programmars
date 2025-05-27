// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.Transform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.Statements.COBOL_StopStatement;
import com.eagle.programmar.COBOL.Transform.Transform_COBOL;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Stop<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_StopStatement stopStatement)
	{
		return trans._target._createStatement.createQuitStatement(stopStatement);
	}
}
