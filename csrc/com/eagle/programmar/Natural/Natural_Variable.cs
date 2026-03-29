// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 7, 2011

namespace com.eagle.programmar.Natural
{
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class Natural_Variable : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_BuiltinVariable extends com.eagle.tokens.TokenSequence
		public class Natural_BuiltinVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationStar star;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice builtins = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("PAGE-NUMBER");
			public Natural_KeywordChoice builtins = new Natural_KeywordChoice("PAGE-NUMBER");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_UserVariable extends com.eagle.tokens.TokenSequence
		public class Natural_UserVariable : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference id;
			public Natural_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Natural_Field> fields;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Subscript subscript;
			public  OPT;

			public class Natural_Field : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference id;
				public Natural_Identifier_Reference id;
			}
		}
	}

}
