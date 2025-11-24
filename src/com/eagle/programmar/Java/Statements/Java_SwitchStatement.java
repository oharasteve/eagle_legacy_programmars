// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

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
import com.eagle.tokens.TokenChooser.CHOICE;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_SwitchStatement extends TokenSequence
		implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @NEWLINE @DOC("statements.html#14.11") Java_Keyword SWITCH = new Java_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression val;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<Java_SwitchCase> caseClauses;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;

	public static class Java_SwitchCase extends TokenChooser
	{
		public @CHOICE Java_Comment XXcomment;
		public @CHOICE Java_CaseClause XXcaseClause;
		public @CHOICE Java_DefaultClause XXdefaultClause;
	}

	public @CHOICE static class Java_CaseClause extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword CASE = new Java_Keyword("case");
		public @S(20) SeparatedList<Java_Expression, PunctuationComma> exprList;
		public @S(30) @NOSPACE PunctuationColon colon;
		public @S(40) @OPT @PYDENT TokenList<Java_StatementOrComment> statements;
	}

	public @CHOICE static class Java_DefaultClause extends TokenSequence
	{
		public @S(10) @NEWLINE Java_Keyword DEFAULT = new Java_Keyword("default");
		public @S(20) @NOSPACE PunctuationColon colon;
		public @S(30) @OPT @PYDENT TokenList<Java_StatementOrComment> statements;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public Java_Statement generateSwitch(Java_Expression expr,
			ArrayList<Java_Expression> values, ArrayList<ArrayList<Java_Statement>> cases,
			ArrayList<Java_Statement> defaultCase, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.val = expr;

		int numCases = values.size();
		caseClauses = new TokenList<Java_SwitchCase>();
		for (int i = 0; i < numCases; i++)
		{
			Java_CaseClause caseClause = new Java_CaseClause();
			caseClause.exprList = new SeparatedList<Java_Expression, PunctuationComma>();
			caseClause.exprList.addPrimaryElement(values.get(i));

			caseClause.colon = new PunctuationColon();
			caseClause.statements = new TokenList<Java_StatementOrComment>();
			caseClause.statements.setPresent(true);

			for (Java_Statement stmt1 : cases.get(i))
			{
				Java_StatementOrComment stmtComm1 = new Java_StatementOrComment();
				stmtComm1.setWhich(stmt1);
				caseClause.statements.addToken(stmtComm1);
			}
			Java_BreakStatement brk1 = new Java_BreakStatement();
			Java_StatementOrComment stmtComm1 = new Java_StatementOrComment();
			stmtComm1.setWhich(brk1.generateBreak(this));
			caseClause.statements.addToken(stmtComm1);

			Java_SwitchCase switchCase1 = new Java_SwitchCase();
			switchCase1.setWhich(caseClause);
			caseClauses.addToken(switchCase1);
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
			Java_BreakStatement brk2 = new Java_BreakStatement();
			Java_StatementOrComment stmtComm2 = new Java_StatementOrComment();
			stmtComm2.setWhich(brk2.generateBreak(this));
			defaultClause.statements.addToken(stmtComm2);

			Java_SwitchCase switchCase2 = new Java_SwitchCase();
			switchCase2.setWhich(defaultClause);
			caseClauses.addToken(switchCase2);
		}

		return Java_Generator.wrapStatement(this);
	}
}
