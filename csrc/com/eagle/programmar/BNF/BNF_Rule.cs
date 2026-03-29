// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF
{
	using BNF_Rule_Definition = com.eagle.programmar.BNF.Symbols.BNF_Rule_Definition;
	using BNF_Punctuation = com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class BNF_Rule : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.BNF.Symbols.BNF_Rule_Definition definition;
		public BNF_Rule_Definition definition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.BNF.Terminals.BNF_Punctuation coloncolonEquals = new com.eagle.programmar.BNF.Terminals.BNF_Punctuation("::=");
		public BNF_Punctuation coloncolonEquals = new BNF_Punctuation("::=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) BNF_Expression expression;
		public BNF_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
