// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

namespace com.eagle.programmar.PHP
{
	using PHP_EndTag = com.eagle.programmar.PHP.PHP_Program.PHP_EndTag;
	using PHP_Entry = com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
	using PHP_StartTag = com.eagle.programmar.PHP.PHP_Program.PHP_StartTag;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_Syntax = com.eagle.programmar.Perl.Perl_Syntax;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	// <?php stmts; if(cond) { ?> xxx <?php } else { ?> xxx <?php } ?>

	public class PHP_IfBlock : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) PHP_IfCondition condition;
		public @SYNTAX(typeof(Perl_Syntax)) PHP_IfCondition condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.PHP.PHP_Program.PHP_Entry> ifPart;
		public TokenList<PHP_Entry> ifPart;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) PHP_IfElse elseBlock;
		public @SYNTAX(typeof(Perl_Syntax)) PHP_IfElse elseBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.PHP.PHP_Program.PHP_Entry> elsePart;
		public TokenList<PHP_Entry> elsePart;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) PHP_EndIf endIf;
		public @SYNTAX(typeof(Perl_Syntax)) PHP_EndIf endIf;

		public static class PHP_IfCondition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PHP.PHP_Program.PHP_StartTag startTag;
			public PHP_StartTag startTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Perl.Perl_Statement> statements;
			public TokenList<Perl_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Keyword IF = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("if");
			public Perl_Keyword IF = new Perl_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Perl.Perl_Expression condition;
			public Perl_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.PHP.PHP_Program.PHP_EndTag endTag;
			public PHP_EndTag endTag;
		}

		public static class PHP_IfElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PHP.PHP_Program.PHP_StartTag startTag;
			public PHP_StartTag startTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Keyword ELSE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("else");
			public Perl_Keyword ELSE = new Perl_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.PHP.PHP_Program.PHP_EndTag endTag;
			public PHP_EndTag endTag;
		}

		public static class PHP_EndIf extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PHP.PHP_Program.PHP_StartTag startTag;
			public PHP_StartTag startTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PHP.PHP_Program.PHP_EndTag endTag;
			public PHP_EndTag endTag;
		}
	}

}
