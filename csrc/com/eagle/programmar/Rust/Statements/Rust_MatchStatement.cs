// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rust.Statements
{

	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Statement = com.eagle.programmar.Rust.Rust_Statement;
	using Rust_Syntax = com.eagle.programmar.Rust.Rust_Syntax;
	using Rust_Comment = com.eagle.programmar.Rust.Terminals.Rust_Comment;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationVerticalBar = com.eagle.tokens.punctuation.PunctuationVerticalBar;

	public class Rust_MatchStatement : TokenSequence, AbstractStatement, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("expressions/match-expr.html") com.eagle.programmar.Rust.Terminals.Rust_Keyword MATCH = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("match");
		public @DOC("expressions/match-expr.html") Rust_Keyword MATCH = new Rust_Keyword("match");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Expression val;
		public Rust_Expression val;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Rust_MatchClause> clauses;
		public TokenList<Rust_MatchClause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public static class Rust_MatchClause extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_Comment XXcomment;
			public Rust_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_CaseClause XXcaseClause;
			public Rust_CaseClause XXcaseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rust_DefaultClause XXdefaultClause;
			public Rust_DefaultClause XXdefaultClause;
		}

		public static class Rust_CaseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Rust.Rust_Expression,com.eagle.tokens.punctuation.PunctuationVerticalBar> exprList;
			public SeparatedList<Rust_Expression, PunctuationVerticalBar> exprList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Punctuation arrow = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("=>");
			public Rust_Punctuation arrow = new Rust_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Rust.Rust_Statement> statements;
			public @OPT TokenList<Rust_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
		}

		public static class Rust_DefaultClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation underscore = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation('_');
			public Rust_Punctuation underscore = new Rust_Punctuation('_');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Punctuation arrow = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("=>");
			public Rust_Punctuation arrow = new Rust_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Rust.Rust_Statement> statements;
			public @OPT TokenList<Rust_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Rust.Rust_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public static Rust_Statement generateMatch(Rust_Expression expr, List<Rust_Expression> values, List<List<Rust_Statement>> stmtLists, List<Rust_Statement> defaultStmts, AbstractToken source)
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
				caseClause.exprList = new SeparatedList<Rust_Expression, PunctuationVerticalBar>();
				caseClause.exprList.addPrimaryElement(values.get(i)); // Just one value right now

				caseClause.arrow = new Rust_Punctuation("=>");
				caseClause.statements = new TokenList<Rust_Statement>();
				caseClause.statements.setPresent(true);

				foreach (Rust_Statement stmt1 in stmtLists.get(i))
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

				foreach (Rust_Statement stmt2 in defaultStmts)
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

}
