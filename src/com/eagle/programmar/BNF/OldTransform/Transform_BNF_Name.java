// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

package com.eagle.programmar.BNF.OldTransform;

import com.eagle.core.AbstractLanguage;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.Expressions.BNF_Rulename;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_BNF_Name<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public String transformName(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			BNF_Rulename name, boolean optional, boolean isChoice, boolean inTokenSequence)
	{
		int qual = 0;
		if (optional) qual |= DATA_QUALIFIERS.OPTIONAL._value;
		if (isChoice) qual |= DATA_QUALIFIERS.CHOICE._value;
		if (inTokenSequence) qual |= DATA_QUALIFIERS.SEQUENCE._value;

		// Three cases:
		// I - token becomes just token
		// II - token+ becomes TokenList<token>
		// III - token* becomes @OPT TokenList<token>

		String id = name.ref.getValue();
		String varName = Transform_BNF.fixVarName(id);

		String className;
		int len = id.length();
		char last = id.charAt(len - 1);
		if (Character.isDigit(last))
		{
			className = Transform_BNF.fixClassName(id.substring(0, len - 1)); // use 'form' instead of 'form1' for class
																				// name
		}
		else
		{
			className = Transform_BNF.fixClassName(id);
		}

		String typeName = className;
		if (name.starOrPlus.isPresent())
		{
			typeName = "TokenList<" + className + ">";
			String starOrPlus = name.starOrPlus.getValue();
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
		}

		Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, varName, typeName, null,
				null, name);
		trans._target._createClass.addClassData(cls, dataStmt);
		return className;
	}
}
