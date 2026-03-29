// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Condition = com.eagle.programmar.Natural.Natural_Condition;
	using Natural_Expression = com.eagle.programmar.Natural.Natural_Expression;
	using Natural_Label = com.eagle.programmar.Natural.Natural_Label;
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_ReadStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("sm/read.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword READ = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("READ");
		public @DOC("sm/read.htm") Natural_Keyword READ = new Natural_Keyword("READ");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Read_Number_Records numberRecords;
		public @OPT Natural_Read_Number_Records numberRecords;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
		public Natural_Identifier_Reference viewName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_PhysicalLogical physicalLogical;
		public @OPT Natural_PhysicalLogical physicalLogical;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Natural_ReadClause> clauses;
		public @OPT TokenList<Natural_ReadClause> clauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public @OPT TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Natural_KeywordChoice endRead = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("END-READ", "END-ALL");
		public @OPT Natural_KeywordChoice endRead = new Natural_KeywordChoice("END-READ", "END-ALL");

		public static class Natural_ReadClause extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Read_By XXreadBy;
			public Natural_Read_By XXreadBy;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Read_Starting XXreadStarting;
			public Natural_Read_Starting XXreadStarting;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Read_Where XXreadWhere;
			public Natural_Read_Where XXreadWhere;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Read_Ending XXreadEnding;
			public Natural_Read_Ending XXreadEnding;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Read_With XXreadWith;
			public Natural_Read_With XXreadWith;
		}

		public static class Natural_PhysicalLogical extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword IN = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("IN");
			public @OPT Natural_Keyword IN = new Natural_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice device = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("PHYSICAL", "LOGICAL");
			public Natural_KeywordChoice device = new Natural_KeywordChoice("PHYSICAL", "LOGICAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword SEQUENCE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SEQUENCE");
			public @OPT Natural_Keyword SEQUENCE = new Natural_Keyword("SEQUENCE");
		}

		public static class Natural_Read_Number_Records extends TokenSequence
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

		public static class Natural_Read_By extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword BY = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BY");
			public Natural_Keyword BY = new Natural_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Condition cond;
			public Natural_Condition cond;
		}

		public static class Natural_Read_Starting extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword STARTING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("STARTING");
			public @OPT Natural_Keyword STARTING = new Natural_Keyword("STARTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword FROM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FROM");
			public Natural_Keyword FROM = new Natural_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Expression expr;
			public Natural_Expression expr;
		}

		public static class Natural_Read_Ending extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("ENDING");
			public Natural_Keyword ENDING = new Natural_Keyword("ENDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword AT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AT");
			public Natural_Keyword AT = new Natural_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Expression expr;
			public Natural_Expression expr;
		}

		public static class Natural_Read_Where extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword WHERE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("WHERE");
			public Natural_Keyword WHERE = new Natural_Keyword("WHERE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Condition cond;
			public Natural_Condition cond;
		}

		public static class Natural_Read_With extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword WITH = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("WITH");
			public Natural_Keyword WITH = new Natural_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Condition cond;
			public Natural_Condition cond;
		}
	}

}
