// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

namespace com.eagle.programmar.COBOL
{
	using COBOL_AcceptStatement = com.eagle.programmar.COBOL.Statements.COBOL_AcceptStatement;
	using COBOL_AddStatement = com.eagle.programmar.COBOL.Statements.COBOL_AddStatement;
	using COBOL_CallStatement = com.eagle.programmar.COBOL.Statements.COBOL_CallStatement;
	using COBOL_CancelStatement = com.eagle.programmar.COBOL.Statements.COBOL_CancelStatement;
	using COBOL_CloseStatement = com.eagle.programmar.COBOL.Statements.COBOL_CloseStatement;
	using COBOL_CommitStatement = com.eagle.programmar.COBOL.Statements.COBOL_CommitStatement;
	using COBOL_ComputeStatement = com.eagle.programmar.COBOL.Statements.COBOL_ComputeStatement;
	using COBOL_ContinueStatement = com.eagle.programmar.COBOL.Statements.COBOL_ContinueStatement;
	using COBOL_CopyStatement = com.eagle.programmar.COBOL.Statements.COBOL_CopyStatement;
	using COBOL_DeleteStatement = com.eagle.programmar.COBOL.Statements.COBOL_DeleteStatement;
	using COBOL_DisplayStatement = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement;
	using COBOL_DivideStatement = com.eagle.programmar.COBOL.Statements.COBOL_DivideStatement;
	using COBOL_EvaluateStatement = com.eagle.programmar.COBOL.Statements.COBOL_EvaluateStatement;
	using COBOL_ExitStatement = com.eagle.programmar.COBOL.Statements.COBOL_ExitStatement;
	using COBOL_GenerateStatement = com.eagle.programmar.COBOL.Statements.COBOL_GenerateStatement;
	using COBOL_GoBackStatement = com.eagle.programmar.COBOL.Statements.COBOL_GoBackStatement;
	using COBOL_GoStatement = com.eagle.programmar.COBOL.Statements.COBOL_GoStatement;
	using COBOL_IfStatement = com.eagle.programmar.COBOL.Statements.COBOL_IfStatement;
	using COBOL_InitializeStatement = com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
	using COBOL_InitiateStatement = com.eagle.programmar.COBOL.Statements.COBOL_InitiateStatement;
	using COBOL_InspectStatement = com.eagle.programmar.COBOL.Statements.COBOL_InspectStatement;
	using COBOL_InvokeStatement = com.eagle.programmar.COBOL.Statements.COBOL_InvokeStatement;
	using COBOL_MergeStatement = com.eagle.programmar.COBOL.Statements.COBOL_MergeStatement;
	using COBOL_MoveStatement = com.eagle.programmar.COBOL.Statements.COBOL_MoveStatement;
	using COBOL_MultiplyStatement = com.eagle.programmar.COBOL.Statements.COBOL_MultiplyStatement;
	using COBOL_NextStatement = com.eagle.programmar.COBOL.Statements.COBOL_NextStatement;
	using COBOL_OpenStatement = com.eagle.programmar.COBOL.Statements.COBOL_OpenStatement;
	using COBOL_PerformStatement = com.eagle.programmar.COBOL.Statements.COBOL_PerformStatement;
	using COBOL_ReadStatement = com.eagle.programmar.COBOL.Statements.COBOL_ReadStatement;
	using COBOL_ReleaseStatement = com.eagle.programmar.COBOL.Statements.COBOL_ReleaseStatement;
	using COBOL_ReturnStatement = com.eagle.programmar.COBOL.Statements.COBOL_ReturnStatement;
	using COBOL_RewriteStatement = com.eagle.programmar.COBOL.Statements.COBOL_RewriteStatement;
	using COBOL_SearchStatement = com.eagle.programmar.COBOL.Statements.COBOL_SearchStatement;
	using COBOL_SetStatement = com.eagle.programmar.COBOL.Statements.COBOL_SetStatement;
	using COBOL_SortStatement = com.eagle.programmar.COBOL.Statements.COBOL_SortStatement;
	using COBOL_StartStatement = com.eagle.programmar.COBOL.Statements.COBOL_StartStatement;
	using COBOL_StopStatement = com.eagle.programmar.COBOL.Statements.COBOL_StopStatement;
	using COBOL_StringStatement = com.eagle.programmar.COBOL.Statements.COBOL_StringStatement;
	using COBOL_SubtractStatement = com.eagle.programmar.COBOL.Statements.COBOL_SubtractStatement;
	using COBOL_TerminateStatement = com.eagle.programmar.COBOL.Statements.COBOL_TerminateStatement;
	using COBOL_UnlockStatement = com.eagle.programmar.COBOL.Statements.COBOL_UnlockStatement;
	using COBOL_UnstringStatement = com.eagle.programmar.COBOL.Statements.COBOL_UnstringStatement;
	using COBOL_UseStatement = com.eagle.programmar.COBOL.Statements.COBOL_UseStatement;
	using COBOL_WriteStatement = com.eagle.programmar.COBOL.Statements.COBOL_WriteStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class COBOL_Statement : TokenChooser, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_AcceptStatement XXacceptStatement;
		public COBOL_AcceptStatement XXacceptStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_AddStatement XXaddStatement;
		public COBOL_AddStatement XXaddStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_CallStatement XXcallStatement;
		public COBOL_CallStatement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_CancelStatement XXcancelStatement;
		public COBOL_CancelStatement XXcancelStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_CloseStatement XXcloseStatement;
		public COBOL_CloseStatement XXcloseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_CommitStatement XXcommitStatement;
		public COBOL_CommitStatement XXcommitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ComputeStatement XXcomputeStatement;
		public COBOL_ComputeStatement XXcomputeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ContinueStatement XXcontinueStatement;
		public COBOL_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_CopyStatement XXcopyStatement;
		public COBOL_CopyStatement XXcopyStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DeleteStatement XXdeleteStatement;
		public COBOL_DeleteStatement XXdeleteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DisplayStatement XXdisplayStatement;
		public COBOL_DisplayStatement XXdisplayStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DivideStatement XXdivideStatement;
		public COBOL_DivideStatement XXdivideStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ExitStatement XXexitStatement;
		public COBOL_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_EvaluateStatement XXevaluateStatement;
		public COBOL_EvaluateStatement XXevaluateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_GenerateStatement XXgenerateStatement;
		public COBOL_GenerateStatement XXgenerateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_GoStatement XXgoStatement;
		public COBOL_GoStatement XXgoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_GoBackStatement XXgoBackStatement;
		public COBOL_GoBackStatement XXgoBackStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_IfStatement XXifStatement;
		public COBOL_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_InitiateStatement XXinitiateStatement;
		public COBOL_InitiateStatement XXinitiateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_InitializeStatement XXinitializeStatement;
		public COBOL_InitializeStatement XXinitializeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_InvokeStatement XXinvokeStatement;
		public COBOL_InvokeStatement XXinvokeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_InspectStatement XXinspectStatement;
		public COBOL_InspectStatement XXinspectStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_MergeStatement XXmergeStatement;
		public COBOL_MergeStatement XXmergeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_MoveStatement XXmoveStatement;
		public COBOL_MoveStatement XXmoveStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_MultiplyStatement XXmultiplyStatement;
		public COBOL_MultiplyStatement XXmultiplyStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_NextStatement XXnextStatement;
		public COBOL_NextStatement XXnextStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_OpenStatement XXopenStatement;
		public COBOL_OpenStatement XXopenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PerformStatement XXperformStatement;
		public COBOL_PerformStatement XXperformStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ReadStatement XXreadStatement;
		public COBOL_ReadStatement XXreadStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ReleaseStatement XXreleaseStatement;
		public COBOL_ReleaseStatement XXreleaseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ReturnStatement XXreturnStatement;
		public COBOL_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_RewriteStatement XXrewruteStatement;
		public COBOL_RewriteStatement XXrewruteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SearchStatement XXsearchStatement;
		public COBOL_SearchStatement XXsearchStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SetStatement XXsetStatement;
		public COBOL_SetStatement XXsetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SortStatement XXsortStatement;
		public COBOL_SortStatement XXsortStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_StartStatement XXstartStatement;
		public COBOL_StartStatement XXstartStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_StopStatement XXstopStatement;
		public COBOL_StopStatement XXstopStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_StringStatement XXstringStatement;
		public COBOL_StringStatement XXstringStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SubtractStatement XXsubtractStatement;
		public COBOL_SubtractStatement XXsubtractStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_TerminateStatement XXterminateStatement;
		public COBOL_TerminateStatement XXterminateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_UnlockStatement XXunlockStatement;
		public COBOL_UnlockStatement XXunlockStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_UnstringStatement XXunstringStatement;
		public COBOL_UnstringStatement XXunstringStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_UseStatement XXuseStatement;
		public COBOL_UseStatement XXuseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_WriteStatement XXwriteStatement;
		public COBOL_WriteStatement XXwriteStatement;

		// public @LAST COBOL_UnparsedStatement XXunparsedStatement;
	}

}
