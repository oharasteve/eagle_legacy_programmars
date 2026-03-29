// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Condition = com.eagle.programmar.Natural.Natural_Condition;
	using Natural_Label = com.eagle.programmar.Natural.Natural_Label;
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_FindStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("sm/find.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword FIND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FIND");
		public @DOC("sm/find.htm") Natural_Keyword FIND = new Natural_Keyword("FIND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_FindType findType;
		public Natural_FindType findType;

		public static class Natural_FindType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_FindNoBlock extends com.eagle.tokens.TokenSequence
			public static class Natural_FindNoBlock extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice howMany = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("FIRST", "NUMBER", "UNIQUE");
				public Natural_KeywordChoice howMany = new Natural_KeywordChoice("FIRST", "NUMBER", "UNIQUE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Find_Number_Records numberRecords;
				public @OPT Natural_Find_Number_Records numberRecords;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
				public Natural_Identifier_Reference viewName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_KeywordChoice device = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("PHYSICAL", "LOGICAL");
				public @OPT Natural_KeywordChoice device = new Natural_KeywordChoice("PHYSICAL", "LOGICAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<Natural_Find_Clause> clauses;
				public TokenList<Natural_Find_Clause> clauses;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_FindWithBlock extends com.eagle.tokens.TokenSequence
			public static class Natural_FindWithBlock extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Find_Number_Records numberRecords;
				public @OPT Natural_Find_Number_Records numberRecords;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
				public Natural_Identifier_Reference viewName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_KeywordChoice device = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("PHYSICAL", "LOGICAL");
				public @OPT Natural_KeywordChoice device = new Natural_KeywordChoice("PHYSICAL", "LOGICAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Natural_Find_Clause> clauses;
				public TokenList<Natural_Find_Clause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
				public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDFIND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-FIND");
				public Natural_Keyword ENDFIND = new Natural_Keyword("END-FIND");
			}
		}

		public static class Natural_Find_Clause extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_By_Condition XXfindByCond;
			public Natural_Find_By_Condition XXfindByCond;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_By_ISN XXfindByISN;
			public Natural_Find_By_ISN XXfindByISN;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_From XXfindFrom;
			public Natural_Find_From XXfindFrom;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_Coupled XXcoupled;
			public Natural_Find_Coupled XXcoupled;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_Sorted_By XXsortedBy;
			public Natural_Find_Sorted_By XXsortedBy;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Find_Where XXfindWhere;
			public Natural_Find_Where XXfindWhere;
		}

		public static class Natural_Find_Number_Records extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Number number;
			public Natural_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class Natural_Find_By_ISN extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword BY = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BY");
			public Natural_Keyword BY = new Natural_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference isn;
			public Natural_Identifier_Reference isn;
		}

		public static class Natural_Find_Coupled extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice andOr = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("AND", "OR");
			public Natural_KeywordChoice andOr = new Natural_KeywordChoice("AND", "OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword COUPLED = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("COUPLED");
			public Natural_Keyword COUPLED = new Natural_Keyword("COUPLED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword TO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("TO");
			public @OPT Natural_Keyword TO = new Natural_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Keyword FILE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FILE");
			public @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
			public Natural_Identifier_Reference viewName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Natural_Find_Coupled_Via via;
			public @OPT Natural_Find_Coupled_Via via;

			public static class Natural_Find_Coupled_Via extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword VIA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("VIA");
				public Natural_Keyword VIA = new Natural_Keyword("VIA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference var1;
				public Natural_Identifier_Reference var1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_Find_Via_Equals equals;
				public Natural_Find_Via_Equals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Keyword TO = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("TO");
				public @OPT Natural_Keyword TO = new Natural_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference var2;
				public Natural_Identifier_Reference var2;

				public static class Natural_Find_Via_Equals extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationEquals XXequals;
					public PunctuationEquals XXequals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_KeywordChoice XXEQUALS = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("EQ", "EQUAL");
					public Natural_KeywordChoice XXEQUALS = new Natural_KeywordChoice("EQ", "EQUAL");
				}
			}
		}

		public static class Natural_Find_Sorted_By extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword SORTED = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SORTED");
			public @OPT Natural_Keyword SORTED = new Natural_Keyword("SORTED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword BY = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BY");
			public Natural_Keyword BY = new Natural_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Variable> vars;
			public TokenList<Natural_Variable> vars;
		}

		public static class Natural_Find_By_Condition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice byWith = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("BY", "WITH");
			public Natural_KeywordChoice byWith = new Natural_KeywordChoice("BY", "WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Find_With_Limit findWithLimit;
			public @OPT Natural_Find_With_Limit findWithLimit;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Condition cond;
			public Natural_Condition cond;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Natural_Find_Retain retain;
			public @OPT Natural_Find_Retain retain;

			public static class Natural_Find_With_Limit extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword LIMIT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("LIMIT");
				public Natural_Keyword LIMIT = new Natural_Keyword("LIMIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_Find_Number_Records limit;
				public Natural_Find_Number_Records limit;
			}

			public static class Natural_Find_Retain extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword RETAIN = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("RETAIN");
				public Natural_Keyword RETAIN = new Natural_Keyword("RETAIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword AS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AS");
				public Natural_Keyword AS = new Natural_Keyword("AS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
				public Natural_Literal literal;
			}
		}

		public static class Natural_Find_From extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword STARTING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("STARTING");
			public @OPT Natural_Keyword STARTING = new Natural_Keyword("STARTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword FROM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FROM");
			public Natural_Keyword FROM = new Natural_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
			public Natural_Literal literal;
		}

		public static class Natural_Find_Where extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword WHERE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("WHERE");
			public Natural_Keyword WHERE = new Natural_Keyword("WHERE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Condition cond;
			public Natural_Condition cond;
		}
	}

}
