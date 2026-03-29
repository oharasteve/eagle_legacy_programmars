// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{
	using C_AutoLock = com.eagle.programmar.C.Statements.C_AutoLock;
	using C_BreakStatement = com.eagle.programmar.C.Statements.C_BreakStatement;
	using C_ContinueStatement = com.eagle.programmar.C.Statements.C_ContinueStatement;
	using C_DoStatement = com.eagle.programmar.C.Statements.C_DoStatement;
	using C_Embed_Assembler = com.eagle.programmar.C.Statements.C_Embed_Assembler;
	using C_ExpressionStatement = com.eagle.programmar.C.Statements.C_ExpressionStatement;
	using C_ForStatement = com.eagle.programmar.C.Statements.C_ForStatement;
	using C_FprintfStatement = com.eagle.programmar.C.Statements.C_FprintfStatement;
	using C_GotoStatement = com.eagle.programmar.C.Statements.C_GotoStatement;
	using C_IfStatement = com.eagle.programmar.C.Statements.C_IfStatement;
	using C_PrintfStatement = com.eagle.programmar.C.Statements.C_PrintfStatement;
	using C_ReturnStatement = com.eagle.programmar.C.Statements.C_ReturnStatement;
	using C_StatementBlock = com.eagle.programmar.C.Statements.C_StatementBlock;
	using C_SwitchStatement = com.eagle.programmar.C.Statements.C_SwitchStatement;
	using C_WhileStatement = com.eagle.programmar.C.Statements.C_WhileStatement;
	using C_TypeStruct = com.eagle.programmar.C.Types.C_TypeStruct;
	using C_TypeUnion = com.eagle.programmar.C.Types.C_TypeUnion;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class C_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Data XXjdata;
		public C_Data XXjdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Label XXlabel;
		public C_Label XXlabel;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
		public PunctuationSemicolon XXsemicolon; // Empty for loop body is ok

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment XXmacro;
		public @SYNTAX(typeof(CMacro_Syntax)) CMacro_StatementOrComment XXmacro;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_StatementBlock XXstatementBlock;
		public C_StatementBlock XXstatementBlock;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Embed_Assembler XXassembler;
		public C_Embed_Assembler XXassembler;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_AutoLock XXautoLock;
		public C_AutoLock XXautoLock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_BreakStatement XXbreakStatement;
		public C_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ContinueStatement XXcontinueStatement;
		public C_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_DoStatement XXdoStatement;
		public C_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ForStatement XXforStatement;
		public C_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FprintfStatement XXfprintfFunction;
		public C_FprintfStatement XXfprintfFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_GotoStatement XXgotoStatement;
		public C_GotoStatement XXgotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_IfStatement XXifStatement;
		public C_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_PrintfStatement XXprintfStatement;
		public C_PrintfStatement XXprintfStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ReturnStatement XXreturnStatement;
		public C_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_SwitchStatement XXswitchStatement;
		public C_SwitchStatement XXswitchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_WhileStatement XXwhileStatement;
		public C_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Declaration XXdeclaration;
		public C_Declaration XXdeclaration; // Like [[fallthrough]]
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_TypeStruct XXstructDefinition;
		public C_TypeStruct XXstructDefinition; // Like struct bob_t;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST C_TypeUnion XXunionDefinition;
		public C_TypeUnion XXunionDefinition;

		// Do this one last, just because it is so slow
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_ExpressionStatement XXassignmentStatement;
		public C_ExpressionStatement XXassignmentStatement;

		// public @LAST C_UnparsedStatement XXunparsedStatement;
	}

}
