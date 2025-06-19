// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2010

package com.eagle.programmar.COBOL.OldTransform.Statements;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.COBOL.OldTransform.Transform_COBOL;
import com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;

public class COBOL_Transform_Evaluate<Lang extends AbstractLanguage, Cls extends AbstractClass, Stmt extends AbstractStatement, Meth extends AbstractMethod, Expr extends AbstractExpression, Var extends AbstractVariable, Type extends AbstractType>
{
	public Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans,
			COBOL_EvaluateStatement evaluateStatement)
	{
		throw new RuntimeException("Need to implement");
//		AbstractToken which = evaluateStatement.key.getWhich();
//		AbstractExpression keyValue;
//		if (which instanceof COBOL_EvaluateExpression)
//		{
//			COBOL_EvaluateExpression expr = (COBOL_EvaluateExpression) which;
//			keyValue = trans.transformExpression(expr.expr);
//		}
//		else if (which instanceof COBOL_EvaluateCondition)
//		{
//			COBOL_EvaluateCondition cond = (COBOL_EvaluateCondition) which;
//			keyValue = trans.transformExpression(cond.cond);
//		}
//		else throw new EagleTransformException("Can't handle evaluate key yet: " + which);
//		
//		AbstractToken previousIf = null;
//		AbstractToken firstIf = null;
//		for (COBOL_EvaluateWhenClause clause : evaluateStatement.whens._elements)
//		{
//			AbstractToken what = clause.value.getWhich();
//
//			if (what instanceof COBOL_Identifier_Reference)
//			{
//				COBOL_Identifier_Reference ref = (COBOL_Identifier_Reference) what;
//				String fullName = COBOL_Transform_Data.getFullVariableName(trans, ref, null);
//				String ifStmt = "if (" + fullName + " == " + keyValue + ") { } else { } ";
//				AbstractToken ifStatement = block.makeIfStatement();
//				gen.parseWithSource(ifStmt, ifStatement, what);
//				
//				if (previousIf != null)
//				{
//					block.setElseStatement(previousIf, ifStatement);
//				}
//				
//				for (COBOL_StatementOrComment statementOrComment : clause.statements._elements)
//				{
//					AbstractToken whichStmtComm = statementOrComment.getWhich();
//					if (whichStmtComm instanceof COBOL_Statement)
//					{
//						COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
//						AbstractToken newStatement = COBOL_Transform_Statement.transform(trans, method, oldStatement.getWhich());
//						block.addThenStatement(ifStatement, newStatement, oldStatement);
//					}
//				}
//				
//				if (firstIf == null) firstIf = ifStatement;
//				previousIf = ifStatement;
//			}
//			else if (what instanceof COBOL_Keyword)
//			{
//				COBOL_Keyword keyword = (COBOL_Keyword) what;
//				if ( ! (what.toString().equals("OTHER")))
//				{
//					throw new EagleTransformException("Can't handle evaluate keyword yet: " + keyword);
//				}
//				
//				for (COBOL_StatementOrComment statementOrComment : clause.statements._elements)
//				{
//					AbstractToken whichStmtComm = statementOrComment.getWhich();
//					if (whichStmtComm instanceof COBOL_Statement)
//					{
//						COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
//						AbstractToken newStatement = COBOL_Transform_Statement.transform(trans, method, oldStatement.getWhich());
//						block.addElseStatement(previousIf, newStatement, oldStatement);
//					}
//				}
//			}
//			else if (what instanceof COBOL_EvaluateCondition)
//			{
//				COBOL_EvaluateCondition cond = (COBOL_EvaluateCondition) what;
//				String condStr = COBOL_Transform_Expression.transform(cond.cond);
//				String ifStmt = "if (" + condStr + " == " + keyValue + ") { } else { } ";
//				AbstractToken ifStatement = block.makeIfStatement();
//				gen.parseWithSource(ifStmt, ifStatement, what);
//				
//				if (previousIf != null)
//				{
//					block.setElseStatement(previousIf, ifStatement);
//				}
//				
//				for (COBOL_StatementOrComment statementOrComment : clause.statements._elements)
//				{
//					AbstractToken whichStmtComm = statementOrComment.getWhich();
//					if (whichStmtComm instanceof COBOL_Statement)
//					{
//						COBOL_Statement oldStatement = (COBOL_Statement) whichStmtComm;
//						AbstractToken newStatement = COBOL_Transform_Statement.transform(trans, method, oldStatement.getWhich());
//						block.addThenStatement(ifStatement, newStatement, oldStatement);
//					}
//				}
//				
//				if (firstIf == null) firstIf = ifStatement;
//				previousIf = ifStatement;
//			}
//			else throw new EagleTransformException("Can't handle evaluate 'when' yet: " + what);
//		}
//		
//		return firstIf;
	}
}
