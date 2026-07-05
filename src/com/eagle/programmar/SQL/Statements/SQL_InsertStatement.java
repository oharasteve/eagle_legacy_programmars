// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Table;
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
		public @CHOICE SQL_InsertSet XXinsertSet;
		public @CHOICE SQL_InsertValues XXinsertValues;
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
		// Find the right table
		String tableName = table.getValue().toUpperCase();
		EagleValue val = interpreter.findSymbol(tableName);
		if (!(val instanceof SQL_Table))
		{
			throw new RuntimeException("Can only insert values into a Table");
		}
		SQL_Table stable = (SQL_Table) val;

		if (!(clause.getWhich() instanceof SQL_InsertValues))
		{
			throw new RuntimeException("Can only handle Insert values");
		}
		SQL_InsertValues insert = (SQL_InsertValues) clause.getWhich();
		int numValues = insert.values.getPrimaryCount();
		int numColumns = stable.getNumberColumns();
		if (numValues != numColumns)
		{
			throw new RuntimeException("Number of fields to Insert is " + numValues +
					", but should be " + numColumns);
		}

		ArrayList<EagleValue> values = new ArrayList<EagleValue>();
		for (int col = 0; col < numValues; col++)
		{
			SQL_Expression expr = insert.values.getPrimaryElement(col);
			TypeEnum type = stable.getColumnType(col);
			switch (type)
			{
			case INTEGER:
				int intVal = interpreter.getIntValue(expr);
				values.add(new EagleInteger(intVal));
				break;
			case STRING:
				String strVal = interpreter.getStrValue(expr);
				values.add(new EagleString(strVal));
				break;
			default:
				throw new RuntimeException("Unexpected field type: " + type.toString());
			}
		}
		stable.addRow(values);
	}
}
