// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

namespace com.eagle.programmar.Go
{
	using Go_BlockStatement = com.eagle.programmar.Go.Statements.Go_BlockStatement;
	using Go_BreakStatement = com.eagle.programmar.Go.Statements.Go_BreakStatement;
	using Go_ConstBlock = com.eagle.programmar.Go.Statements.Go_ConstBlock;
	using Go_ExpressionStatement = com.eagle.programmar.Go.Statements.Go_ExpressionStatement;
	using Go_FmtPrintfStatement = com.eagle.programmar.Go.Statements.Go_FmtPrintfStatement;
	using Go_ForStatement = com.eagle.programmar.Go.Statements.Go_ForStatement;
	using Go_IfStatement = com.eagle.programmar.Go.Statements.Go_IfStatement;
	using Go_ReturnStatement = com.eagle.programmar.Go.Statements.Go_ReturnStatement;
	using Go_SwitchStatement = com.eagle.programmar.Go.Statements.Go_SwitchStatement;
	using Go_TypeDefinition = com.eagle.programmar.Go.Statements.Go_TypeDefinition;
	using Go_VarStatement = com.eagle.programmar.Go.Statements.Go_VarStatement;
	using Go_WhileStatement = com.eagle.programmar.Go.Statements.Go_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Go_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_BlockStatement XXblockStatement;
		public Go_BlockStatement XXblockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_BreakStatement XXbreakStatement;
		public Go_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_CommentEoln XXcomment;
		public Go_CommentEoln XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_ConstBlock XXconstBlock;
		public Go_ConstBlock XXconstBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_FmtPrintfStatement XXfmtPrintfStatement;
		public Go_FmtPrintfStatement XXfmtPrintfStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_ForStatement XXforStatement;
		public Go_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_IfStatement XXifStatement;
		public Go_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_ReturnStatement XXreturnStatement;
		public Go_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_SwitchStatement XXswitchStatement;
		public Go_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_TypeDefinition XXtypeDefinition;
		public Go_TypeDefinition XXtypeDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_VarStatement XXvarStatement;
		public Go_VarStatement XXvarStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Go_WhileStatement XXwhileStatement;
		public Go_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Go_ExpressionStatement XXexpressionStatement;
		public Go_ExpressionStatement XXexpressionStatement;
	}

}
