// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

namespace com.eagle.programmar.JavaP.Statements
{
	using JavaP_CodeBlock = com.eagle.programmar.JavaP.JavaP_CodeBlock;
	using JavaP_MethodArgument = com.eagle.programmar.JavaP.JavaP_MethodArgument;
	using JavaP_CodeLineNumbers = com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
	using JavaP_CodeLocalValues = com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
	using JavaP_OneClassConstantValue = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassConstantValue;
	using JavaP_OneClassDeprecated = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassDeprecated;
	using JavaP_OneClassDescriptor = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassDescriptor;
	using JavaP_OneClassExceptions = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassExceptions;
	using JavaP_OneClassFlags = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassFlags;
	using JavaP_OneClassGeneric = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
	using JavaP_OneClassMethodParameters = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassMethodParameters;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_QualifiedName = com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class JavaP_Classes : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln1;
		public JavaP_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<JavaP_OneClass> oneClass;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln2;
		public JavaP_EndOfLine eoln2;

		public class JavaP_OneClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<JavaP_Modifier> modifier;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) JavaP_OneClassHeader header;
			public JavaP_OneClassHeader header;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln1;
			public JavaP_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<JavaP_OneClassParameter> parameters;
			public TokenList<JavaP_OneClassParameter> parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT JavaP_EndOfLine eoln2;
			public  OPT;

			public class JavaP_OneClassHeader : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_OneClassRegularHeader extends com.eagle.tokens.TokenSequence
				public class JavaP_OneClassRegularHeader : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName type;
					public JavaP_QualifiedName type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_OneClassGeneric generic;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<JavaP_NoSubscript> subscript;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) JavaP_OneClassWhat what;
					public JavaP_OneClassWhat what;

					public class JavaP_NoSubscript : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
						public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
						public PunctuationRightBracket rightBracket;
					}
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_OneClassStaticHeader extends com.eagle.tokens.TokenSequence
				public class JavaP_OneClassStaticHeader : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
					public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
					public PunctuationRightBrace rightBrace;
				}
			}

			public class JavaP_Modifier : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice PUBLIC = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("abstract", "final", "private", "protected", "public", "static", "synchronized", "volatile");
				public JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice("abstract", "final", "private", "protected", "public", "static", "synchronized", "volatile");
			}

			public class JavaP_OneClassWhat : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST JavaP_QualifiedName XXdata;
				public JavaP_QualifiedName XXdata;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class JavaP_OneClassMethod extends com.eagle.tokens.TokenSequence
				public class JavaP_OneClassMethod : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_QualifiedName name;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.JavaP.JavaP_MethodArgument, com.eagle.tokens.punctuation.PunctuationComma> params;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT JavaP_OneClassThrows classThrows;
					public  OPT;

					public class JavaP_OneClassThrows : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword THROWS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("throws");
						public JavaP_Keyword THROWS = new JavaP_Keyword("throws");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName, com.eagle.tokens.punctuation.PunctuationComma> name;
						public SeparatedList<JavaP_QualifiedName, PunctuationComma> name;
					}
				}
			}

			public class JavaP_OneClassParameter : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeBlock XXcode;
				public JavaP_CodeBlock XXcode;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_Signature XXsignature;
				public JavaP_Signature XXsignature;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_RuntimeVisibleAnnotations XXruntimeAnnotation;
				public JavaP_RuntimeVisibleAnnotations XXruntimeAnnotation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_RuntimeVisibleTypeAnnotations XXruntimeTypeAnnotation;
				public JavaP_RuntimeVisibleTypeAnnotations XXruntimeTypeAnnotation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeLineNumbers XXlineNumbers;
				public JavaP_CodeLineNumbers XXlineNumbers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_CodeLocalValues XXlocalValues;
				public JavaP_CodeLocalValues XXlocalValues;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassDescriptor XXdescriptor;
				public JavaP_OneClassDescriptor XXdescriptor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassFlags XXflags;
				public JavaP_OneClassFlags XXflags;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassConstantValue XXconstantValue;
				public JavaP_OneClassConstantValue XXconstantValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassExceptions XXexceptions;
				public JavaP_OneClassExceptions XXexceptions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassMethodParameters XXmethodParameters;
				public JavaP_OneClassMethodParameters XXmethodParameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JavaP_OneClassDeprecated XXdeprecated;
				public JavaP_OneClassDeprecated XXdeprecated;
			}
		}
	}

}
