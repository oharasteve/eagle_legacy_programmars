// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{
	using Python_ExpressionList = com.eagle.programmar.Python.Python_ExpressionList;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Python_DeleteStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("simple_stmts.html#the-del-statement") com.eagle.programmar.Python.Terminals.Python_Keyword DEL = new com.eagle.programmar.Python.Terminals.Python_Keyword("del");
		public @DOC("simple_stmts.html#the-del-statement") Python_Keyword DEL = new Python_Keyword("del");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_ExpressionList expressionList;
		public Python_ExpressionList expressionList;
	}

}
