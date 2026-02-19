// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2025

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.transform.EagleGenerator.SubscriptEnum;

public class Python_MatchStatement extends TokenSequence
{
	public @S(10) Python_Keyword MATCH = new Python_Keyword("match");
	public @S(20) Python_Expression expression;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) @PYDENT TokenList<Python_MatchCase> matchCases;

	public static class Python_MatchCase extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) Python_Keyword CASE = new Python_Keyword("case");
		public @S(40) Python_Expression value;
		public @S(50) @NOSPACE PunctuationColon colon;
		public @S(60) @PYDENT Python_StatementBlock statements;
	}

	public static Python_ComplexStatement generateMatch(Python_Expression expr,
			ArrayList<Python_Expression> values, ArrayList<ArrayList<Python_ComplexStatement>> cases,
			ArrayList<Python_ComplexStatement> defaultCase, AbstractToken source)
	{
		Python_MatchStatement matchStmt = new Python_MatchStatement();
		matchStmt.colon = new PunctuationColon();
		matchStmt.expression = expr;

		int numCases = values.size();
		matchStmt.matchCases = new TokenList<Python_MatchCase>();
		for (int i = 0; i < numCases; i++)
		{
			Python_MatchCase caseClause1 = new Python_MatchCase();
			caseClause1.value = values.get(i);
			caseClause1.colon = new PunctuationColon();
			caseClause1.statements = new Python_StatementBlock();

			Python_MultilineStatement multi1 = new Python_MultilineStatement();
			caseClause1.statements.setWhich(multi1);
			multi1.statements = new TokenList<Python_ComplexStatement>();
			for (Python_ComplexStatement stmt1 : cases.get(i))
			{
				multi1.statements.addToken(stmt1);
			}

			matchStmt.matchCases.addToken(caseClause1);
		}

		if (defaultCase != null && defaultCase.size() > 0)
		{
			Python_MatchCase caseClause2 = new Python_MatchCase();
			caseClause2.value = Python_VariableExpression.generateVariableExpression(
					"_", SubscriptEnum.FIRST_IS_ZERO, null, matchStmt);
			caseClause2.colon = new PunctuationColon();
			caseClause2.statements = new Python_StatementBlock();

			Python_MultilineStatement multi2 = new Python_MultilineStatement();
			caseClause2.statements.setWhich(multi2);
			multi2.statements = new TokenList<Python_ComplexStatement>();
			for (Python_ComplexStatement stmt2 : defaultCase)
			{
				multi2.statements.addToken(stmt2);
			}

			matchStmt.matchCases.addToken(caseClause2);
		}

		return Python_Generator.wrapStatement(matchStmt);
	}
}
