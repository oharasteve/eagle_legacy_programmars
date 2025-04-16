// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_StateMachine;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_Field;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_Row;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_Table;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement.SQL_SelectStmt.SQL_SelectClause;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement.SQL_SelectStmt.SQL_SelectClause.SQL_SelectFrom;
import com.eagle.programmar.SQL.Statements.SQL_SelectStatement.SQL_SelectStmt.SQL_SelectClause.SQL_SelectWhere;
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
		SQL_StateMachine state = (SQL_StateMachine) interpreter._state;
		
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
		String tableName = from.table.getValue().toUpperCase();
		if (! (state._tables.containsKey(tableName)))
		{
			throw new RuntimeException("Unable to find a table named " + tableName);
		}
		SQL_Table table = state._tables.get(tableName);
		
		for (SQL_Row row : table._rows)
		{
			int numFields = table._fields.size();
			for (int fld = 0; fld < numFields; fld++)
			{
				EagleValue value = row._values.get(fld);
				SQL_Field field = table._fields.get(fld);
				interpreter._symbolTable.setSymbol(this, field._name, value);
			}
			
			// Run the condition for this row. Done if it matches.
			if (interpreter.getBoolValue(where))
			{
				EagleValue result = interpreter.getEagleValue(what);
				interpreter.pushEagleValue(result);
				
				// Remove all the fields that we added
				for (SQL_Field field : table._fields)
				{
					interpreter._symbolTable.removeSymbol(field._name);
				}
				return;
			}
		}
		
		throw new RuntimeException("SELECT returned no values");
	}
}
