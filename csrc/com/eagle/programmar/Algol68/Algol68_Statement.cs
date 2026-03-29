// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68
{
	using Algol68_Assignment = com.eagle.programmar.Algol68.Statements.Algol68_Assignment;
	using Algol68_Data = com.eagle.programmar.Algol68.Statements.Algol68_Data;
	using Algol68_ExpressionStatement = com.eagle.programmar.Algol68.Statements.Algol68_ExpressionStatement;
	using Algol68_ForStatement = com.eagle.programmar.Algol68.Statements.Algol68_ForStatement;
	using Algol68_IfStatement = com.eagle.programmar.Algol68.Statements.Algol68_IfStatement;
	using Algol68_PrintStatement = com.eagle.programmar.Algol68.Statements.Algol68_PrintStatement;
	using Algol68_PrintfStatement = com.eagle.programmar.Algol68.Statements.Algol68_PrintfStatement;
	using Algol68_Procedure = com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
	using Algol68_WhileStatement = com.eagle.programmar.Algol68.Statements.Algol68_WhileStatement;
	using Algol68_Comment = com.eagle.programmar.Algol68.Terminals.Algol68_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Algol68_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Comment XXcomment;
		public Algol68_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Data XXdata;
		public Algol68_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_ForStatement XXforStatement;
		public Algol68_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_IfStatement XXifStatement;
		public Algol68_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_PrintStatement XXprintStatement;
		public Algol68_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_PrintfStatement XXprintfStatement;
		public Algol68_PrintfStatement XXprintfStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Procedure XXprocedure;
		public Algol68_Procedure XXprocedure;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_WhileStatement XXwhileStatement;
		public Algol68_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Algol68_Assignment XXassignment;
		public Algol68_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Algol68_ExpressionStatement XXexpressionStatement;
		public Algol68_ExpressionStatement XXexpressionStatement;
	}

}
