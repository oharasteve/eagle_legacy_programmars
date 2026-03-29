// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

namespace com.eagle.programmar.COBOL.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using com.eagle.programmar.COBOL.OldTransform.Statements;
	using COBOL_AcceptStatement = com.eagle.programmar.COBOL.Statements.COBOL_AcceptStatement;
	using COBOL_AddStatement = com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
	using COBOL_ComputeStatement = com.eagle.programmar.COBOL.Statements.COBOL_ComputeStatement;
	using COBOL_DisplayStatement = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
	using COBOL_EvaluateStatement = com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement;
	using COBOL_InitializeStatement = com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
	using COBOL_MoveStatement = com.eagle.programmar.COBOL.Statements.COBOL_MoveStatement;
	using COBOL_PerformStatement = com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
	using COBOL_StopStatement = com.eagle.programmar.COBOL.Statements.COBOL_StopStatement;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Transform_COBOL_Statement<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
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

		public virtual Stmt transformStatement(COBOL_Statement mainStatement)
		{
			COBOL_AbstractStatement oldStatement = (COBOL_AbstractStatement) mainStatement.getWhich();
			Stmt newStatement;
			if (oldStatement is COBOL_AcceptStatement)
			{
				newStatement = _transformAccept.transform(_trans, (COBOL_AcceptStatement) oldStatement);
			}
			else if (oldStatement is COBOL_AddStatement)
			{
				newStatement = _transformAdd.transform(_trans, (COBOL_AddStatement) oldStatement);
			}
			else if (oldStatement is COBOL_ComputeStatement)
			{
				newStatement = _transformCompute.transform(_trans, (COBOL_ComputeStatement) oldStatement);
			}
			else if (oldStatement is COBOL_DisplayStatement)
			{
				newStatement = _transformDisplay.transform(_trans, (COBOL_DisplayStatement) oldStatement);
			}
			else if (oldStatement is COBOL_EvaluateStatement)
			{
				newStatement = _transformEvaluate.transform(_trans, (COBOL_EvaluateStatement) oldStatement);
			}
			else if (oldStatement is COBOL_InitializeStatement)
			{
				newStatement = _transformInitialize.transform(_trans, (COBOL_InitializeStatement) oldStatement);
			}
			else if (oldStatement is COBOL_MoveStatement)
			{
				newStatement = _transformMove.transform(_trans, (COBOL_MoveStatement) oldStatement);
			}
			else if (oldStatement is COBOL_PerformStatement)
			{
				newStatement = _transformPerform.transform(_trans, (COBOL_PerformStatement) oldStatement);
			}
			else if (oldStatement is COBOL_StopStatement)
			{
				newStatement = _transformStop.transform(_trans, (COBOL_StopStatement) oldStatement);
			}
			else
			{
				throw new Exception("Cannot handle COBOL statement: " + oldStatement + " at line " + (oldStatement.getStartLine() + 1));
			}

			return newStatement;
		}
	}

}
