// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_StateMachine;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_FieldEnum;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_Row;
import com.eagle.programmar.SQL.SQL_StateMachine.SQL_Table;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_InsertStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("sql_insert.asp") SQL_Keyword INSERT = new SQL_Keyword("INSERT");
	public @S(20) @OPT SQL_OrReplace orReplace;
	public @S(30) SQL_Keyword INTO = new SQL_Keyword("INTO");
	public @S(40) SQL_Identifier_Reference table;
	public @S(50) SQL_InsertClause clause;
	public @S(60) PunctuationSemicolon semicolon;

	public static class SQL_OrReplace extends TokenSequence
	{
		public @S(10) SQL_Keyword OR = new SQL_Keyword("OR");
		public @S(20) SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
	}

	public static class SQL_InsertClause extends TokenChooser
	{
		public @CHOICE SQL_InsertSet insertSet;
		public @CHOICE SQL_InsertValues insertValues;
	}
	
	public static class SQL_InsertSet extends TokenSequence
	{
		public @S(10) SQL_Keyword SET = new SQL_Keyword("SET");
		public @S(20) SeparatedList<SQL_InsertAssignment, PunctuationComma> assignments;

		public static class SQL_InsertAssignment extends TokenSequence
		{
			public @S(10) SQL_Identifier_Reference var;
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_Expression value;
		}
	}

	public static class SQL_InsertValues extends TokenSequence
	{
		public @S(10) @OPT SQL_InsertNames insertNames;
		public @S(20) SQL_Keyword VALUES = new SQL_Keyword("VALUES");
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) SeparatedList<SQL_Expression, PunctuationComma> values;
		public @S(50) PunctuationRightParen rightParen;

		public static class SQL_InsertNames extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) SeparatedList<SQL_Identifier_Reference, PunctuationComma> vars;
			public @S(30) PunctuationRightParen rightParen;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		SQL_StateMachine state = (SQL_StateMachine) interpreter._state;
		
		// Find the right table
		String tableName = table.getValue().toUpperCase();
		if (! state._tables.containsKey(tableName))
		{
			throw new RuntimeException("Unable to find table " + tableName);
		}
		SQL_Table stable = state._tables.get(tableName);
		
		if (! (clause.getWhich() instanceof SQL_InsertValues))
		{
			throw new RuntimeException("Can only handle Insert values");
		}
		SQL_InsertValues insert = (SQL_InsertValues) clause.getWhich();
		int numValues = insert.values.getPrimaryCount();
		int numFields = stable._fields.size();
		if (numValues != numFields)
		{
			throw new RuntimeException("Number of fields to Insert is " + numValues +
					", but should be " + numFields);
		}
		
		SQL_Row row = new SQL_Row();
		row._values = new ArrayList<EagleValue>();
		stable._rows.add(row);
		for (int i = 0; i < numValues; i++)
		{
			SQL_Expression expr = insert.values.getPrimaryElement(i);
			SQL_FieldEnum type = stable._fields.get(i)._type;
			switch (type)
			{
			case SQL_FieldInteger:
				int val = interpreter.getIntValue(expr);
				row._values.add(new EagleInteger(val));
				break;
			case SQL_FieldString:
				String str = interpreter.getStrValue(expr);
				row._values.add(new EagleString(str));
				break;
			default:
				throw new RuntimeException("Unexpected field type: " + type.toString());
			}
		}
	}
}
