// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Decorators = com.eagle.programmar.Python.Python_Decorators;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Type = com.eagle.programmar.Python.Python_Type;
	using Python_Class_Definition = com.eagle.programmar.Python.Symbols.Python_Class_Definition;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Python_ClassDeclaration : TokenSequence, AbstractClass
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Python.Python_Decorators> decorators;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("compound_stmts.html#class-definitions") @NOSPACE Python_Keyword CLASS = new com.eagle.programmar.Python.Terminals.Python_Keyword("class");
		public @DOC("compound_stmts.html#class-definitions") Python_Keyword CLASS = new Python_Keyword("class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Symbols.Python_Class_Definition name;
		public Python_Class_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_ClassSuper superClass;
		public @OPT Python_ClassSuper superClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationColon colon;
		public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) Python_StatementBlock statements;
		public Python_StatementBlock statements;

		public static class Python_ClassSuper extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_WhatSuper whatSuper;
			public @OPT Python_WhatSuper whatSuper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public static class Python_WhatSuper extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Type type;
				public Python_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_ClassSuper superClass;
				public @OPT Python_ClassSuper superClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) com.eagle.tokens.TokenList<Python_MoreTypes> moreTypes;
				public @OPT TokenList<Python_MoreTypes> moreTypes;
			}

			public static class Python_MoreTypes extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Type type;
				public Python_Type type;
			}
		}
	}

}
