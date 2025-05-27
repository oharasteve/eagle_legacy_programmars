// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

package com.eagle.programmar.BNF.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.BNF_Expression;
import com.eagle.programmar.BNF.Expressions.BNF_Optional;
import com.eagle.programmar.BNF.Expressions.BNF_Rulename;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_BNF_Optional<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private int _optionals = 0;

	public void transformOptional(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			BNF_Optional optional, boolean isChoice, boolean inTokenSequence)
	{
		BNF_Expression expr = optional.expression;
		if (expr.terms.size() == 1 && expr.choices == null)
		{
			AbstractToken first = expr.terms.first().getWhich();
			if (first instanceof BNF_Literal)
			{
				trans._transformLiteral.transformLiteral(trans, cls, seq, (BNF_Literal) first, true, isChoice,
						inTokenSequence);
				return;
			}
			if (first instanceof BNF_Rulename)
			{
				trans._transformName.transformName(trans, cls, seq, (BNF_Rulename) first, true, isChoice,
						inTokenSequence);
				return;
			}
		}

		int qual = DATA_QUALIFIERS.OPTIONAL._value;
		if (isChoice) qual |= DATA_QUALIFIERS.CHOICE._value;
		if (inTokenSequence) qual |= DATA_QUALIFIERS.SEQUENCE._value;

		_optionals++;
		String className = Transform_BNF.fixClassName("Optional_" + _optionals);
		String varName = "optional_" + _optionals;
		trans.writeExpression(cls, className, expr);

		Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, className, null,
				null, optional);
		trans._target._createClass.addClassData(cls, dataStmt);
	}
}
