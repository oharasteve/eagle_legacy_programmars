// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.CSharp
{
	using CSharp_PragmaDirective = com.eagle.programmar.CSharp.Directives.CSharp_PragmaDirective;
	using CSharp_BreakStatement = com.eagle.programmar.CSharp.Statements.CSharp_BreakStatement;
	using CSharp_CheckedStatement = com.eagle.programmar.CSharp.Statements.CSharp_CheckedStatement;
	using CSharp_ContinueStatement = com.eagle.programmar.CSharp.Statements.CSharp_ContinueStatement;
	using CSharp_DoWhileStatement = com.eagle.programmar.CSharp.Statements.CSharp_DoWhileStatement;
	using CSharp_ExitStatement = com.eagle.programmar.CSharp.Statements.CSharp_ExitStatement;
	using CSharp_ExpressionStatement = com.eagle.programmar.CSharp.Statements.CSharp_ExpressionStatement;
	using CSharp_ForEachStatement = com.eagle.programmar.CSharp.Statements.CSharp_ForEachStatement;
	using CSharp_ForStatement = com.eagle.programmar.CSharp.Statements.CSharp_ForStatement;
	using CSharp_GetProperty = com.eagle.programmar.CSharp.Statements.CSharp_GetProperty;
	using CSharp_GotoStatement = com.eagle.programmar.CSharp.Statements.CSharp_GotoStatement;
	using CSharp_IfStatement = com.eagle.programmar.CSharp.Statements.CSharp_IfStatement;
	using CSharp_LockStatement = com.eagle.programmar.CSharp.Statements.CSharp_LockStatement;
	using CSharp_ReturnStatement = com.eagle.programmar.CSharp.Statements.CSharp_ReturnStatement;
	using CSharp_SetProperty = com.eagle.programmar.CSharp.Statements.CSharp_SetProperty;
	using CSharp_StatementBlock = com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
	using CSharp_SwitchStatement = com.eagle.programmar.CSharp.Statements.CSharp_SwitchStatement;
	using CSharp_SynchronizedStatement = com.eagle.programmar.CSharp.Statements.CSharp_SynchronizedStatement;
	using CSharp_ThrowStatement = com.eagle.programmar.CSharp.Statements.CSharp_ThrowStatement;
	using CSharp_TryStatement = com.eagle.programmar.CSharp.Statements.CSharp_TryStatement;
	using CSharp_UsingStatement = com.eagle.programmar.CSharp.Statements.CSharp_UsingStatement;
	using CSharp_WhileStatement = com.eagle.programmar.CSharp.Statements.CSharp_WhileStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CSharp_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon XXsemicolon;
		public @CURIOUS("Extra semicolon") PunctuationSemicolon XXsemicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Data XXdata;
		public CSharp_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Class XXmyclass;
		public CSharp_Class XXmyclass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Enum XXenumeration;
		public CSharp_Enum XXenumeration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_PragmaDirective XXpragmaDirective;
		public @NEWLINE CSharp_PragmaDirective XXpragmaDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_StatementBlock XXstatementBlock;
		public CSharp_StatementBlock XXstatementBlock;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_BreakStatement XXbreakStatement;
		public CSharp_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ContinueStatement XXcontinueStatement;
		public CSharp_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_CheckedStatement XXcheckedStatement;
		public CSharp_CheckedStatement XXcheckedStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_DoWhileStatement XXdoStatement;
		public CSharp_DoWhileStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ExitStatement XXexitStatement;
		public CSharp_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ForStatement XXforStatement;
		public CSharp_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ForEachStatement XXforEachStatement;
		public CSharp_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_GetProperty XXgetProperty;
		public CSharp_GetProperty XXgetProperty;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_GotoStatement XXgotoStatement;
		public CSharp_GotoStatement XXgotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_IfStatement XXifStatement;
		public CSharp_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_LockStatement XXlockStatement;
		public CSharp_LockStatement XXlockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ReturnStatement XXreturnStatement;
		public CSharp_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_SetProperty XXsetProperty;
		public CSharp_SetProperty XXsetProperty;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_SuperStatement XXsuperStatement;
		public CSharp_SuperStatement XXsuperStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_SwitchStatement XXswitchStatement;
		public CSharp_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_SynchronizedStatement XXsynchronizedStatement;
		public CSharp_SynchronizedStatement XXsynchronizedStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ThrowStatement XXthrowStatement;
		public CSharp_ThrowStatement XXthrowStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_TryStatement XXtryStatement;
		public CSharp_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_UsingStatement XXusingStatement;
		public CSharp_UsingStatement XXusingStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_WhileStatement XXwhileStatement;
		public CSharp_WhileStatement XXwhileStatement;

		// Do this one after the others, just because it is so slow
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_ExpressionStatement XXassignmentStatement;
		public CSharp_ExpressionStatement XXassignmentStatement;

		// public @LAST CSharp_UnparsedStatement XXunparsed;
	}

}
