// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.PLI.Expressions
{
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Comment = com.eagle.programmar.PLI.Terminals.PLI_Comment;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class PLI_CommentExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Comment comment;
		public PLI_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.PLI_Expression expr;
		public PLI_Expression expr;
	}

}
