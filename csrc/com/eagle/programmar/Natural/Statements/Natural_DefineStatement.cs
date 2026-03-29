// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Subscript = com.eagle.programmar.Natural.Natural_Subscript;
	using Natural_Data_Definition = com.eagle.programmar.Natural.Symbols.Natural_Data_Definition;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Comment = com.eagle.programmar.Natural.Terminals.Natural_Comment;
	using Natural_DataType = com.eagle.programmar.Natural.Terminals.Natural_DataType;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Level = com.eagle.programmar.Natural.Terminals.Natural_Level;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using Natural_Punctuation = com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_DefineStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/defineda.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword DEFINE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DEFINE");
		public @DOC("sm/defineda.htm") Natural_Keyword DEFINE = new Natural_Keyword("DEFINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword DATA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DATA");
		public Natural_Keyword DATA = new Natural_Keyword("DATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword LOCAL = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("LOCAL");
		public Natural_Keyword LOCAL = new Natural_Keyword("LOCAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Natural_DataLine> dataLines;
		public TokenList<Natural_DataLine> dataLines;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDDEFINE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-DEFINE");
		public Natural_Keyword ENDDEFINE = new Natural_Keyword("END-DEFINE");

		public static class Natural_DataLine extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Comment comment;
			public @OPT Natural_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Level level;
			public Natural_Level level;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword REDEFINE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("REDEFINE");
			public @OPT Natural_Keyword REDEFINE = new Natural_Keyword("REDEFINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Symbols.Natural_Data_Definition name;
			public Natural_Data_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_DataView dataView;
			public @OPT Natural_DataView dataView;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Natural_DataDeclaration dataDeclaration;
			public @OPT Natural_DataDeclaration dataDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Natural_Subscript subscript;
			public @OPT Natural_Subscript subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Natural_DataInitialization init;
			public @OPT Natural_DataInitialization init;

			public static class Natural_DataView extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword VIEW = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("VIEW");
				public Natural_Keyword VIEW = new Natural_Keyword("VIEW");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
				public Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference dbTable;
				public Natural_Identifier_Reference dbTable;
			}

			public static class Natural_DataDeclaration extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_DataType dataType;
				public Natural_DataType dataType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

			public static class Natural_DataInitialization extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword INIT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("INIT");
				public Natural_Keyword INIT = new Natural_Keyword("INIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Punctuation lessThan = new com.eagle.programmar.Natural.Terminals.Natural_Punctuation('<');
				public Natural_Punctuation lessThan = new Natural_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
				public Natural_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Terminals.Natural_Punctuation greaterThan = new com.eagle.programmar.Natural.Terminals.Natural_Punctuation('>');
				public Natural_Punctuation greaterThan = new Natural_Punctuation('>');
			}

			// These are special -- they are not parsed. A post-edit step fills them in.
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP TokenList<Natural_DataLine> children = new com.eagle.tokens.TokenList<Natural_DataLine>();
			public TokenList<Natural_DataLine> children = new TokenList<Natural_DataLine>();
			public Natural_DataParent parentDeclContainer = new Natural_DataParent();

			public static class Natural_DataParent // Does NOT extend GenericToken -- don't want this parsed or looked at
			{
				public Natural_DataLine parentDecl;
			}
		}
	}

}
