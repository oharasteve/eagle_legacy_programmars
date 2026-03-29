// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell
{
	using CMD_Syntax = com.eagle.programmar.CMD.CMD_Syntax;
	using CMD_FindStr_Statement = com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
	using CMD_Xcopy_Statement = com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
	using Powershell_BeginBlock = com.eagle.programmar.Powershell.Statements.Powershell_BeginBlock;
	using Powershell_BreakStatement = com.eagle.programmar.Powershell.Statements.Powershell_BreakStatement;
	using Powershell_Command = com.eagle.programmar.Powershell.Statements.Powershell_Command;
	using Powershell_ContinueStatement = com.eagle.programmar.Powershell.Statements.Powershell_ContinueStatement;
	using Powershell_DoStatement = com.eagle.programmar.Powershell.Statements.Powershell_DoStatement;
	using Powershell_ExitStatement = com.eagle.programmar.Powershell.Statements.Powershell_ExitStatement;
	using Powershell_ExpressionStatement = com.eagle.programmar.Powershell.Statements.Powershell_ExpressionStatement;
	using Powershell_ForEachStatement = com.eagle.programmar.Powershell.Statements.Powershell_ForEachStatement;
	using Powershell_ForStatement = com.eagle.programmar.Powershell.Statements.Powershell_ForStatement;
	using Powershell_Function = com.eagle.programmar.Powershell.Statements.Powershell_Function;
	using Powershell_IfStatement = com.eagle.programmar.Powershell.Statements.Powershell_IfStatement;
	using Powershell_ProcessBlock = com.eagle.programmar.Powershell.Statements.Powershell_ProcessBlock;
	using Powershell_ReturnStatement = com.eagle.programmar.Powershell.Statements.Powershell_ReturnStatement;
	using Powershell_StartJob = com.eagle.programmar.Powershell.Statements.Powershell_StartJob;
	using Powershell_SwitchStatement = com.eagle.programmar.Powershell.Statements.Powershell_SwitchStatement;
	using Powershell_ThrowStatement = com.eagle.programmar.Powershell.Statements.Powershell_ThrowStatement;
	using Powershell_TryStatement = com.eagle.programmar.Powershell.Statements.Powershell_TryStatement;
	using Powershell_WhereStatement = com.eagle.programmar.Powershell.Statements.Powershell_WhereStatement;
	using Powershell_WhileStatement = com.eagle.programmar.Powershell.Statements.Powershell_WhileStatement;
	using Powershell_WriteStatement = com.eagle.programmar.Powershell.Statements.Powershell_WriteStatement;
	using Powershell_Comment = com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Powershell_Element : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Powershell_Statement element;
		public Powershell_Statement element;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_EndOfLine eoln;
		public  OPT;

		public class Powershell_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_BeginBlock XXbeginBlock;
			public Powershell_BeginBlock XXbeginBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_BreakStatement XXbreakStatement;
			public Powershell_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Comment XXcomment;
			public Powershell_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ContinueStatement XXcontinueStatement;
			public Powershell_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Directive XXdirective;
			public Powershell_Directive XXdirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_DoStatement XXdoStatement;
			public Powershell_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ExitStatement XXexitStatement;
			public Powershell_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_IfStatement XXifStatement;
			public Powershell_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ForEachStatement XXforeachStatement;
			public Powershell_ForEachStatement XXforeachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ForStatement XXforStatement;
			public Powershell_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Function XXfunctionDefinition;
			public Powershell_Function XXfunctionDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ProcessBlock XXprocessBlock;
			public Powershell_ProcessBlock XXprocessBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ReturnStatement XXreturnStatement;
			public Powershell_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_StartJob XXstartJob;
			public Powershell_StartJob XXstartJob;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_SwitchStatement XXswitchStatement;
			public Powershell_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_ThrowStatement XXthrowStatement;
			public Powershell_ThrowStatement XXthrowStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_TryStatement XXtryStatement;
			public Powershell_TryStatement XXtryStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_WhereStatement XXwhereObjectStatement;
			public Powershell_WhereStatement XXwhereObjectStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_WriteStatement XXwriteStatement;
			public Powershell_WriteStatement XXwriteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_WhileStatement XXwhileStatement;
			public Powershell_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMD.CMD_Syntax.class) com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement XXxcopyStatement;
			public @SYNTAX(typeof(CMD_Syntax)) CMD_Xcopy_Statement XXxcopyStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMD.CMD_Syntax.class) com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement XXfindstrStatement;
			public @SYNTAX(typeof(CMD_Syntax)) CMD_FindStr_Statement XXfindstrStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Command XXcommand;
			public Powershell_Command XXcommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Powershell_ExpressionStatement XXexpressionStatement;
			public Powershell_ExpressionStatement XXexpressionStatement;
		}
	}

}
