// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript
{
	using Javascript_BreakStatement = com.eagle.programmar.Javascript.Statements.Javascript_BreakStatement;
	using Javascript_ContinueStatement = com.eagle.programmar.Javascript.Statements.Javascript_ContinueStatement;
	using Javascript_DoStatement = com.eagle.programmar.Javascript.Statements.Javascript_DoStatement;
	using Javascript_DocumentWriteln = com.eagle.programmar.Javascript.Statements.Javascript_DocumentWriteln;
	using Javascript_ExpressionStmt = com.eagle.programmar.Javascript.Statements.Javascript_ExpressionStmt;
	using Javascript_ForEachStatement = com.eagle.programmar.Javascript.Statements.Javascript_ForEachStatement;
	using Javascript_ForStatement = com.eagle.programmar.Javascript.Statements.Javascript_ForStatement;
	using Javascript_IfStatement = com.eagle.programmar.Javascript.Statements.Javascript_IfStatement;
	using Javascript_ImportStatement = com.eagle.programmar.Javascript.Statements.Javascript_ImportStatement;
	using Javascript_ReturnStatement = com.eagle.programmar.Javascript.Statements.Javascript_ReturnStatement;
	using Javascript_StatementBlock = com.eagle.programmar.Javascript.Statements.Javascript_StatementBlock;
	using Javascript_SwitchStatement = com.eagle.programmar.Javascript.Statements.Javascript_SwitchStatement;
	using Javascript_ThrowStatement = com.eagle.programmar.Javascript.Statements.Javascript_ThrowStatement;
	using Javascript_TryStatement = com.eagle.programmar.Javascript.Statements.Javascript_TryStatement;
	using Javascript_WhileStatement = com.eagle.programmar.Javascript.Statements.Javascript_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Javascript_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Javascript_StatementBlock XXblock;
		public Javascript_StatementBlock XXblock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Data XXdata;
		public Javascript_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon XXsemicolon;
		public @CURIOUS("Extra semicolon") PunctuationSemicolon XXsemicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_BreakStatement XXbreakStatement;
		public Javascript_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ContinueStatement XXcontinueStatement;
		public Javascript_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_DoStatement XXdoStatement;
		public Javascript_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_DocumentWriteln XXdocumentWriteln;
		public Javascript_DocumentWriteln XXdocumentWriteln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ForStatement XXforStatement;
		public Javascript_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ForEachStatement XXforEachStatement;
		public Javascript_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_Function XXfunction;
		public Javascript_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_IfStatement XXifStatement;
		public Javascript_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ImportStatement XXimportStatement;
		public Javascript_ImportStatement XXimportStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ReturnStatement XXreturnStatement;
		public Javascript_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_SwitchStatement XXswitchStatement;
		public Javascript_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_ThrowStatement XXthrowStatement;
		public Javascript_ThrowStatement XXthrowStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_TryStatement XXtryStatement;
		public Javascript_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Javascript_WhileStatement XXwhileStatement;
		public Javascript_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Javascript_ExpressionStmt XXexpressionStmt;
		public Javascript_ExpressionStmt XXexpressionStmt;
		// public @LAST Javascript_UnparsedStatement XXunparsedStatement;
	}

}
