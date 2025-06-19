// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 9, 2016

package com.eagle.programmar.BNF.OldTransform;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.DATA_QUALIFIERS;
import com.eagle.oldGenerate.Old_Generate_Eagle_Statement.PRIVACY;
import com.eagle.programmar.BNF.Terminals.BNF_Literal;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_BNF_Literal<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private int _puncts = 0;

	public String transformLiteral(Transform_BNF<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, Cls cls, int seq,
			BNF_Literal literal, boolean optional, boolean isChoice, boolean inTokenSequence)
	{
		int qual = 0;
		if (optional) qual |= DATA_QUALIFIERS.OPTIONAL._value;
		if (isChoice) qual |= DATA_QUALIFIERS.CHOICE._value;
		if (inTokenSequence) qual |= DATA_QUALIFIERS.SEQUENCE._value;

		String originalTxt = literal.getValue();
		String txt = originalTxt;
		if (originalTxt.startsWith("'") && originalTxt.endsWith("'"))
		{
			txt = txt.substring(1, txt.length() - 1);
		}
		else if (originalTxt.startsWith("\"") && originalTxt.endsWith("\""))
		{
			txt = txt.substring(1, txt.length() - 1);
		}

		String typeName;
		String token;
		if (txt.matches("[A-Za-z]+"))
		{
			token = trans.foldUp(txt);
			typeName = "BNF_Keyword";
		}
		else
		{
			_puncts++;
			typeName = "BNF_Punctuation";
			token = "punct_" + _puncts;
		}

		Type type = trans._target._createClass.createType(typeName);
		ArrayList<AbstractExpression> txtExpr = new ArrayList<AbstractExpression>();
		txtExpr.add(trans._target._createExpression.createLiteral('"' + txt + '"'));
		Expr init = trans._target._createExpression.createNew(type, txtExpr);

		Stmt dataStmt = trans._target._createStatement.createData(PRIVACY.PUBLIC, qual, seq, token, typeName, init,
				null, literal);
		trans._target._createClass.addClassData(cls, dataStmt);
		return token;
	}
}
