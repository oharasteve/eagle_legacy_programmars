// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Python_FromStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-import-statement") com.eagle.programmar.Python.Terminals.Python_Keyword FROM = new com.eagle.programmar.Python.Terminals.Python_Keyword("from");
		public @DOC("simple_stmts.html#the-import-statement") Python_Keyword FROM = new Python_Keyword("from");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_PunctuationChoice dots = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice(".", "..");
		public @OPT Python_PunctuationChoice dots = new Python_PunctuationChoice(".", "..");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Python.Python_Variable, com.eagle.tokens.punctuation.PunctuationPeriod> fromName;
		public @OPT SeparatedList<Python_Variable, PunctuationPeriod> fromName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Python_ImportStatement importStatement;
		public Python_ImportStatement importStatement;
	}

}
