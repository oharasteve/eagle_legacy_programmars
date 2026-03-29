// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

namespace com.eagle.programmar.COBOL.OldTransform.Statements
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_UserVariable = com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
	using com.eagle.programmar.COBOL.OldTransform;
	using com.eagle.programmar.COBOL.OldTransform;
	using COBOL_AddStatement = com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
	using COBOL_AddNoGiving = com.eagle.programmar.COBOL.Statements.COBOL_AddStatement.COBOL_AddNoGiving;
	using COBOL_AddOnSizeError = com.eagle.programmar.COBOL.Statements.COBOL_AddStatement.COBOL_AddOnSizeError;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;

	public class COBOL_Transform_Add<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_AddStatement addStatement)
		{
			AbstractToken which = addStatement.type.getWhich();
			if (!(which is COBOL_AddStatement.COBOL_AddNoGiving))
			{
				return default(Stmt);
			}

			COBOL_AddStatement.COBOL_AddNoGiving noGiving = (COBOL_AddStatement.COBOL_AddNoGiving) which;
			AbstractToken variable = noGiving.addTo.var.getWhich();
			Stmt asgStatement = null;
			string targetName = null;
			if (variable is COBOL_UserVariable)
			{
				COBOL_UserVariable userVar = (COBOL_UserVariable) variable;
				Expr expr = trans._transCobolExpr.transformExpression(noGiving.expr);
				targetName = trans._transCobolData.getFullVariableName(userVar.id, null);
				asgStatement = trans._target._createStatement.createAssignment(targetName, null, AssignmentEnum.PLUS_EQUALS, expr, null, addStatement);
			}

			if (addStatement.onErrorList == null || addStatement.onErrorList.size() == 0)
			{
				return asgStatement;
			}

			if (addStatement.onErrorList._elements.size() > 1)
			{
				throw new Exception("Can only handle one ON SIZE ERROR clause now");
			}

			variable = noGiving.addTo.var.getWhich();
			int max = 0;
			COBOL_AddStatement.COBOL_AddOnSizeError err = null;
			if (variable is COBOL_UserVariable)
			{
				COBOL_UserVariable userVar = (COBOL_UserVariable) variable;
				err = addStatement.onErrorList.first();
				max = Transform_COBOL_Data.getMaximumValue(userVar.id);
			}

			Expr targetVar = trans._target._createExpression.createVariableExpression(targetName, null);
			Expr minVar = trans._target._createExpression.createNumber(-max);
			Expr maxVar = trans._target._createExpression.createNumber(max);
			Expr ifCondition;

			if (err.NOT.isPresent())
			{
				// (targetName > -max && targetName < max)
				Expr left = trans._target._createExpression.createRelational(targetVar, RelationalEnum.GREATER_THAN, minVar, variable);
				Expr right = trans._target._createExpression.createRelational(targetVar, RelationalEnum.LESS_THAN, maxVar, variable);
				ifCondition = trans._target._createExpression.createAnd(left, right, null);
			}
			else
			{
				// (targetName <= -max || targetName >= max)
				Expr left = trans._target._createExpression.createRelational(targetVar, RelationalEnum.LESS_EQUALS, minVar, variable);
				Expr right = trans._target._createExpression.createRelational(targetVar, RelationalEnum.GREATER_EQUALS, maxVar, variable);
				ifCondition = trans._target._createExpression.createOr(left, right, null);
			}

			List<Stmt> errorActions = new List<Stmt>();
			foreach (COBOL_Statement oldStatement in err.actions._elements)
			{
				Stmt stmt = trans._transCobolStmt.transformStatement(oldStatement);
				errorActions.Add(stmt);
			}
			Stmt thenBlock = trans._target._createStatement.createStatementBlock(errorActions, err);

			List<Stmt> elseStatement = new List<Stmt>();
			elseStatement.Add(asgStatement);
			Stmt elseBlock = trans._target._createStatement.createStatementBlock(elseStatement, addStatement);

			Stmt ifStatement = trans._target._createStatement.createIfStatement1(ifCondition, thenBlock, elseBlock, err);
			return ifStatement;
		}
	}

}
