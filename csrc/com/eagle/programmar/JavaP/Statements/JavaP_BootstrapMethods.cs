// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.JavaP.Statements
{
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_HashNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
	using JavaP_Identifier = com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_LClassName = com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
	using JavaP_Number = com.eagle.programmar.JavaP.Terminals.JavaP_Number;
	using JavaP_RestOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class JavaP_BootstrapMethods : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword BOOTSTRAP_METHODS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("BootstrapMethods");
		public JavaP_Keyword BOOTSTRAP_METHODS = new JavaP_Keyword("BootstrapMethods");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<JavaP_Methods> methods;
		public TokenList<JavaP_Methods> methods;

		public class JavaP_Methods : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Number number;
			public JavaP_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon1;
			public PunctuationColon colon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber ref_id;
			public JavaP_HashNumber ref_id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_Identifier id;
			public JavaP_Identifier id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.Terminals.JavaP_Identifier, com.eagle.tokens.punctuation.PunctuationSlash> ParentNames;
			public SeparatedList<JavaP_Identifier, PunctuationSlash> ParentNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.JavaP.Terminals.JavaP_Identifier methodName;
			public JavaP_Identifier methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationColon colon2;
			public PunctuationColon colon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.SeparatedList<com.eagle.programmar.JavaP.Terminals.JavaP_LClassName, com.eagle.tokens.punctuation.PunctuationSemicolon> classes;
			public SeparatedList<JavaP_LClassName, PunctuationSemicolon> classes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
			public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) com.eagle.programmar.JavaP.Terminals.JavaP_LClassName className1;
			public JavaP_LClassName className1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public PunctuationSemicolon semicolon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(150) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(160) com.eagle.programmar.JavaP.Terminals.JavaP_LClassName className2;
			public JavaP_LClassName className2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(170) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon3;
			public PunctuationSemicolon semicolon3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(180) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln1;
			public JavaP_EndOfLine eoln1;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(190) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword METHOD = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Method");
			public JavaP_Keyword METHOD = new JavaP_Keyword("Method");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(200) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword ARGUMENTS = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("arguments");
			public JavaP_Keyword ARGUMENTS = new JavaP_Keyword("arguments");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(210) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(220) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln2;
			public JavaP_EndOfLine eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(230) com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber arg_id;
			public JavaP_HashNumber arg_id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(240) com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine restOfLine;
			public JavaP_RestOfLine restOfLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(250) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln3;
			public JavaP_EndOfLine eoln3;
		}

	}

}
