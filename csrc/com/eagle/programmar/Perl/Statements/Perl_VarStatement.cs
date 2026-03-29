// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

namespace com.eagle.programmar.Perl.Statements
{
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Program = com.eagle.programmar.Perl.Perl_Program;
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Perl_VarStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Perl_VarPrefix> prefix;
		public TokenList<Perl_VarPrefix> prefix;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Perl_Punctuation dollar = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('$');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition var;
		public Perl_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Perl_Variable_Init init;
		public  OPT;

		public class Perl_VarPrefix : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_KeywordChoice XXmodifier = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice(com.eagle.programmar.Perl.Perl_Program.MODIFIERS);
			public Perl_KeywordChoice XXmodifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
		}

		public class Perl_Variable_Init : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Expression initVal;
			public Perl_Expression initVal;
		}
	}

}
