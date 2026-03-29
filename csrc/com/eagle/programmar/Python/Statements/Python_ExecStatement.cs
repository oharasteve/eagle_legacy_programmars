// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 7, 2014

namespace com.eagle.programmar.Python.Statements
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_ExecStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword EXEC = new com.eagle.programmar.Python.Terminals.Python_Keyword("exec");
		public Python_Keyword EXEC = new Python_Keyword("exec");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Python.Python_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public SeparatedList<Python_Expression, PunctuationComma> exprs;
	}

}
