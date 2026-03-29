// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia
{
	using Julia_CommentEoln = com.eagle.programmar.Julia.Julia_Program.Julia_CommentEoln;
	using Julia_Assignment = com.eagle.programmar.Julia.Statements.Julia_Assignment;
	using Julia_BlockStatement = com.eagle.programmar.Julia.Statements.Julia_BlockStatement;
	using Julia_BreakStatement = com.eagle.programmar.Julia.Statements.Julia_BreakStatement;
	using Julia_Data = com.eagle.programmar.Julia.Statements.Julia_Data;
	using Julia_ExpressionStatement = com.eagle.programmar.Julia.Statements.Julia_ExpressionStatement;
	using Julia_ForStatement = com.eagle.programmar.Julia.Statements.Julia_ForStatement;
	using Julia_Function = com.eagle.programmar.Julia.Statements.Julia_Function;
	using Julia_IfStatement = com.eagle.programmar.Julia.Statements.Julia_IfStatement;
	using Julia_PrintlnStatement = com.eagle.programmar.Julia.Statements.Julia_PrintlnStatement;
	using Julia_ReturnStatement = com.eagle.programmar.Julia.Statements.Julia_ReturnStatement;
	using Julia_WhileStatement = com.eagle.programmar.Julia.Statements.Julia_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Julia_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Assignment XXassignment;
		public Julia_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_BlockStatement XXblockStatement;
		public Julia_BlockStatement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_BreakStatement XXbreakStatement;
		public Julia_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_CommentEoln XXcomment;
		public Julia_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Data XXdata;
		public Julia_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_ForStatement XXforStatement;
		public Julia_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_Function XXfunction;
		public Julia_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_IfStatement XXifStatement;
		public Julia_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_PrintlnStatement XXputsStatement;
		public Julia_PrintlnStatement XXputsStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_ReturnStatement XXreturnStatement;
		public Julia_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Julia_WhileStatement XXwhileStatement;
		public Julia_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Julia_ExpressionStatement XXexpressionStatement;
		public Julia_ExpressionStatement XXexpressionStatement;
	}

}
