// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada
{
	using Ada_AppendStatement = com.eagle.programmar.Ada.Statements.Ada_AppendStatement;
	using Ada_Assignment = com.eagle.programmar.Ada.Statements.Ada_Assignment;
	using Ada_Data = com.eagle.programmar.Ada.Statements.Ada_Data;
	using Ada_ExitStatement = com.eagle.programmar.Ada.Statements.Ada_ExitStatement;
	using Ada_ExpressionStatement = com.eagle.programmar.Ada.Statements.Ada_ExpressionStatement;
	using Ada_ForStatement = com.eagle.programmar.Ada.Statements.Ada_ForStatement;
	using Ada_Function = com.eagle.programmar.Ada.Statements.Ada_Function;
	using Ada_IfStatement = com.eagle.programmar.Ada.Statements.Ada_IfStatement;
	using Ada_Procedure = com.eagle.programmar.Ada.Statements.Ada_Procedure;
	using Ada_PutIntegerStatement = com.eagle.programmar.Ada.Statements.Ada_PutIntegerStatement;
	using Ada_PutStatement = com.eagle.programmar.Ada.Statements.Ada_PutStatement;
	using Ada_ReturnStatement = com.eagle.programmar.Ada.Statements.Ada_ReturnStatement;
	using Ada_WhileStatement = com.eagle.programmar.Ada.Statements.Ada_WhileStatement;
	using Ada_WithUseStatement = com.eagle.programmar.Ada.Statements.Ada_WithUseStatement;
	using Ada_Comment = com.eagle.programmar.Ada.Terminals.Ada_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Ada_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_AppendStatement XXappendStatement;
		public Ada_AppendStatement XXappendStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Assignment XXassignment;
		public Ada_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_ExitStatement XXbreakStatement;
		public Ada_ExitStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Comment XXcomment;
		public Ada_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Data XXdata;
		public Ada_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_ForStatement XXforStatement;
		public Ada_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Function XXfunction;
		public Ada_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_IfStatement XXifStatement;
		public Ada_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_PutStatement XXputStatement;
		public Ada_PutStatement XXputStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_PutIntegerStatement XXputIntegerStatement;
		public Ada_PutIntegerStatement XXputIntegerStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Procedure XXprocedure;
		public Ada_Procedure XXprocedure;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_ReturnStatement XXreturnStatement;
		public Ada_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_WithUseStatement XXwithUseStatement;
		public Ada_WithUseStatement XXwithUseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_WhileStatement XXwhileStatement;
		public Ada_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Ada_ExpressionStatement XXexpressionStatement;
		public Ada_ExpressionStatement XXexpressionStatement;
	}

}
