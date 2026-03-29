// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby
{
	using Ruby_Assignment = com.eagle.programmar.Ruby.Statements.Ruby_Assignment;
	using Ruby_BlockStatement = com.eagle.programmar.Ruby.Statements.Ruby_BlockStatement;
	using Ruby_BreakStatement = com.eagle.programmar.Ruby.Statements.Ruby_BreakStatement;
	using Ruby_Data = com.eagle.programmar.Ruby.Statements.Ruby_Data;
	using Ruby_ExpressionStatement = com.eagle.programmar.Ruby.Statements.Ruby_ExpressionStatement;
	using Ruby_ForStatement = com.eagle.programmar.Ruby.Statements.Ruby_ForStatement;
	using Ruby_Function = com.eagle.programmar.Ruby.Statements.Ruby_Function;
	using Ruby_IfStatement = com.eagle.programmar.Ruby.Statements.Ruby_IfStatement;
	using Ruby_PutsStatement = com.eagle.programmar.Ruby.Statements.Ruby_PutsStatement;
	using Ruby_ReturnStatement = com.eagle.programmar.Ruby.Statements.Ruby_ReturnStatement;
	using Ruby_WhileStatement = com.eagle.programmar.Ruby.Statements.Ruby_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Ruby_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_Assignment XXassignment;
		public Ruby_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_BlockStatement XXblockStatement;
		public Ruby_BlockStatement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_BreakStatement XXbreakStatement;
		public Ruby_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_CommentEoln XXcomment;
		public Ruby_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_Data XXdata;
		public Ruby_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_ForStatement XXforStatement;
		public Ruby_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_Function XXfunction;
		public Ruby_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_IfStatement XXifStatement;
		public Ruby_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_PutsStatement XXputsStatement;
		public Ruby_PutsStatement XXputsStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_ReturnStatement XXreturnStatement;
		public Ruby_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ruby_WhileStatement XXwhileStatement;
		public Ruby_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Ruby_ExpressionStatement XXexpressionStatement;
		public Ruby_ExpressionStatement XXexpressionStatement;
	}

}
