// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class SQL_SelectStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatementList
{
	public @S(10) SQL_SelectStmt selectStatement;
	public @S(20) @OPT TokenList<SQL_SelectUnion> more;
	public @S(30) @OPT SelectLimit selectLimit;

	public static class SQL_SelectUnion extends TokenSequence
	{
		public @S(10) SQL_Keyword UNION = new SQL_Keyword("UNION");
		public @S(20) @OPT SQL_Keyword ALL = new SQL_Keyword("ALL");
		public @S(30) SQL_SelectStmt selectStatement;
	}

	public static class SelectLimit extends TokenSequence
	{
		public @S(10) SQL_Keyword LIMIT = new SQL_Keyword("LIMIT");
		public @S(20) SQL_Number number;
	}

	public static class SQL_SelectStmt extends TokenSequence
	{
		public @S(10) @DOC("sql_select.asp") SQL_Keyword SELECT = new SQL_Keyword("SELECT");
		public @S(20) SeparatedList<SQL_SelectWhat, PunctuationComma> what;
		public @S(30) @OPT TokenList<SQL_SelectClause> clauses;
		public @S(40) @OPT PunctuationSemicolon semicolon;
	}

	public static class SQL_SelectWhat extends TokenSequence
	{
		public @S(10) SQL_Expression expr;
		public @S(20) @OPT SQL_SelectAs as;

		public static class SQL_SelectAs extends TokenSequence
		{
			public @S(10) @OPT SQL_Keyword AS = new SQL_Keyword("AS");
			public @S(20) SQL_Identifier_Reference name;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// This is essentially a PRINT statement
		if (more != null && more.size() > 0)
		{
			throw new RuntimeException("Cannot handle SELECT / UNION yet");
		}

		if (selectStatement.clauses != null && selectStatement.clauses.size() > 0)
		{
			throw new RuntimeException("Cannot handle SELECT clauses yet");
		}

		for (int i = 0; i < selectStatement.what.getPrimaryCount(); i++)
		{
			SQL_SelectWhat what = selectStatement.what.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(what.expr);
			System.out.print(val); // It should have its own newline '\n'
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		// Just treat it like a PRINT statement for now
		if (more != null && more.size() > 0)
		{
			throw new RuntimeException("Cannot handle SELECT / UNION yet");
		}

		if (selectStatement.clauses != null && selectStatement.clauses.size() > 0)
		{
			throw new RuntimeException("Cannot handle SELECT clauses yet");
		}

		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (int i = 0; i < selectStatement.what.getPrimaryCount(); i++)
		{
			SQL_SelectWhat what = selectStatement.what.getPrimaryElement(i);
			AbstractExpression line = transformer.transformExpression(generator, what.expr);
			result.add(generator.newPrintStatement(line, false, false, this));
		}
		return result;
	}
}
