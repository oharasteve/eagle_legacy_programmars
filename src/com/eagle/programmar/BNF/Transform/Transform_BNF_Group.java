// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

package com.eagle.programmar.BNF.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.generate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.Expressions.BNF_Group;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_BNF_Group<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private int _groups = 0;

	public void transformGroup(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			BNF_Group group, boolean inTokenSequence)
	{
		_groups++;
		int qual = 0;
		if (inTokenSequence) qual |= DATA_QUALIFIERS.SEQUENCE._value;

		// Three cases:
		// I - (group) becomes just GroupClass
		// II - (group)+ becomes TokenList<GroupClass>
		// III - (group)* becomes @OPT TokenList<GroupClass>

		String className = Transform_BNF.fixClassName("Group_" + _groups);
		String varName = "group_" + _groups;

		trans.writeExpression(cls, className, group.expression);

		String typeName = className;
		if (group.starOrPlus.isPresent())
		{
			String starOrPlus = group.starOrPlus.getValue();
			switch (starOrPlus)
			{
			case "*":
				qual |= DATA_QUALIFIERS.OPTIONAL._value;
				break;
			case "+":
				break;
			default:
				throw new RuntimeException("Expected '*' or '+', not " + starOrPlus);
			}
			typeName = "TokenList<" + className + ">";
		}

		Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, typeName, null,
				null, group);
		trans._target._createClass.addClassData(cls, dataStmt);
	}
}
