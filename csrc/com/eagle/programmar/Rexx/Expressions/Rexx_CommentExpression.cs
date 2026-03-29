// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Expressions
{
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Comment = com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Rexx_CommentExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Terminals.Rexx_Comment comment;
		public Rexx_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Rexx_Expression expr;
		public Rexx_Expression expr;
	}

}
