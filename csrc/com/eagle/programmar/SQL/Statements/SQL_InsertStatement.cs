// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Table = com.eagle.programmar.SQL.SQL_Table;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class SQL_InsertStatement : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sql_insert.asp") com.eagle.programmar.SQL.Terminals.SQL_Keyword INSERT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("INSERT");
		public @DOC("sql_insert.asp") SQL_Keyword INSERT = new SQL_Keyword("INSERT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_OrReplace orReplace;
		public @OPT SQL_OrReplace orReplace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword INTO = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("INTO");
		public SQL_Keyword INTO = new SQL_Keyword("INTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference table;
		public SQL_Identifier_Reference table;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) SQL_InsertClause clause;
		public SQL_InsertClause clause;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public static class SQL_OrReplace extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword OR = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("OR");
			public SQL_Keyword OR = new SQL_Keyword("OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword REPLACE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("REPLACE");
			public SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
		}

		public static class SQL_InsertClause extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_InsertSet XXinsertSet;
			public SQL_InsertSet XXinsertSet;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_InsertValues XXinsertValues;
			public SQL_InsertValues XXinsertValues;
		}

		public static class SQL_InsertSet extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword SET = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SET");
			public SQL_Keyword SET = new SQL_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<SQL_InsertAssignment, com.eagle.tokens.punctuation.PunctuationComma> assignments;
			public SeparatedList<SQL_InsertAssignment, PunctuationComma> assignments;

			public static class SQL_InsertAssignment extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference var;
				public SQL_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.SQL_Expression value;
				public SQL_Expression value;
			}
		}

		public static class SQL_InsertValues extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SQL_InsertNames insertNames;
			public @OPT SQL_InsertNames insertNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword VALUES = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("VALUES");
			public SQL_Keyword VALUES = new SQL_Keyword("VALUES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.SQL_Expression, com.eagle.tokens.punctuation.PunctuationComma> values;
			public SeparatedList<SQL_Expression, PunctuationComma> values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public static class SQL_InsertNames extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> vars;
				public SeparatedList<SQL_Identifier_Reference, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// Find the right table
			string tableName = table.getValue().ToUpper();
			EagleValue val = interpreter.findSymbol(tableName);
			if (!(val is SQL_Table))
			{
				throw new Exception("Can only insert values into a Table");
			}
			SQL_Table stable = (SQL_Table) val;

			if (!(clause.getWhich() is SQL_InsertValues))
			{
				throw new Exception("Can only handle Insert values");
			}
			SQL_InsertValues insert = (SQL_InsertValues) clause.getWhich();
			int numValues = insert.values.getPrimaryCount();
			int numColumns = stable.NumberColumns;
			if (numValues != numColumns)
			{
				throw new Exception("Number of fields to Insert is " + numValues + ", but should be " + numColumns);
			}

			List<EagleValue> values = new List<EagleValue>();
			for (int col = 0; col < numValues; col++)
			{
				SQL_Expression expr = insert.values.getPrimaryElement(col);
				TypeEnum type = stable.getColumnType(col);
				switch (type)
				{
				case INTEGER:
					int intVal = interpreter.getIntValue(expr);
					values.Add(new EagleInteger(intVal));
					break;
				case STRING:
					string strVal = interpreter.getStrValue(expr);
					values.Add(new EagleString(strVal));
					break;
				default:
					throw new Exception("Unexpected field type: " + type.ToString());
				}
			}
			stable.addRow(values);
		}
	}

}
