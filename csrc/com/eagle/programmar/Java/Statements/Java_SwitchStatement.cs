// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

namespace com.eagle.programmar.Java.Statements
{

	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_StatementOrComment = com.eagle.programmar.Java.Java_StatementOrComment;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
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

	public class Java_SwitchStatement : TokenSequence, AbstractStatement, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements.html#14.11") com.eagle.programmar.Java.Terminals.Java_Keyword SWITCH = new com.eagle.programmar.Java.Terminals.Java_Keyword("switch");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Expression val;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<Java_SwitchCase> caseClauses;
		public TokenList<Java_SwitchCase> caseClauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;

		public class Java_SwitchCase : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Comment XXcomment;
			public Java_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_CaseClause XXcaseClause;
			public Java_CaseClause XXcaseClause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_DefaultClause XXdefaultClause;
			public Java_DefaultClause XXdefaultClause;
		}

		public class Java_CaseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE Java_Keyword CASE = new com.eagle.programmar.Java.Terminals.Java_Keyword("case");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Java_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprList;
			public SeparatedList<Java_Expression, PunctuationComma> exprList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @PYDENT TokenList<com.eagle.programmar.Java.Java_StatementOrComment> statements;
			public  OPT;
		}

		public class Java_DefaultClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE Java_Keyword DEFAULT = new com.eagle.programmar.Java.Terminals.Java_Keyword("default");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationColon colon;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @PYDENT TokenList<com.eagle.programmar.Java.Java_StatementOrComment> statements;
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Java.Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public static Java_Statement generateSwitch(Java_Expression expr, List<Java_Expression> values, List<List<Java_Statement>> cases, List<Java_Statement> defaultCase, AbstractToken source)
		{
			Java_SwitchStatement switchStmt = new Java_SwitchStatement();
			switchStmt.leftParen = new PunctuationLeftParen();
			switchStmt.rightParen = new PunctuationRightParen();
			switchStmt.leftBrace = new PunctuationLeftBrace();
			switchStmt.rightBrace = new PunctuationRightBrace();
			switchStmt.val = expr;

			int numCases = values.Count;
			switchStmt.caseClauses = new TokenList<Java_SwitchCase>();
			for (int i = 0; i < numCases; i++)
			{
				Java_CaseClause caseClause = new Java_CaseClause();
				caseClause.exprList = new SeparatedList<Java_Expression, PunctuationComma>();
				caseClause.exprList.addPrimaryElement(values[i]);

				caseClause.colon = new PunctuationColon();
				caseClause.statements = new TokenList<Java_StatementOrComment>();
				caseClause.statements.setPresent(true);

				foreach (Java_Statement stmt1 in cases[i])
				{
					Java_StatementOrComment stmtComm1 = new Java_StatementOrComment();
					stmtComm1.setWhich(stmt1);
					caseClause.statements.addToken(stmtComm1);
				}
				Java_StatementOrComment stmtComm1 = new Java_StatementOrComment();
				stmtComm1.setWhich(Java_BreakStatement.generateBreak(switchStmt));
				caseClause.statements.addToken(stmtComm1);

				Java_SwitchCase switchCase1 = new Java_SwitchCase();
				switchCase1.setWhich(caseClause);
				switchStmt.caseClauses.addToken(switchCase1);
			}

			if (defaultCase != null && defaultCase.Count > 0)
			{
				Java_DefaultClause defaultClause = new Java_DefaultClause();
				defaultClause.colon = new PunctuationColon();
				defaultClause.statements = new TokenList<Java_StatementOrComment>();
				defaultClause.statements.setPresent(true);

				foreach (Java_Statement stmt2 in defaultCase)
				{
					Java_StatementOrComment stmtComm2 = new Java_StatementOrComment();
					stmtComm2.setWhich(stmt2);
					defaultClause.statements.addToken(stmtComm2);
				}
				Java_StatementOrComment stmtComm2 = new Java_StatementOrComment();
				stmtComm2.setWhich(Java_BreakStatement.generateBreak(switchStmt));
				defaultClause.statements.addToken(stmtComm2);

				Java_SwitchCase switchCase2 = new Java_SwitchCase();
				switchCase2.setWhich(defaultClause);
				switchStmt.caseClauses.addToken(switchCase2);
			}

			return Java_Generator.wrapStatement(switchStmt);
		}
	}

}
