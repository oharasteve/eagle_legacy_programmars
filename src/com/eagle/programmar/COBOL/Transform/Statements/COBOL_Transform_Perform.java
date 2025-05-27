// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 27, 2010

package com.eagle.programmar.COBOL.Transform.Statements;

import java.util.ArrayList;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformInline;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformParagraph;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
import com.eagle.programmar.COBOL.Transform.Transform_COBOL;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Perform<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_PerformStatement performStatement)
	{
		AbstractToken whichPerform = performStatement.what.getWhich();
		if (whichPerform instanceof COBOL_PerformParagraph)
		{
			return transformPerformParagraph(trans, (COBOL_PerformParagraph) whichPerform);
		}

		if (whichPerform instanceof COBOL_PerformInline)
		{
			return transformPerformInline(trans, (COBOL_PerformInline) whichPerform);
		}

		throw new RuntimeException("Can't handle this perform yet: " + whichPerform);
	}

	private Stmt transformPerformInline(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_PerformInline inline)
	{
		AbstractToken token = inline.clauseList.first().getWhich();
		if (token instanceof COBOL_PerformVarying)
		{
			COBOL_PerformVarying varying = (COBOL_PerformVarying) token;

			// Collect all the inline statements
			ArrayList<Stmt> statements = new ArrayList<Stmt>();
			for (COBOL_StatementOrComment statementOrComment : inline.statements._elements)
			{
				AbstractToken whichStmtComm = statementOrComment.getWhich();
				if (whichStmtComm instanceof COBOL_Statement)
				{
					COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
					Stmt newStatement = trans.transformStatement(oldStatement);
					statements.add(newStatement);
				}
			}
			Stmt action = trans._target._createStatement.createStatementBlock(statements, inline);

			String loopVar = trans._transCobolData.getFullVariableName(varying.id, null);
			Expr initVal = trans.transformExpression(varying.from);
			Expr incrVal = trans.transformExpression(varying.by);
			Expr stopVal = findStopValue(trans, varying);

			if (stopVal != null)
			{
				Stmt forStatement = trans._target._createStatement.createForRangeStatement(loopVar, initVal, stopVal, incrVal, action, varying);
				return forStatement;
			}
		}

		if (token instanceof COBOL_PerformUntil)
		{
			COBOL_PerformUntil until = (COBOL_PerformUntil) token;

			// Collect all the inline statements
			ArrayList<AbstractStatement> actions = new ArrayList<AbstractStatement>();
			for (COBOL_StatementOrComment statementOrComment : inline.statements._elements)
			{
				AbstractToken whichStmtComm = statementOrComment.getWhich();
				if (whichStmtComm instanceof COBOL_Statement)
				{
					COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
					Stmt newStatement = trans.transformStatement(oldStatement);
					actions.add(newStatement);
				}
			}

			Expr termCond = trans.transformExpression(until.condition);
			Stmt doStatement = trans._target._createStatement.createDoUntilStatement(
					termCond, actions, until);
			return doStatement;
		}

		throw new RuntimeException("Can't handle this inline perform yet: " + inline);
	}

	private Stmt transformPerformParagraph(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_PerformParagraph perfPara)
	{
		String para = perfPara.performStartParagraph.toString();

		Var var = trans._target._createExpression.createVariable(para);
		Expr fnCall = trans._target._createExpression.createMethodCall(var, null);
		Stmt action = trans._target._createStatement.createFunctionCall(fnCall, perfPara);

		if (perfPara.clauseList != null)
		{
			// Need to go through the list in reverse order
			ArrayList<COBOL_PerformClause> reversedList = new ArrayList<COBOL_PerformClause>();
			for (COBOL_PerformClause clause : perfPara.clauseList._elements)
			{
				reversedList.add(0, clause); // Puts each one at the beginning
			}

			for (COBOL_PerformClause clause : reversedList)
			{
				AbstractToken which = clause.getWhich();
				if (which instanceof COBOL_PerformVarying)
				{
					COBOL_PerformVarying varying = (COBOL_PerformVarying) which;
					String loopVar = trans._transCobolData.getFullVariableName(varying.id, null);
					Expr initVal = trans.transformExpression(varying.from);
					Expr incrVal = trans.transformExpression(varying.by);
					Expr stopVal = findStopValue(trans, varying);
					// if (stopVal == null) return null;
					Stmt forStatement = trans._target._createStatement.createForRangeStatement(loopVar, initVal, stopVal, incrVal, action, varying);
					action = forStatement;
				}
				else if (which instanceof COBOL_PerformUntil)
				{
					COBOL_PerformUntil until = (COBOL_PerformUntil) which;
					Expr untilExpression = trans.transformExpression(until.condition);
					ArrayList<AbstractStatement> actions = new ArrayList<AbstractStatement>();
					actions.add(action);
					Stmt doStatement = trans._target._createStatement.createDoUntilStatement(
							untilExpression, actions, until);
					action = doStatement;
				}
				else
					throw new RuntimeException("Can't handle PERFORM clause yet: " + clause);
			}
		}

		return action;
	}

	// PERFORM P VARYING I FROM 1 BY 1 UNTIL I = 10
	// Need to remove the "I = " part from the expression.
	private Expr findStopValue(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_PerformVarying varying)
	{
		return null;
	}
}
