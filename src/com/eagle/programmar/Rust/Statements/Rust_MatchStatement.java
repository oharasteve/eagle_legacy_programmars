// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Syntax;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationVerticalBar;

public class Rust_MatchStatement extends TokenSequence implements AbstractStatement, EagleScopeInterface
{
	public @S(10) @DOC("expressions/match-expr.html") Rust_Keyword MATCH = new Rust_Keyword("match");
	public @S(20) Rust_Expression val;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) TokenList<Rust_MatchClause> clauses;
	public @S(50) PunctuationRightBrace rightBrace;

	public static class Rust_MatchClause extends TokenChooser
	{
		public @CHOICE Rust_Comment XXcomment;
		public @CHOICE Rust_CaseClause XXcaseClause;
		public @CHOICE Rust_DefaultClause XXdefaultClause;
	}

	public static class Rust_CaseClause extends TokenSequence
	{
		public @S(10) SeparatedList<Rust_Expression,PunctuationVerticalBar> exprList;
		public @S(20) Rust_Punctuation arrow = new Rust_Punctuation("=>");
		public @S(30) @OPT TokenList<Rust_Statement> statements;
		public @S(40) @OPT PunctuationComma comma;
	}

	public static class Rust_DefaultClause extends TokenSequence
	{
		public @S(10) Rust_Punctuation underscore = new Rust_Punctuation('_');
		public @S(20) Rust_Punctuation arrow = new Rust_Punctuation("=>");
		public @S(30) @OPT TokenList<Rust_Statement> statements;
		public @S(40) @OPT PunctuationComma comma;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	public static Rust_Statement generateMatch(Rust_Expression expr,
			ArrayList<Rust_Expression> values, ArrayList<ArrayList<Rust_Statement>> stmtLists,
			ArrayList<Rust_Statement> defaultStmts, AbstractToken source)
	{
		Rust_MatchStatement matchStmt = new Rust_MatchStatement();
		matchStmt.leftBrace = new PunctuationLeftBrace();
		matchStmt.rightBrace = new PunctuationRightBrace();
		matchStmt.val = expr;

		int numCases = values.size();
		matchStmt.clauses = new TokenList<Rust_MatchClause>();
		for (int i = 0; i < numCases; i++)
		{
			Rust_CaseClause caseClause = new Rust_CaseClause();
			caseClause.exprList = new SeparatedList<Rust_Expression,PunctuationVerticalBar>();
			caseClause.exprList.addPrimaryElement(values.get(i));	// Just one value right now

			caseClause.arrow = new Rust_Punctuation("=>");
			caseClause.statements = new TokenList<Rust_Statement>();
			caseClause.statements.setPresent(true);

			for (Rust_Statement stmt1 : stmtLists.get(i))
			{
				caseClause.statements.addToken(stmt1);
			}
			Rust_Statement breakStmt = Rust_BreakStatement.generateBreak(matchStmt);
			caseClause.statements.addToken(breakStmt);

			Rust_MatchClause matchClause = new Rust_MatchClause();
			matchClause.setWhich(caseClause);
			matchStmt.clauses.addToken(matchClause);
		}

		if (defaultStmts != null && defaultStmts.size() > 0)
		{
			Rust_DefaultClause defaultClause = new Rust_DefaultClause();
			defaultClause.arrow = new Rust_Punctuation("=>");
			defaultClause.statements = new TokenList<Rust_Statement>();
			defaultClause.statements.setPresent(true);

			for (Rust_Statement stmt2 : defaultStmts)
			{
				defaultClause.statements.addToken(stmt2);
			}
			Rust_Statement breakStmt = Rust_BreakStatement.generateBreak(matchStmt);
			defaultClause.statements.addToken(breakStmt);

			Rust_MatchClause matchClause = new Rust_MatchClause();
			matchClause.setWhich(defaultClause);
			matchStmt.clauses.addToken(matchClause);
		}

		return Rust_Generator.wrapStatement(matchStmt);
	}
}
