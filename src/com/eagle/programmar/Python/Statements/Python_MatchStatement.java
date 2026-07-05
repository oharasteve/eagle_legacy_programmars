// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2025

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.SubscriptEnum;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_StartOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationVerticalBar;

public class Python_MatchStatement extends TokenSequence
{
	public @S(10) Python_Keyword MATCH = new Python_Keyword("match");
	public @S(20) Python_Expression expression;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) @PYDENT TokenList<Python_MatchCase> matchCases;

	public static class Python_MatchCase extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_StartOfLine soln = new Python_StartOfLine();
		public @S(30) Python_Keyword CASE = new Python_Keyword("case");
		public @S(40) SeparatedList<Python_Expression, PunctuationVerticalBar> values;
		public @S(50) @NOSPACE PunctuationColon colon;
		public @S(60) @PYDENT Python_StatementBlock statements;
	}
	
	public static Python_ComplexStatement generateMatch(Python_Expression expr,
			ArrayList<ArrayList<Python_Expression>> values, ArrayList<ArrayList<Python_ComplexStatement>> cases,
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
			caseClause1.values = new SeparatedList<Python_Expression, PunctuationVerticalBar>();
			caseClause1.colon = new PunctuationColon();
			matchStmt.matchCases.addToken(caseClause1);

			for (int k = 0; k < values.get(i).size(); k++)
			{
				if (k > 0)
				{
					caseClause1.values.addSecondaryElement(new PunctuationVerticalBar());
				}
				caseClause1.values.addPrimaryElement(values.get(i).get(k));
			}
			
			caseClause1.statements = new Python_StatementBlock();
			Python_MultilineStatement multi1 = new Python_MultilineStatement();
			caseClause1.statements.setWhich(multi1);
			multi1.statements = new TokenList<Python_ComplexStatement>();
			for (Python_ComplexStatement stmt1 : cases.get(i))
			{
				AbstractToken which1 = stmt1.statementOrComment.getWhich();
				if (which1 instanceof Python_SameLineStatement)
				{
					Python_SameLineStatement same1 = (Python_SameLineStatement) which1;
					int nPieces = same1.statements.getPrimaryCount();
					for (int j = 0; j < nPieces; j++)
					{
						Python_Statement stmt2 = same1.statements.getPrimaryElement(j);
						if (! (stmt2.getWhich() instanceof Python_BreakStatement))
						{
							multi1.statements.addToken(stmt1);
						}
					}
				}
			}
		}

		if (defaultCase != null && defaultCase.size() > 0)
		{
			Python_MatchCase caseClause2 = new Python_MatchCase();
			caseClause2.values = new SeparatedList<Python_Expression, PunctuationVerticalBar>();
			caseClause2.colon = new PunctuationColon();
			matchStmt.matchCases.addToken(caseClause2);

			caseClause2.values.addPrimaryElement(Python_VariableExpression.generateVariableExpression(
					"_", SubscriptEnum.FIRST_IS_ZERO, null, matchStmt));
			
			caseClause2.statements = new Python_StatementBlock();
			Python_MultilineStatement multi2 = new Python_MultilineStatement();
			caseClause2.statements.setWhich(multi2);
			multi2.statements = new TokenList<Python_ComplexStatement>();
			for (Python_ComplexStatement stmt4 : defaultCase)
			{
				AbstractToken which4 = stmt4.statementOrComment.getWhich();
				if (which4 instanceof Python_SameLineStatement)
				{
					Python_SameLineStatement same2 = (Python_SameLineStatement) which4;
					int nPieces = same2.statements.getPrimaryCount();
					for (int k = 0; k < nPieces; k++)
					{
						Python_Statement stmt5 = same2.statements.getPrimaryElement(k);
						if (! (stmt5.getWhich() instanceof Python_BreakStatement))
						{
							multi2.statements.addToken(stmt4);
						}
					}
				}
			}
		}

		return Python_Generator.wrapStatement(matchStmt);
	}
}
