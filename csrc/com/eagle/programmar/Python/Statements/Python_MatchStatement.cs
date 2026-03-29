// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2025

namespace com.eagle.programmar.Python.Statements
{

	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_ElseStartOfLine = com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;

	public class Python_MatchStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword MATCH = new com.eagle.programmar.Python.Terminals.Python_Keyword("match");
		public Python_Keyword MATCH = new Python_Keyword("match");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression expression;
		public Python_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationColon colon;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @PYDENT TokenList<Python_MatchCase> matchCases;
		public  PYDENT;

		public class Python_MatchCase : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NEWLINE Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword CASE = new com.eagle.programmar.Python.Terminals.Python_Keyword("case");
			public Python_Keyword CASE = new Python_Keyword("case");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Python_Expression value;
			public Python_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationColon colon;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @PYDENT Python_StatementBlock statements;
			public  PYDENT;
		}

		public static Python_ComplexStatement generateMatch(Python_Expression expr, List<Python_Expression> values, List<List<Python_ComplexStatement>> cases, List<Python_ComplexStatement> defaultCase, AbstractToken source)
		{
			Python_MatchStatement matchStmt = new Python_MatchStatement();
			matchStmt.colon = new PunctuationColon();
			matchStmt.expression = expr;

			int numCases = values.Count;
			matchStmt.matchCases = new TokenList<Python_MatchCase>();
			for (int i = 0; i < numCases; i++)
			{
				Python_MatchCase caseClause1 = new Python_MatchCase();
				caseClause1.value = values[i];
				caseClause1.colon = new PunctuationColon();
				caseClause1.statements = new Python_StatementBlock();

				Python_MultilineStatement multi1 = new Python_MultilineStatement();
				caseClause1.statements.setWhich(multi1);
				multi1.statements = new TokenList<Python_ComplexStatement>();
				foreach (Python_ComplexStatement stmt1 in cases[i])
				{
					multi1.statements.addToken(stmt1);
				}

				matchStmt.matchCases.addToken(caseClause1);
			}

			if (defaultCase != null && defaultCase.Count > 0)
			{
				Python_MatchCase caseClause2 = new Python_MatchCase();
				caseClause2.value = Python_VariableExpression.generateVariableExpression("_", SubscriptEnum.FIRST_IS_ZERO, null, matchStmt);
				caseClause2.colon = new PunctuationColon();
				caseClause2.statements = new Python_StatementBlock();

				Python_MultilineStatement multi2 = new Python_MultilineStatement();
				caseClause2.statements.setWhich(multi2);
				multi2.statements = new TokenList<Python_ComplexStatement>();
				foreach (Python_ComplexStatement stmt2 in defaultCase)
				{
					multi2.statements.addToken(stmt2);
				}

				matchStmt.matchCases.addToken(caseClause2);
			}

			return Python_Generator.wrapStatement(matchStmt);
		}
	}

}
