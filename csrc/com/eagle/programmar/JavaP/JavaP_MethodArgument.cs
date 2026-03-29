// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.JavaP
{
	using JavaP_OneClassGeneric = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
	using JavaP_Identifier = com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_Punctuation = com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
	using JavaP_QualifiedName = com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class JavaP_MethodArgument : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_Punctuation XXquestion = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('?');
		public JavaP_Punctuation XXquestion = new JavaP_Punctuation('?');

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_MethodArg extends com.eagle.tokens.TokenSequence
		public class JavaP_MethodArg : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_QuestionExtends question;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_TypeExtends type;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName name;
			public JavaP_QualifiedName name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT JavaP_OneClassGeneric generic;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT JavaP_EmptySubscript subscript;
			public  OPT;

			public class JavaP_QuestionExtends : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation question = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('?');
				public JavaP_Punctuation question = new JavaP_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword EXTENDS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("extends");
				public JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
			}

			public class JavaP_TypeExtends : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Identifier typeName;
				public JavaP_Identifier typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword EXTENDS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("extends");
				public JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
			}

			public class JavaP_EmptySubscript : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
			}
		}
	}
}
