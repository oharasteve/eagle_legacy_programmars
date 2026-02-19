// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

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
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SwitchStatement extends TokenSequence
		implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @NEWLINE @DOC("statements/selection-statements") CSharp_Keyword SWITCH = new CSharp_Keyword("switch");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression val;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<CSharp_SwitchCase> caseClauses;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;

	public static class CSharp_SwitchCase extends TokenChooser
	{
		public @CHOICE CSharp_Comment XXcomment;
		public @CHOICE CSharp_CaseClause XXcaseClause;
		public @CHOICE CSharp_DefaultClause XXdefaultClause;
	}

	public static class CSharp_CaseClause extends TokenSequence
	{
		public @S(10) @NEWLINE CSharp_Keyword CASE = new CSharp_Keyword("case");
		public @S(20) SeparatedList<CSharp_Expression, PunctuationComma> exprList;
		public @S(30) @NOSPACE PunctuationColon colon;
		public @S(40) @OPT @PYDENT TokenList<CSharp_StatementOrComment> statements;
	}

	public static class CSharp_DefaultClause extends TokenSequence
	{
		public @S(10) @NEWLINE CSharp_Keyword DEFAULT = new CSharp_Keyword("default");
		public @S(20) @NOSPACE PunctuationColon colon;
		public @S(30) @OPT @PYDENT TokenList<CSharp_StatementOrComment> statements;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public static CSharp_Statement generateSwitch(CSharp_Expression expr,
			ArrayList<CSharp_Expression> values, ArrayList<ArrayList<CSharp_Statement>> cases,
			ArrayList<CSharp_Statement> defaultCase, AbstractToken source)
	{
		CSharp_SwitchStatement switchStmt = new CSharp_SwitchStatement();
		switchStmt.leftParen = new PunctuationLeftParen();
		switchStmt.rightParen = new PunctuationRightParen();
		switchStmt.leftBrace = new PunctuationLeftBrace();
		switchStmt.rightBrace = new PunctuationRightBrace();
		switchStmt.val = expr;

		int numCases = values.size();
		switchStmt.caseClauses = new TokenList<CSharp_SwitchCase>();
		for (int i = 0; i < numCases; i++)
		{
			CSharp_CaseClause caseClause = new CSharp_CaseClause();
			caseClause.exprList = new SeparatedList<CSharp_Expression, PunctuationComma>();
			caseClause.exprList.addPrimaryElement(values.get(i));

			caseClause.colon = new PunctuationColon();
			caseClause.statements = new TokenList<CSharp_StatementOrComment>();
			caseClause.statements.setPresent(true);

			for (CSharp_Statement stmt1 : cases.get(i))
			{
				CSharp_StatementOrComment stmtComm1 = new CSharp_StatementOrComment();
				stmtComm1.setWhich(stmt1);
				caseClause.statements.addToken(stmtComm1);
			}
			CSharp_StatementOrComment stmtComm1 = new CSharp_StatementOrComment();
			stmtComm1.setWhich(CSharp_BreakStatement.generateBreak(switchStmt));
			caseClause.statements.addToken(stmtComm1);

			CSharp_SwitchCase switchCase1 = new CSharp_SwitchCase();
			switchCase1.setWhich(caseClause);
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
			CSharp_StatementOrComment stmtComm2 = new CSharp_StatementOrComment();
			stmtComm2.setWhich(CSharp_BreakStatement.generateBreak(switchStmt));
			defaultClause.statements.addToken(stmtComm2);

			CSharp_SwitchCase switchCase2 = new CSharp_SwitchCase();
			switchCase2.setWhich(defaultClause);
			switchStmt.caseClauses.addToken(switchCase2);
		}

		return CSharp_Generator.wrapStatement(switchStmt);
	}
}
