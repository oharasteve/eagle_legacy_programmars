// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

namespace com.eagle.programmar.COBOL.OldTransform.Statements
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_StatementOrComment = com.eagle.programmar.COBOL.COBOL_StatementOrComment;
	using com.eagle.programmar.COBOL.OldTransform;
	using COBOL_PerformClause = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause;
	using COBOL_PerformUntil = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
	using COBOL_PerformVarying = com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
	using COBOL_PerformInline = com.eagle.programmar.COBOL.Statements.COBOL_PerformInline;
	using COBOL_PerformParagraph = com.eagle.programmar.COBOL.Statements.COBOL_PerformParagraph;
	using COBOL_PerformStatement = com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class COBOL_Transform_Perform<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_PerformStatement performStatement)
		{
			AbstractToken whichPerform = performStatement.what.getWhich();
			if (whichPerform is COBOL_PerformParagraph)
			{
				return transformPerformParagraph(trans, (COBOL_PerformParagraph) whichPerform);
			}

			if (whichPerform is COBOL_PerformInline)
			{
				return transformPerformInline(trans, (COBOL_PerformInline) whichPerform);
			}

			throw new Exception("Can't handle this perform yet: " + whichPerform);
		}

		private Stmt transformPerformInline(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_PerformInline inline)
		{
			AbstractToken token = inline.clauseList.first().getWhich();
			if (token is COBOL_PerformClause.COBOL_PerformVarying)
			{
				COBOL_PerformClause.COBOL_PerformVarying varying = (COBOL_PerformClause.COBOL_PerformVarying) token;

				// Collect all the inline statements
				List<Stmt> statements = new List<Stmt>();
				foreach (COBOL_StatementOrComment statementOrComment in inline.statements._elements)
				{
					AbstractToken whichStmtComm = statementOrComment.getWhich();
					if (whichStmtComm is COBOL_Statement)
					{
						COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
						Stmt newStatement = trans.transformStatement(oldStatement);
						statements.Add(newStatement);
					}
				}
				Stmt action = trans._target._createStatement.createStatementBlock(statements, inline);

				string loopVar = trans._transCobolData.getFullVariableName(varying.id, null);
				Expr initVal = trans.transformExpression(varying.from);
				Expr incrVal = trans.transformExpression(varying.by);
				Expr stopVal = findStopValue(trans, varying);

				if (stopVal != null)
				{
					Stmt forStatement = trans._target._createStatement.createForRangeStatement(loopVar, initVal, stopVal, incrVal, action, varying);
					return forStatement;
				}
			}

			if (token is COBOL_PerformClause.COBOL_PerformUntil)
			{
				COBOL_PerformClause.COBOL_PerformUntil until = (COBOL_PerformClause.COBOL_PerformUntil) token;

				// Collect all the inline statements
				List<AbstractStatement> actions = new List<AbstractStatement>();
				foreach (COBOL_StatementOrComment statementOrComment in inline.statements._elements)
				{
					AbstractToken whichStmtComm = statementOrComment.getWhich();
					if (whichStmtComm is COBOL_Statement)
					{
						COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
						Stmt newStatement = trans.transformStatement(oldStatement);
						actions.Add(newStatement);
					}
				}

				Expr termCond = trans.transformExpression(until.condition);
				Stmt doStatement = trans._target._createStatement.createDoUntilStatement(termCond, actions, until);
				return doStatement;
			}

			throw new Exception("Can't handle this inline perform yet: " + inline);
		}

		private Stmt transformPerformParagraph(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_PerformParagraph perfPara)
		{
			string para = perfPara.performStartParagraph.ToString();

			Var var = trans._target._createExpression.createVariable(para);
			Expr fnCall = trans._target._createExpression.createMethodCall(var, null);
			Stmt action = trans._target._createStatement.createFunctionCall(fnCall, perfPara);

			if (perfPara.clauseList != null)
			{
				// Need to go through the list in reverse order
				List<COBOL_PerformClause> reversedList = new List<COBOL_PerformClause>();
				foreach (COBOL_PerformClause clause in perfPara.clauseList._elements)
				{
					reversedList.Insert(0, clause); // Puts each one at the beginning
				}

				foreach (COBOL_PerformClause clause in reversedList)
				{
					AbstractToken which = clause.getWhich();
					if (which is COBOL_PerformClause.COBOL_PerformVarying)
					{
						COBOL_PerformClause.COBOL_PerformVarying varying = (COBOL_PerformClause.COBOL_PerformVarying) which;
						string loopVar = trans._transCobolData.getFullVariableName(varying.id, null);
						Expr initVal = trans.transformExpression(varying.from);
						Expr incrVal = trans.transformExpression(varying.by);
						Expr stopVal = findStopValue(trans, varying);
						// if (stopVal == null) return null;
						Stmt forStatement = trans._target._createStatement.createForRangeStatement(loopVar, initVal, stopVal, incrVal, action, varying);
						action = forStatement;
					}
					else if (which is COBOL_PerformClause.COBOL_PerformUntil)
					{
						COBOL_PerformClause.COBOL_PerformUntil until = (COBOL_PerformClause.COBOL_PerformUntil) which;
						Expr untilExpression = trans.transformExpression(until.condition);
						List<AbstractStatement> actions = new List<AbstractStatement>();
						actions.Add(action);
						Stmt doStatement = trans._target._createStatement.createDoUntilStatement(untilExpression, actions, until);
						action = doStatement;
					}
					else
					{
						throw new Exception("Can't handle PERFORM clause yet: " + clause);
					}
				}
			}

			return action;
		}

		// PERFORM P VARYING I FROM 1 BY 1 UNTIL I = 10
		// Need to remove the "I = " part from the expression.
		private Expr findStopValue(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_PerformClause.COBOL_PerformVarying varying)
		{
			return default(Expr);
		}
	}

}
