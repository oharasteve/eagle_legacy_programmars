// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.OldTransform;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_COBOL_Expression<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Transform_COBOL_Expression(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans)
	{
	}

	public Expr transformExpression(COBOL_Expression expr)
	{
		throw new RuntimeException("Unable to handle expression: " + expr.getWhich().toString());
	}
}
