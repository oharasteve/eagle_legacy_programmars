// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Statements
{
	using JavaP_Syntax = com.eagle.programmar.JavaP.JavaP_Syntax;
	using JavaP_OneClassGeneric = com.eagle.programmar.JavaP.Parameters.JavaP_OneClassGeneric;
	using JavaP_Comment = com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_HashNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
	using JavaP_HexNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_Number = com.eagle.programmar.JavaP.Terminals.JavaP_Number;
	using JavaP_Punctuation = com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
	using JavaP_QualifiedName = com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class JavaP_PublicClass : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT JavaP_Keyword PUBLIC = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("public");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_Keyword ABSTRACT = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("abstract");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT JavaP_Keyword FINAL = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("final");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("class", "interface");
		public JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "interface");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName className;
		public JavaP_QualifiedName className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT JavaP_OneClassGeneric generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<JavaP_ClassExtends> classExtends;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<JavaP_ClassImplements> classImplements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT JavaP_MinorVersion minorVersion;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT JavaP_MajorVersion majorVersion;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT JavaP_Flags flags;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT JavaP_ThisClass thisClass;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) @OPT JavaP_SuperClass superClass;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) @OPT JavaP_Interfaces interfaces;
		public  OPT;

		public class JavaP_ExtendsClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName qualifiedName;
			public JavaP_QualifiedName qualifiedName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT JavaP_ExtendsTemplatedClass genericName;
			public  OPT;

			public class JavaP_ExtendsTemplatedClass : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation lessThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('<');
				public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName, com.eagle.tokens.punctuation.PunctuationComma> names;
				public SeparatedList<JavaP_QualifiedName, PunctuationComma> names;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation greaterThan = new com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation('>');
				public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
			}
		}

		public class JavaP_ClassExtends : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword EXTENDS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("extends");
			public JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<JavaP_ExtendsClass, com.eagle.tokens.punctuation.PunctuationComma> parentClassName;
			public SeparatedList<JavaP_ExtendsClass, PunctuationComma> parentClassName;
		}

		public class JavaP_ClassImplements : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword IMPLEMENTS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("implements");
			public JavaP_Keyword IMPLEMENTS = new JavaP_Keyword("implements");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<JavaP_ExtendsClass, com.eagle.tokens.punctuation.PunctuationComma> parentClassName;
			public SeparatedList<JavaP_ExtendsClass, PunctuationComma> parentClassName;
		}

		public class JavaP_Flags : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword FLAGS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("flags");
			public JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT JavaP_FlagCode flagCode;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<JavaP_Flag, com.eagle.tokens.punctuation.PunctuationComma> flags;
			public SeparatedList<JavaP_Flag, PunctuationComma> flags;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;

			public class JavaP_FlagCode : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber hex;
				public JavaP_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

			public class JavaP_Flag : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice name = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice(com.eagle.programmar.JavaP.JavaP_Syntax.ACC_CODES);
				public JavaP_KeywordChoice name = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
			}
		}

		public class JavaP_ThisClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword THIS_CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("this_class");
			public JavaP_Keyword THIS_CLASS = new JavaP_Keyword("this_class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber classNumber;
			public JavaP_HashNumber classNumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT JavaP_Comment className;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;
		}

		public class JavaP_SuperClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword SUPER_CLASS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("super_class");
			public JavaP_Keyword SUPER_CLASS = new JavaP_Keyword("super_class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber classNumber;
			public JavaP_HashNumber classNumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT JavaP_Comment className;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;
		}

		public class JavaP_Interfaces : TokenSequence
		{
			// interfaces: 0, fields: 3, methods: 12, attributes: 1
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword INTERFACES = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("interfaces");
			public JavaP_Keyword INTERFACES = new JavaP_Keyword("interfaces");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon1;
			public PunctuationColon colon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_Number numInterfaces;
			public JavaP_Number numInterfaces;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma1;
			public PunctuationComma comma1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword FIELDS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("fields");
			public JavaP_Keyword FIELDS = new JavaP_Keyword("fields");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationColon colon2;
			public PunctuationColon colon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.JavaP.Terminals.JavaP_Number numFields;
			public JavaP_Number numFields;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationComma comma2;
			public PunctuationComma comma2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword METHODS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("methods");
			public JavaP_Keyword METHODS = new JavaP_Keyword("methods");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationColon colon3;
			public PunctuationColon colon3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.JavaP.Terminals.JavaP_Number numMethods;
			public JavaP_Number numMethods;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.tokens.punctuation.PunctuationComma comma3;
			public PunctuationComma comma3;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword ATTRIBTES = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("attributes");
			public JavaP_Keyword ATTRIBTES = new JavaP_Keyword("attributes");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.tokens.punctuation.PunctuationColon colon4;
			public PunctuationColon colon4;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) com.eagle.programmar.JavaP.Terminals.JavaP_Number numAttributess;
			public JavaP_Number numAttributess;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;
		}
	}

}
