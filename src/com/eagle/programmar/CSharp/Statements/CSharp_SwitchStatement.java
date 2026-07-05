// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.SwitchMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_SwitchStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleScopeInterface,
				EagleTransformableStatement
{
	public @S(10) @NEWLINE @DOC("statements/selection-statements") CSharp_Keyword SWITCH = new CSharp_Keyword("switch");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression value;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<CSharp_SwitchCase> caseClauses;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;

	public static class CSharp_SwitchCase extends TokenChooser
	{
		public @CHOICE CSharp_Comment XXcomment;
		public @CHOICE CSharp_CaseClauses XXcaseClauses;
		public @CHOICE CSharp_DefaultClause XXdefaultClause;
	}

	public static class CSharp_CaseClause extends TokenSequence
	{
		public @S(10) @NEWLINE CSharp_Keyword CASE = new CSharp_Keyword("case");
		public @S(20) SeparatedList<CSharp_Expression,PunctuationComma> exprs;
		public @S(30) @NOSPACE PunctuationColon colon;
	}

	public static class CSharp_CaseClauses extends TokenSequence
	{
		public @S(10) TokenList<CSharp_CaseClause> cases;
		public @S(20) @PYDENT @OPT TokenList<CSharp_StatementOrComment> statements;
	}

	public static class CSharp_DefaultClause extends TokenSequence
	{
		public @S(10) @NEWLINE CSharp_Keyword DEFAULT = new CSharp_Keyword("default");
		public @S(20) @NOSPACE PunctuationColon colon;
		public @S(30) @PYDENT @OPT TokenList<CSharp_StatementOrComment> statements;
	}

	private @SKIP SwitchMetrics _metrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<CSharp_StatementOrComment> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line numbers etc are all set
			_metrics = new SwitchMetrics(interpreter._metrics, SWITCH);
		}

		CSharp_DefaultClause defaultClause = null;

		int val = interpreter.getIntValue(value);
		for (int i = 0; i < caseClauses.size(); i++)
		{
			AbstractToken which = caseClauses._elements.get(i).getWhich();
			if (which instanceof CSharp_CaseClauses)
			{
				CSharp_CaseClauses cases = (CSharp_CaseClauses) which;
				int numCases = cases.cases.size();
				for (int j = 0; j < numCases; j++)
				{
					int nExprs = cases.cases._elements.get(j).exprs.getPrimaryCount();
					for (int k = 0; k < nExprs; k++)
					{
						CSharp_Expression expr = cases.cases._elements.get(j).exprs.getPrimaryElement(k);
						int whenValue = interpreter.getIntValue(expr);
						if (val == whenValue)
						{
							_metrics.matched(expr, whenValue);
							todo = cases.statements;
							break;
						}
					}
					if (todo != null) break;
				}
			}
			else if (which instanceof CSharp_DefaultClause)
			{
				defaultClause = (CSharp_DefaultClause) which;
			}
			
			if (todo != null)
			{
				break;
			}
		}

		if (todo == null && defaultClause != null && defaultClause.isPresent())
		{
			_metrics.noMatch(defaultClause);
			todo = defaultClause.statements;
		}

		if (todo != null)
		{
			for (CSharp_StatementOrComment stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newValue = transformer.transformExpression(generator, value);

		ArrayList<AbstractStatement> defaultActionList = null;
		
		ArrayList<ArrayList<AbstractExpression>> allCases = new ArrayList<ArrayList<AbstractExpression>>();
		ArrayList<ArrayList<AbstractStatement>> allActions = new ArrayList<ArrayList<AbstractStatement>>();
		for (int i = 0; i < caseClauses.size(); i++)
		{
			AbstractToken which = caseClauses._elements.get(i).getWhich();
			if (which instanceof CSharp_CaseClauses)
			{
				CSharp_CaseClauses when = (CSharp_CaseClauses) which;
				ArrayList<AbstractExpression> caseList = new ArrayList<AbstractExpression>();
				for (CSharp_CaseClause clause : when.cases._elements)
				{
					int nExprs = clause.exprs.getPrimaryCount();
					for (int k = 0; k < nExprs; k++)
					{
						CSharp_Expression expr = clause.exprs.getPrimaryElement(k);
						AbstractExpression newExpr2 = transformer.transformExpression(generator, expr);
						caseList.add(newExpr2);
					}
				}
				allCases.add(caseList);
				
				ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
				for (CSharp_StatementOrComment stmt1 : when.statements._elements)
				{
					ArrayList<AbstractStatement> transStmts = transformer.transformStatement(generator, stmt1);
					for (AbstractStatement stmt2 : transStmts)
					{
						actionList.add(stmt2);
					}
				}
				allActions.add(actionList);
			}
			else if (which instanceof CSharp_DefaultClause)
			{
				CSharp_DefaultClause defaultClause = (CSharp_DefaultClause) which;
				defaultActionList = new ArrayList<AbstractStatement>();
				for (CSharp_StatementOrComment stmt3 : defaultClause.statements._elements)
				{
					for (AbstractStatement stmt4 : transformer.transformStatement(generator, stmt3))
					{
						defaultActionList.add(stmt4);
					}
				}
			}
		}

		AbstractStatement stmt = generator.newSwitchStatement(newValue, allCases, allActions, defaultActionList, this);
		return stmt;
	}

	public static CSharp_Statement generateSwitch(CSharp_Expression expr,
			ArrayList<ArrayList<CSharp_Expression>> values,
			ArrayList<ArrayList<CSharp_Statement>> actions,
			ArrayList<CSharp_Statement> defaultCase, AbstractToken source)
	{
		CSharp_SwitchStatement switchStmt = new CSharp_SwitchStatement();
		switchStmt.leftParen = new PunctuationLeftParen();
		switchStmt.rightParen = new PunctuationRightParen();
		switchStmt.leftBrace = new PunctuationLeftBrace();
		switchStmt.rightBrace = new PunctuationRightBrace();
		switchStmt.value = expr;

		int numCaseBlocks = values.size();
		switchStmt.caseClauses = new TokenList<CSharp_SwitchCase>();
		for (int i = 0; i < numCaseBlocks; i++)
		{
			CSharp_CaseClauses caseClauses = new CSharp_CaseClauses();
			caseClauses.cases = new TokenList<CSharp_CaseClause>();
			for (int j = 0; j < values.get(i).size(); j++)
			{
				CSharp_CaseClause caseClause = new CSharp_CaseClause();
				caseClause.exprs = new SeparatedList<CSharp_Expression,PunctuationComma>();
				caseClause.exprs.addPrimaryElement(values.get(i).get(j));
				caseClause.colon = new PunctuationColon();
				caseClauses.cases.addToken(caseClause);
			}

			caseClauses.statements = new TokenList<CSharp_StatementOrComment>();
			caseClauses.statements.setPresent(true);

			for (CSharp_Statement stmt1 : actions.get(i))
			{
				CSharp_StatementOrComment stmtComm1 = new CSharp_StatementOrComment();
				stmtComm1.setWhich(stmt1);
				caseClauses.statements.addToken(stmtComm1);
			}

			CSharp_SwitchCase switchCase1 = new CSharp_SwitchCase();
			switchCase1.setWhich(caseClauses);
			switchStmt.caseClauses.addToken(switchCase1);
		}

		if (defaultCase != null && defaultCase.size() > 0)
		{
			CSharp_DefaultClause defaultClause = new CSharp_DefaultClause();
			defaultClause.colon = new PunctuationColon();
			defaultClause.statements = new TokenList<CSharp_StatementOrComment>();
			defaultClause.statements.setPresent(true);

			for (CSharp_Statement stmt2 : defaultCase)
			{
				CSharp_StatementOrComment stmtComm2 = new CSharp_StatementOrComment();
				stmtComm2.setWhich(stmt2);
				defaultClause.statements.addToken(stmtComm2);
			}

			CSharp_SwitchCase switchCase2 = new CSharp_SwitchCase();
			switchCase2.setWhich(defaultClause);
			switchStmt.caseClauses.addToken(switchCase2);
		}

		return CSharp_Generator.wrapStatement(switchStmt);
	}
}
