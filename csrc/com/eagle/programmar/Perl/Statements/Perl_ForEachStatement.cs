// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Perl_ForEachStatement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_ForEachAsStatement extends com.eagle.tokens.TokenSequence
		public class Perl_ForEachAsStatement : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.foreach.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword FOREACH = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("foreach");
			public @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression expr;
			public Perl_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Terminals.Perl_Keyword AS = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("as");
			public Perl_Keyword AS = new Perl_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition var;
			public Perl_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Perl_ForEachArrow arrow;
			public @OPT Perl_ForEachArrow arrow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Perl.Perl_Statement stmt;
			public Perl_Statement stmt;

			public static class Perl_ForEachArrow extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation equalsGreater = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("=>");
				public Perl_Punctuation equalsGreater = new Perl_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Variable var;
				public Perl_Variable var;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_ForEachNoAsStatement extends com.eagle.tokens.TokenSequence
		public static class Perl_ForEachNoAsStatement extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.foreach.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword FOREACH = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("foreach");
			public @DOC("control-structures.foreach.php") Perl_Keyword FOREACH = new Perl_Keyword("foreach");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_ForEachVariable var;
			public @OPT Perl_ForEachVariable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Perl_Expression expr;
			public Perl_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Perl.Perl_Statement stmt;
			public Perl_Statement stmt;

			public static class Perl_ForEachVariable extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Perl_Keyword MY = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("my");
				public @OPT Perl_Keyword MY = new Perl_Keyword("my");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
				public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition var;
				public Perl_Variable_Definition var;
			}
		}
	}

}
