// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Python_RaiseStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-raise-statement") com.eagle.programmar.Python.Terminals.Python_Keyword RAISE = new com.eagle.programmar.Python.Terminals.Python_Keyword("raise");
		public @DOC("simple_stmts.html#the-raise-statement") Python_Keyword RAISE = new Python_Keyword("raise");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<Python_RaiseCondition, com.eagle.tokens.punctuation.PunctuationComma> raiseConditions;
		public @OPT SeparatedList<Python_RaiseCondition, PunctuationComma> raiseConditions;

		public static class Python_RaiseCondition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression condition;
			public Python_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_RaiseFrom raiseFrom;
			public @OPT Python_RaiseFrom raiseFrom;

			public static class Python_RaiseFrom extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword FROM = new com.eagle.programmar.Python.Terminals.Python_Keyword("from");
				public Python_Keyword FROM = new Python_Keyword("from");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression condition;
				public Python_Expression condition;
			}
		}
	}

}
