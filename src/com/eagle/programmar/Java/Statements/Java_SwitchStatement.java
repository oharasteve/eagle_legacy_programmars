// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.SwitchMetrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_SwitchStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleScopeInterface,
				EagleTransformableStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.11") Java_Keyword SWITCH = new Java_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression value;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<Java_SwitchCase> caseClauses;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;

	public static class Java_SwitchCase extends TokenChooser
	{
		public @CHOICE Java_Comment XXcomment;
		public @CHOICE Java_CaseClauses XXcaseClauses;
		public @CHOICE Java_DefaultClause XXdefaultClause;
	}

	public static class Java_CaseClause extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword CASE = new Java_Keyword("case");
		public @S(20) SeparatedList<Java_Expression,PunctuationComma> exprs;
		public @S(30) @NOSPACE PunctuationColon colon;
	}

	public static class Java_CaseClauses extends TokenSequence
	{
		public @S(10) TokenList<Java_CaseClause> cases;
		public @S(20) @PYDENT @OPT TokenList<Java_StatementOrComment> statements;
	}

	public static class Java_DefaultClause extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword DEFAULT = new Java_Keyword("default");
		public @S(20) @NOSPACE PunctuationColon colon;
		public @S(30) @PYDENT @OPT TokenList<Java_StatementOrComment> statements;
	}

	private @SKIP SwitchMetrics _metrics = null;
	
	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Java_StatementOrComment> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line numbers etc are all set
			_metrics = new SwitchMetrics(interpreter._metrics, SWITCH);
		}

		Java_DefaultClause defaultClause = null;

		int val = interpreter.getIntValue(value);
		for (int i = 0; i < caseClauses.size(); i++)
		{
			AbstractToken which = caseClauses._elements.get(i).getWhich();
			if (which instanceof Java_CaseClauses)
			{
				Java_CaseClauses cases = (Java_CaseClauses) which;
				int numCases = cases.cases.size();
				for (int j = 0; j < numCases; j++)
				{
					int nExprs = cases.cases._elements.get(j).exprs.getPrimaryCount();
					for (int k = 0; k < nExprs; k++)
					{
						Java_Expression expr = cases.cases._elements.get(j).exprs.getPrimaryElement(k);
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
			else if (which instanceof Java_DefaultClause)
			{
				defaultClause = (Java_DefaultClause) which;
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
			for (Java_StatementOrComment stmt : todo._elements)
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
			if (which instanceof Java_CaseClauses)
			{
				Java_CaseClauses when = (Java_CaseClauses) which;
				ArrayList<AbstractExpression> caseList = new ArrayList<AbstractExpression>();
				for (Java_CaseClause clause : when.cases._elements)
				{
					int nExprs = clause.exprs.getPrimaryCount();
					for (int k = 0; i < nExprs; k++)
					{
						Java_Expression expr = clause.exprs.getPrimaryElement(k);
						AbstractExpression newExpr2 = transformer.transformExpression(generator, expr);
						caseList.add(newExpr2);
					}
				}
				allCases.add(caseList);
				
				ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
				for (Java_StatementOrComment stmt1 : when.statements._elements)
				{
					ArrayList<AbstractStatement> transStmts = transformer.transformStatement(generator, stmt1);
					for (AbstractStatement stmt2 : transStmts)
					{
						actionList.add(stmt2);
					}
				}
				allActions.add(actionList);
			}
			else if (which instanceof Java_DefaultClause)
			{
				Java_DefaultClause defaultClause = (Java_DefaultClause) which;
				defaultActionList = new ArrayList<AbstractStatement>();
				for (Java_StatementOrComment stmt3 : defaultClause.statements._elements)
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
	
	public static Java_Statement generateSwitch(Java_Expression expr,
			ArrayList<ArrayList<Java_Expression>> values,
			ArrayList<ArrayList<Java_Statement>> actions,
			ArrayList<Java_Statement> defaultCase, AbstractToken source)
	{
		Java_SwitchStatement switchStmt = new Java_SwitchStatement();
		switchStmt.leftParen = new PunctuationLeftParen();
		switchStmt.rightParen = new PunctuationRightParen();
		switchStmt.leftBrace = new PunctuationLeftBrace();
		switchStmt.rightBrace = new PunctuationRightBrace();
		switchStmt.value = expr;

		int numCaseBlocks = values.size();
		switchStmt.caseClauses = new TokenList<Java_SwitchCase>();
		for (int i = 0; i < numCaseBlocks; i++)
		{
			Java_CaseClauses caseClauses = new Java_CaseClauses();
			caseClauses.cases = new TokenList<Java_CaseClause>();
			for (int j = 0; j < values.get(i).size(); j++)
			{
				Java_CaseClause caseClause = new Java_CaseClause();
				caseClause.exprs = new SeparatedList<Java_Expression,PunctuationComma>();
				caseClause.exprs.addPrimaryElement(values.get(i).get(j));
				caseClause.colon = new PunctuationColon();
				caseClauses.cases.addToken(caseClause);
			}

			caseClauses.statements = new TokenList<Java_StatementOrComment>();
			caseClauses.statements.setPresent(true);

			for (Java_Statement stmt1 : actions.get(i))
			{
				Java_StatementOrComment stmtComm1 = new Java_StatementOrComment();
				stmtComm1.setWhich(stmt1);
				caseClauses.statements.addToken(stmtComm1);
			}

			Java_SwitchCase switchCase1 = new Java_SwitchCase();
			switchCase1.setWhich(caseClauses);
			switchStmt.caseClauses.addToken(switchCase1);
		}

		if (defaultCase != null && defaultCase.size() > 0)
		{
			Java_DefaultClause defaultClause = new Java_DefaultClause();
			defaultClause.colon = new PunctuationColon();
			defaultClause.statements = new TokenList<Java_StatementOrComment>();
			defaultClause.statements.setPresent(true);

			for (Java_Statement stmt2 : defaultCase)
			{
				Java_StatementOrComment stmtComm2 = new Java_StatementOrComment();
				stmtComm2.setWhich(stmt2);
				defaultClause.statements.addToken(stmtComm2);
			}

			Java_SwitchCase switchCase2 = new Java_SwitchCase();
			switchCase2.setWhich(defaultClause);
			switchStmt.caseClauses.addToken(switchCase2);
		}

		return Java_Generator.wrapStatement(switchStmt);
	}
}
