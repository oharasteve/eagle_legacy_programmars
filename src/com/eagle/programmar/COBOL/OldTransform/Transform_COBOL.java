// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2010

package com.eagle.programmar.COBOL.OldTransform;

import com.eagle.core.AbstractLanguage;
import com.eagle.oldGenerate.Old_Generate_Eagle;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Program_Complete;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.Transform_Eagle;

public class Transform_COBOL<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
		extends Transform_Eagle
{
	public Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> _target;

	// Source side
	public Transform_COBOL_Statement<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolStmt = new Transform_COBOL_Statement<Lang, Cls, Stmt, Meth, Expr, Var, Type>(
			this);
	public Transform_COBOL_Expression<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolExpr = new Transform_COBOL_Expression<Lang, Cls, Stmt, Meth, Expr, Var, Type>(
			this);
	public Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolData = new Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type>(
			this);

	public Transform_COBOL(Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> target)
	{
		_target = target;
	}

	@Override
	public void transformFromXML(AbstractLanguage pgm, String sourceName, String targetName)
	{
		COBOL_Program_Complete program = (COBOL_Program_Complete) pgm;

		// Add in a main program
		_target.createEmptyClass(targetName);

		Transform_COBOL_Identification<Lang, Cls, Stmt, Meth, Expr, Var, Type> transId = new Transform_COBOL_Identification<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		transId.transformIdentificationDivision(this, program);

		Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type> transData = new Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type>(
				this);
		transData.transformDataDivision(program);

		Transform_COBOL_Procedure<Lang, Cls, Stmt, Meth, Expr, Var, Type> transProc = new Transform_COBOL_Procedure<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
		transProc.transformProcedureDivision(this, program);

		// Wow, this is pretty nasty!
		COBOL_Paragraph_Definition source = program.procedureDiv.sections.first().paragraphs.first().paragraphHeaders
				.first().paragraphName;
		String firstPara = source.toString();
		_target.addMain(sourceName, firstPara);
	}

	// Change "FIND-FIXED-ACCT" to "findFixedAcct"
	public static String fixName(String name)
	{
		if (name.indexOf('-') < 0) return name;

		StringBuffer sb = new StringBuffer();

		char firstCh = name.charAt(0);
		if (Character.isDigit(firstCh)) sb.append('_');

		boolean foundDash = false;
		for (char ch : name.toCharArray())
		{
			if (ch == '-')
			{
				foundDash = true;
			}
			else
			{
				if (foundDash)
				{
					sb.append(Character.toUpperCase(ch));
					foundDash = false;
				}
				else
				{
					sb.append(Character.toLowerCase(ch));
				}
			}
		}
		return sb.toString();
	}

	@Override
	public Expr transformExpression(AbstractExpression expr)
	{
		Expr newExpr = _transCobolExpr.transformExpression((COBOL_Expression) expr);
		return newExpr;
	}

	@Override
	public Stmt transformStatement(AbstractStatement stmt)
	{
		Stmt newStmt = _transCobolStmt.transformStatement((COBOL_Statement) stmt);
		return newStmt;
	}
}
