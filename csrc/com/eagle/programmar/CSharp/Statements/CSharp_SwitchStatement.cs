// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

namespace com.eagle.programmar.CSharp.Statements
{

	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_StatementOrComment = com.eagle.programmar.CSharp.CSharp_StatementOrComment;
	using CSharp_Syntax = com.eagle.programmar.CSharp.CSharp_Syntax;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSharp_SwitchStatement : TokenSequence, AbstractStatement, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements/selection-statements") com.eagle.programmar.CSharp.Terminals.CSharp_Keyword SWITCH = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("switch");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Expression val;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<CSharp_SwitchCase> caseClauses;
		public TokenList<CSharp_SwitchCase> caseClauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public class CSharp_SwitchCase : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Comment XXcomment;
			public CSharp_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_CaseClause XXcaseClause;
			public CSharp_CaseClause XXcaseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_DefaultClause XXdefaultClause;
			public CSharp_DefaultClause XXdefaultClause;
		}

		public class CSharp_CaseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE CSharp_Keyword CASE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("case");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.CSharp.CSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprList;
			public SeparatedList<CSharp_Expression, PunctuationComma> exprList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @PYDENT TokenList<com.eagle.programmar.CSharp.CSharp_StatementOrComment> statements;
			public  OPT;
		}

		public class CSharp_DefaultClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE CSharp_Keyword DEFAULT = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("default");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationColon colon;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @PYDENT TokenList<com.eagle.programmar.CSharp.CSharp_StatementOrComment> statements;
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.CSharp.CSharp_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public static CSharp_Statement generateSwitch(CSharp_Expression expr, List<CSharp_Expression> values, List<List<CSharp_Statement>> cases, List<CSharp_Statement> defaultCase, AbstractToken source)
		{
			CSharp_SwitchStatement switchStmt = new CSharp_SwitchStatement();
			switchStmt.leftParen = new PunctuationLeftParen();
			switchStmt.rightParen = new PunctuationRightParen();
			switchStmt.leftBrace = new PunctuationLeftBrace();
			switchStmt.rightBrace = new PunctuationRightBrace();
			switchStmt.val = expr;

			int numCases = values.Count;
			switchStmt.caseClauses = new TokenList<CSharp_SwitchCase>();
			for (int i = 0; i < numCases; i++)
			{
				CSharp_CaseClause caseClause = new CSharp_CaseClause();
				caseClause.exprList = new SeparatedList<CSharp_Expression, PunctuationComma>();
				caseClause.exprList.addPrimaryElement(values[i]);

				caseClause.colon = new PunctuationColon();
				caseClause.statements = new TokenList<CSharp_StatementOrComment>();
				caseClause.statements.setPresent(true);

				foreach (CSharp_Statement stmt1 in cases[i])
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

			if (defaultCase != null && defaultCase.Count > 0)
			{
				CSharp_DefaultClause defaultClause = new CSharp_DefaultClause();
				defaultClause.colon = new PunctuationColon();
				defaultClause.statements = new TokenList<CSharp_StatementOrComment>();
				defaultClause.statements.setPresent(true);

				foreach (CSharp_Statement stmt2 in defaultCase)
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

}
