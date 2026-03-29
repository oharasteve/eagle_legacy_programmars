// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_Number = com.eagle.programmar.SQL.Terminals.SQL_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class SQL_SelectStatement : TokenSequence, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) SQL_SelectStmt selectStatement;
		public SQL_SelectStmt selectStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<SQL_SelectUnion> more;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SelectLimit selectLimit;
		public  OPT;

		public class SQL_SelectUnion : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword UNION = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("UNION");
			public SQL_Keyword UNION = new SQL_Keyword("UNION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_Keyword ALL = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("ALL");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) SQL_SelectStmt selectStatement;
			public SQL_SelectStmt selectStatement;
		}

		public class SelectLimit : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword LIMIT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("LIMIT");
			public SQL_Keyword LIMIT = new SQL_Keyword("LIMIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Number number;
			public SQL_Number number;
		}

		public class SQL_SelectStmt : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sql_select.asp") com.eagle.programmar.SQL.Terminals.SQL_Keyword SELECT = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("SELECT");
			public @DOC("sql_select.asp") SQL_Keyword SELECT = new SQL_Keyword("SELECT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<SQL_SelectWhat, com.eagle.tokens.punctuation.PunctuationComma> what;
			public SeparatedList<SQL_SelectWhat, PunctuationComma> what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<SQL_SelectClause> clauses;
			public @OPT TokenList<SQL_SelectClause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationSemicolon semicolon;
			public @OPT PunctuationSemicolon semicolon;
		}

		public static class SQL_SelectWhat extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.SQL_Expression expr;
			public SQL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_SelectAs as;
			public @OPT SQL_SelectAs @as;

			public static class SQL_SelectAs extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SQL_Keyword AS = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("AS");
				public @OPT SQL_Keyword AS = new SQL_Keyword("AS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference name;
				public SQL_Identifier_Reference name;
			}
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// This is essentially a PRINT statement
			if (more != null && more.size() > 0)
			{
				throw new Exception("Cannot handle SELECT / UNION yet");
			}

			if (selectStatement.clauses != null && selectStatement.clauses.size() > 0)
			{
				throw new Exception("Cannot handle SELECT clauses yet");
			}

			for (int i = 0; i < selectStatement.what.getPrimaryCount(); i++)
			{
				SQL_SelectWhat what = selectStatement.what.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(what.expr);
				Console.Write(val); // It should have its own newline '\n'
			}
		}

		public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Just treat it like a PRINT statement for now
			if (more != null && more.size() > 0)
			{
				throw new Exception("Cannot handle SELECT / UNION yet");
			}

			if (selectStatement.clauses != null && selectStatement.clauses.size() > 0)
			{
				throw new Exception("Cannot handle SELECT clauses yet");
			}

			List<AbstractStatement> result = new List<AbstractStatement>();
			for (int i = 0; i < selectStatement.what.getPrimaryCount(); i++)
			{
				SQL_SelectWhat what = selectStatement.what.getPrimaryElement(i);
				AbstractExpression line = transformer.transformExpression(generator, what.expr);
				result.Add(generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, false, false, this));
			}
			return result;
		}
	}

}
