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
	using Python_ExpressionList = com.eagle.programmar.Python.Python_ExpressionList;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Python_YieldStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-yield-statement") com.eagle.programmar.Python.Terminals.Python_Keyword YIELD = new com.eagle.programmar.Python.Terminals.Python_Keyword("yield");
		public @DOC("simple_stmts.html#the-yield-statement") Python_Keyword YIELD = new Python_Keyword("yield");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_YieldWhat yieldWhat;
		public @OPT Python_YieldWhat yieldWhat;

		public static class Python_YieldWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_ExpressionList XXexpressionList;
			public Python_ExpressionList XXexpressionList;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_YieldFrom extends com.eagle.tokens.TokenSequence
			public static class Python_YieldFrom extends TokenSequence
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
