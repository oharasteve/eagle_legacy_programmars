// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Table;
import com.eagle.programmar.SQL.Statements.SQL_SelectClause;
import com.eagle.programmar.SQL.Statements.SQL_SelectClause.SQL_SelectFrom;
import com.eagle.programmar.SQL.Statements.SQL_SelectClause.SQL_SelectWhere;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_InnerSelect extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SQL_SelectStatement innerSelect;
	public @S(30) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		SQL_Expression what = innerSelect.selectStatement.what.first().expr;
		SQL_SelectFrom from = null;
		SQL_Expression where = null;
		for (SQL_SelectClause clause : innerSelect.selectStatement.clauses._elements)
		{
			if (clause.getWhich() instanceof SQL_SelectFrom)
			{
				if (from != null)
				{
					throw new RuntimeException("Duplicate FROM clause on SELECT");
				}
				from = (SQL_SelectFrom) clause.getWhich();
			}
			else if (clause.getWhich() instanceof SQL_SelectWhere)
			{
				if (where != null)
				{
					throw new RuntimeException("Duplicate WHERE clause on SELECT");
				}
				where = ((SQL_SelectWhere) clause.getWhich()).condition;
			}
			// else ignore it for now
		}
		if (from == null)
		{
			throw new RuntimeException("Missing FROM clause on SELECT");
		}
		if (where == null)
		{
			throw new RuntimeException("Missing WHERE clause on SELECT");
		}
		
		// what, from and where are now all set
		EagleValue val = interpreter.findSymbol(from.table.getValue());
		if (! (val instanceof SQL_Table))
		{
			throw new RuntimeException("Can only select from a Table");
		}
		SQL_Table stable = (SQL_Table) val;
		
		for (int row = 0; row < stable.getNumberRows(); row++)
		{
			ArrayList<EagleValue> values = stable.getRow(row);
			for (int col = 0; col < stable.getNumberColumns(); col++)
			{
				EagleValue value = values.get(col);
				String columnName = stable.getColumnName(col);
				// The -1 means no subscript
				interpreter._symbolTable.setSymbol(this, columnName, -1, value);
			}
			
			// Run the condition for this row. Done if it matches.
			if (interpreter.getBoolValue(where))
			{
				EagleValue result = interpreter.getEagleValue(what);
				interpreter.pushEagleValue(result);
				
				// Remove all the symbols that we added
				for (int col = 0; col < stable.getNumberColumns(); col++)
				{
					String columnName = stable.getColumnName(col);
					interpreter._symbolTable.removeSymbol(columnName);
				}
				return;
			}
		}
		
		throw new RuntimeException("SELECT returned no values");
	}
}
