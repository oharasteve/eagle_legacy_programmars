// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Number = com.eagle.programmar.Perl.Terminals.Perl_Number;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Perl_Variable : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_Identifier_Reference XXvariable;
		public Perl_Identifier_Reference XXvariable;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_UserVariable extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable
		public class Perl_UserVariable : TokenSequence, EagleRunnable
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
			public Perl_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Perl_Subscript> subscript;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Perl_ClassField fld;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Perl_VarFunctionCall fnCall;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Perl_ExpressionList braces;
			public  OPT;

			public class Perl_ClassField : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation arrow = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("->");
				public Perl_Punctuation arrow = new Perl_Punctuation("->");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference fld;
				public Perl_Identifier_Reference fld;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Perl_Subscript> subscripts;
				public  OPT;
			}

			public class Perl_VarFunctionCall : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Comment comment;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<Perl_Expression, com.eagle.tokens.punctuation.PunctuationComma> parameters;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

			public override void interpret(EagleInterpreter interpreter)
			{
				EagleValue value = interpreter.findSymbol(id.getValue());
				if (subscript != null && subscript.size() > 0)
				{
					EagleArray array = (EagleArray) value;
					int sub = interpreter.getIntValue(subscript.first().expr);
					EagleValue val = array.getValue(sub);
					interpreter.pushEagleValue(val);
				}
				else
				{
					interpreter.pushEagleValue(value);
				}
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class Perl_DollarBraceVariable extends com.eagle.tokens.TokenSequence
		public class Perl_DollarBraceVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Perl_ExpressionList braces;
			public Perl_ExpressionList braces;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_DollarNumberVariable extends com.eagle.tokens.TokenSequence
		public class Perl_DollarNumberVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Number number;
			public Perl_Number number;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class Perl_DollarUnderscoreVariable extends com.eagle.tokens.TokenSequence
		public class Perl_DollarUnderscoreVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation underscore = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('_');
			public Perl_Punctuation underscore = new Perl_Punctuation('_');
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_DollarBarVariable extends com.eagle.tokens.TokenSequence
		public class Perl_DollarBarVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation bar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('|');
			public Perl_Punctuation bar = new Perl_Punctuation('|');
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_DollarSignalVariable extends com.eagle.tokens.TokenSequence
		public class Perl_DollarSignalVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Keyword SIG = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("SIG");
			public Perl_Keyword SIG = new Perl_Keyword("SIG");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Perl_Expression signal;
			public Perl_Expression signal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_SpecialVariable extends com.eagle.tokens.TokenSequence
		public class Perl_SpecialVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public Perl_Punctuation dollar = new Perl_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation caret = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('^');
			public Perl_Punctuation caret = new Perl_Punctuation('^');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice special = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("O");
			public Perl_KeywordChoice special = new Perl_KeywordChoice("O");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_PercentUTFVariable extends com.eagle.tokens.TokenSequence
		public class Perl_PercentUTFVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation percent = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('%');
			public Perl_Punctuation percent = new Perl_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Keyword UTF8 = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("utf8");
			public Perl_Keyword UTF8 = new Perl_Keyword("utf8");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Punctuation colonColon = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("::");
			public Perl_Punctuation colonColon = new Perl_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
			public Perl_Identifier_Reference id;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_PercentVariable extends com.eagle.tokens.TokenSequence
		public class Perl_PercentVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation percent = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('%');
			public Perl_Punctuation percent = new Perl_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
			public Perl_Identifier_Reference id;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_AmpersandVariable extends com.eagle.tokens.TokenSequence
		public class Perl_AmpersandVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation ampersand = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('&');
			public Perl_Punctuation ampersand = new Perl_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
			public Perl_Identifier_Reference id;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_AtVariable extends com.eagle.tokens.TokenSequence
		public class Perl_AtVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('@');
			public Perl_Punctuation at = new Perl_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
			public Perl_Identifier_Reference id;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_AtEachVariable extends com.eagle.tokens.TokenSequence
		public class Perl_AtEachVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('@');
			public Perl_Punctuation at = new Perl_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Keyword EACH = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("each");
			public Perl_Keyword EACH = new Perl_Keyword("each");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class Perl_AtUnderscoreVariable extends com.eagle.tokens.TokenSequence
		public class Perl_AtUnderscoreVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation at = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('@');
			public Perl_Punctuation at = new Perl_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Punctuation underscore = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('_');
			public Perl_Punctuation underscore = new Perl_Punctuation('_');
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_NamespaceVariable extends com.eagle.tokens.TokenSequence
		public class Perl_NamespaceVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id1;
			public Perl_Identifier_Reference id1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Perl_More_NamespaceVars> more;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_Punctuation colonColon = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("::");
			public Perl_Punctuation colonColon = new Perl_Punctuation("::");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id2;
			public Perl_Identifier_Reference id2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Perl_NamespaceArrow arrow;
			public  OPT;

			public class Perl_More_NamespaceVars : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
				public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference id;
				public Perl_Identifier_Reference id;
			}

			public class Perl_NamespaceArrow : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation arrow = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation("->");
				public Perl_Punctuation arrow = new Perl_Punctuation("->");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_Keyword NEW = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("new");
				public Perl_Keyword NEW = new Perl_Keyword("new");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Perl_Subscript> subscripts;
				public  OPT;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_ListVariable extends com.eagle.tokens.TokenSequence
		public class Perl_ListVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword LIST = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("list");
			public Perl_Keyword LIST = new Perl_Keyword("list");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<Perl_Expression, com.eagle.tokens.punctuation.PunctuationComma> args;
			public SeparatedList<Perl_Expression, PunctuationComma> args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static string repairName(string perlVariable)
		{
			return perlVariable.replaceAll("\\$", "");
		}
	}

}
