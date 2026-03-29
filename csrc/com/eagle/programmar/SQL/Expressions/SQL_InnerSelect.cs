// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.SQL.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Table = com.eagle.programmar.SQL.SQL_Table;
	using SQL_SelectClause = com.eagle.programmar.SQL.Statements.SQL_SelectClause;
	using SQL_SelectFrom = com.eagle.programmar.SQL.Statements.SQL_SelectClause.SQL_SelectFrom;
	using SQL_SelectWhere = com.eagle.programmar.SQL.Statements.SQL_SelectClause.SQL_SelectWhere;
	using SQL_SelectStatement = com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class SQL_InnerSelect : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Statements.SQL_SelectStatement innerSelect;
		public SQL_SelectStatement innerSelect;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			SQL_Expression what = innerSelect.selectStatement.what.first().expr;
			SQL_SelectClause.SQL_SelectFrom from = null;
			SQL_Expression where = null;
			foreach (SQL_SelectClause clause in innerSelect.selectStatement.clauses._elements)
			{
				if (clause.getWhich() is SQL_SelectClause.SQL_SelectFrom)
				{
					if (from != null)
					{
						throw new Exception("Duplicate FROM clause on SELECT");
					}
					from = (SQL_SelectClause.SQL_SelectFrom) clause.getWhich();
				}
				else if (clause.getWhich() is SQL_SelectClause.SQL_SelectWhere)
				{
					if (where != null)
					{
						throw new Exception("Duplicate WHERE clause on SELECT");
					}
					where = ((SQL_SelectClause.SQL_SelectWhere) clause.getWhich()).condition;
				}
				// else ignore it for now
			}
			if (from == null)
			{
				throw new Exception("Missing FROM clause on SELECT");
			}
			if (where == null)
			{
				throw new Exception("Missing WHERE clause on SELECT");
			}

			// what, from and where are now all set
			EagleValue val = interpreter.findSymbol(from.table.getValue());
			if (!(val is SQL_Table))
			{
				throw new Exception("Can only select from a Table");
			}
			SQL_Table stable = (SQL_Table) val;

			for (int row = 0; row < stable.NumberRows; row++)
			{
				List<EagleValue> values = stable.getRow(row);
				for (int col = 0; col < stable.NumberColumns; col++)
				{
					EagleValue value = values[col];
					string columnName = stable.getColumnName(col);
					// The -1 means no subscript
					interpreter._symbolTable.setSymbol(this, columnName, -1, value);
				}

				// Run the condition for this row. Done if it matches.
				if (interpreter.getBoolValue(where))
				{
					EagleValue result = interpreter.getEagleValue(what);
					interpreter.pushEagleValue(result);

					// Remove all the symbols that we added
					for (int col = 0; col < stable.NumberColumns; col++)
					{
						string columnName = stable.getColumnName(col);
						interpreter._symbolTable.removeSymbol(columnName);
					}
					return;
				}
			}

			throw new Exception("SELECT returned no values");
		}
	}

}
