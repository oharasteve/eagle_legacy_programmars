// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

package com.eagle.programmar.COBOL.Transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Statements.COBOL_AcceptStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_ComputeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_MoveStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
import com.eagle.programmar.COBOL.Statements.COBOL_StopStatement;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Accept;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Add;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Compute;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Display;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Evaluate;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Initialize;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Move;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Perform;
import com.eagle.programmar.COBOL.Transform.Statements.COBOL_Transform_Stop;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Transform_COBOL_Statement<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	private Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> _trans;

	private COBOL_Transform_Accept<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformAccept = new COBOL_Transform_Accept<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Add<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformAdd = new COBOL_Transform_Add<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Compute<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformCompute = new COBOL_Transform_Compute<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Display<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformDisplay = new COBOL_Transform_Display<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Evaluate<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformEvaluate = new COBOL_Transform_Evaluate<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Initialize<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformInitialize = new COBOL_Transform_Initialize<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Move<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformMove = new COBOL_Transform_Move<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Perform<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformPerform = new COBOL_Transform_Perform<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
	private COBOL_Transform_Stop<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transformStop = new COBOL_Transform_Stop<Lang, Cls, Stmt, Meth, Expr, Var, Type>();

	public Transform_COBOL_Statement(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans)
	{
		_trans = trans;
	}

	public Stmt transformStatement(COBOL_Statement mainStatement)
	{
		COBOL_AbstractStatement oldStatement = (COBOL_AbstractStatement) mainStatement.getWhich();
		Stmt newStatement;
		if (oldStatement instanceof COBOL_AcceptStatement)
		{
			newStatement = _transformAccept.transform(_trans, (COBOL_AcceptStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_AddStatement)
		{
			newStatement = _transformAdd.transform(_trans, (COBOL_AddStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_ComputeStatement)
		{
			newStatement = _transformCompute.transform(_trans, (COBOL_ComputeStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_DisplayStatement)
		{
			newStatement = _transformDisplay.transform(_trans, (COBOL_DisplayStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_EvaluateStatement)
		{
			newStatement = _transformEvaluate.transform(_trans, (COBOL_EvaluateStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_InitializeStatement)
		{
			newStatement = _transformInitialize.transform(_trans, (COBOL_InitializeStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_MoveStatement)
		{
			newStatement = _transformMove.transform(_trans, (COBOL_MoveStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_PerformStatement)
		{
			newStatement = _transformPerform.transform(_trans, (COBOL_PerformStatement) oldStatement);
		}
		else if (oldStatement instanceof COBOL_StopStatement)
		{
			newStatement = _transformStop.transform(_trans, (COBOL_StopStatement) oldStatement);
		}
		else
		{
			throw new RuntimeException(
					"Cannot handle COBOL statement: " + oldStatement + " at line " + (oldStatement.getStartLine() + 1));
		}

		return newStatement;
	}
}
