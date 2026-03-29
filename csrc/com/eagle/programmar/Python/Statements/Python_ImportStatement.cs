// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using CHOICE = com.eagle.tokens.TokenChooser.CHOICE;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class Python_ImportStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-import-statement") com.eagle.programmar.Python.Terminals.Python_Keyword IMPORT = new com.eagle.programmar.Python.Terminals.Python_Keyword("import");
		public @DOC("simple_stmts.html#the-import-statement") Python_Keyword IMPORT = new Python_Keyword("import");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_ImportWhat what;
		public Python_ImportWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_FromImportAs fromAs;
		public @OPT Python_FromImportAs fromAs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Python_MoreImports> moreImports;
		public @OPT TokenList<Python_MoreImports> moreImports;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Python_Comment comment;
		public @OPT Python_Comment comment;

		public static class Python_ImportWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ImportFile XXimportName;
			public Python_ImportFile XXimportName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationStar XXstar;
			public PunctuationStar XXstar;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_ImportList extends com.eagle.tokens.TokenSequence
			public static class Python_ImportList extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) Python_ImportListItem list;
				public @SYNTAX(typeof(Python_Multiline_Syntax)) Python_ImportListItem list;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_FromImportAs fromAs;
				public @OPT Python_FromImportAs fromAs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;

				public static class Python_ImportListItem extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_Expression expr;
					public @OPT Python_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_FromImportAs fromAs;
					public @OPT Python_FromImportAs fromAs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Python_MoreImportListItem> nextItem;
					public @OPT TokenList<Python_MoreImportListItem> nextItem;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma comma;
					public @OPT PunctuationComma comma;

					public static class Python_MoreImportListItem extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
						public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression expr;
						public Python_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_FromImportAs fromAs;
						public @OPT Python_FromImportAs fromAs;
					}
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_ImportFile extends com.eagle.tokens.TokenSequence
		public static class Python_ImportFile extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Python.Python_Variable, com.eagle.tokens.punctuation.PunctuationPeriod> importName;
			public SeparatedList<Python_Variable, PunctuationPeriod> importName;
		}

		public static class Python_MoreImports extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_ImportFile importName;
			public Python_ImportFile importName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_FromImportAs fromAs;
			public @OPT Python_FromImportAs fromAs;
		}

		public static class Python_FromImportAs extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword AS = new com.eagle.programmar.Python.Terminals.Python_Keyword("as");
			public Python_Keyword AS = new Python_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Variable asName;
			public Python_Variable asName;
		}
	}

}
