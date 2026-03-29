// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_PrintStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE Python_Keyword PRINT = new com.eagle.programmar.Python.Terminals.Python_Keyword("print");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Punctuation greaterGreater = new com.eagle.programmar.Python.Terminals.Python_Punctuation(">>");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Python.Python_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	}

}
