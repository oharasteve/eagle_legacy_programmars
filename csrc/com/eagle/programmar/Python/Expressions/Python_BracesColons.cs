// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class Python_BracesColons : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_EndOfLine eoln1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) Python_Dictionary dictionary;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_EndOfLine eoln2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public class Python_Dictionary : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_DictionaryElement element;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Python_MoreDictionaryElement> nextElement;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment2;
			public  OPT;

			public class Python_DictionaryElement : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression key;
				public Python_Expression key;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_EndOfLine eoln;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_Comment comment;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Python.Python_Expression value;
				public Python_Expression value;
			}

			public class Python_MoreDictionaryElement : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Python_DictionaryElement element;
				public Python_DictionaryElement element;
			}
		}

		public static Python_Expression generateDictionary(AbstractToken source)
		{
			Python_BracesColons braces = new Python_BracesColons();
			braces.leftBrace = new PunctuationLeftBrace();
			braces.rightBrace = new PunctuationRightBrace();
			braces.setTransformationSource(source);
			return Python_Generator.wrapExpression(braces);
		}
	}

}
