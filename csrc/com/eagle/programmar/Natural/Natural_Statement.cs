// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural
{
	using Natural_AcceptStatement = com.eagle.programmar.Natural.Statements.Natural_AcceptStatement;
	using Natural_AtStatement = com.eagle.programmar.Natural.Statements.Natural_AtStatement;
	using Natural_CompressStatement = com.eagle.programmar.Natural.Statements.Natural_CompressStatement;
	using Natural_ComputeStatement = com.eagle.programmar.Natural.Statements.Natural_ComputeStatement;
	using Natural_DefineStatement = com.eagle.programmar.Natural.Statements.Natural_DefineStatement;
	using Natural_DisplayStatement = com.eagle.programmar.Natural.Statements.Natural_DisplayStatement;
	using Natural_DivideStatement = com.eagle.programmar.Natural.Statements.Natural_DivideStatement;
	using Natural_DoStatement = com.eagle.programmar.Natural.Statements.Natural_DoStatement;
	using Natural_EndStatement = com.eagle.programmar.Natural.Statements.Natural_EndStatement;
	using Natural_EnterStatement = com.eagle.programmar.Natural.Statements.Natural_EnterStatement;
	using Natural_EscapeStatement = com.eagle.programmar.Natural.Statements.Natural_EscapeStatement;
	using Natural_FindStatement = com.eagle.programmar.Natural.Statements.Natural_FindStatement;
	using Natural_FormatStatement = com.eagle.programmar.Natural.Statements.Natural_FormatStatement;
	using Natural_GetStatement = com.eagle.programmar.Natural.Statements.Natural_GetStatement;
	using Natural_HistogramStatement = com.eagle.programmar.Natural.Statements.Natural_HistogramStatement;
	using Natural_IfStatement = com.eagle.programmar.Natural.Statements.Natural_IfStatement;
	using Natural_InputStatement = com.eagle.programmar.Natural.Statements.Natural_InputStatement;
	using Natural_LimitStatement = com.eagle.programmar.Natural.Statements.Natural_LimitStatement;
	using Natural_MoveStatement = com.eagle.programmar.Natural.Statements.Natural_MoveStatement;
	using Natural_ReadStatement = com.eagle.programmar.Natural.Statements.Natural_ReadStatement;
	using Natural_ReinputStatement = com.eagle.programmar.Natural.Statements.Natural_ReinputStatement;
	using Natural_RejectStatement = com.eagle.programmar.Natural.Statements.Natural_RejectStatement;
	using Natural_ReleaseStatement = com.eagle.programmar.Natural.Statements.Natural_ReleaseStatement;
	using Natural_RepeatStatement = com.eagle.programmar.Natural.Statements.Natural_RepeatStatement;
	using Natural_SkipStatement = com.eagle.programmar.Natural.Statements.Natural_SkipStatement;
	using Natural_SortStatement = com.eagle.programmar.Natural.Statements.Natural_SortStatement;
	using Natural_StopStatement = com.eagle.programmar.Natural.Statements.Natural_StopStatement;
	using Natural_StoreStatement = com.eagle.programmar.Natural.Statements.Natural_StoreStatement;
	using Natural_SuspendStatement = com.eagle.programmar.Natural.Statements.Natural_SuspendStatement;
	using Natural_UpdateStatement = com.eagle.programmar.Natural.Statements.Natural_UpdateStatement;
	using Natural_WriteStatement = com.eagle.programmar.Natural.Statements.Natural_WriteStatement;
	using Natural_Comment = com.eagle.programmar.Natural.Terminals.Natural_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Natural_Statement : TokenChooser
	{
		// This one is a little "special".
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Comment XXcomment;
		public Natural_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_AtStatement XXatStatement;
		public Natural_AtStatement XXatStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_AcceptStatement XXacceptStatement;
		public Natural_AcceptStatement XXacceptStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_CompressStatement XXcompressStatement;
		public Natural_CompressStatement XXcompressStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_ComputeStatement XXcomputeStatement;
		public Natural_ComputeStatement XXcomputeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DefineStatement XXdefineStatement;
		public Natural_DefineStatement XXdefineStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DisplayStatement XXdisplayStatement;
		public Natural_DisplayStatement XXdisplayStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DivideStatement XXdivideStatement;
		public Natural_DivideStatement XXdivideStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DoStatement XXdoStatement;
		public Natural_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_EndStatement XXendStatement;
		public Natural_EndStatement XXendStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_EnterStatement XXenterStatement;
		public Natural_EnterStatement XXenterStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_EscapeStatement XXescapeStatement;
		public Natural_EscapeStatement XXescapeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_FindStatement XXfindStatement;
		public Natural_FindStatement XXfindStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_FormatStatement XXfrmatStatement;
		public Natural_FormatStatement XXfrmatStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_GetStatement XXgetStatement;
		public Natural_GetStatement XXgetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_HistogramStatement XXhistogramStatement;
		public Natural_HistogramStatement XXhistogramStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_IfStatement XXifStatement;
		public Natural_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_LimitStatement XXlimitStatement;
		public Natural_LimitStatement XXlimitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_InputStatement XXinputStatement;
		public Natural_InputStatement XXinputStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_MoveStatement XXmoveStatement;
		public Natural_MoveStatement XXmoveStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_ReadStatement XXreadStatement;
		public Natural_ReadStatement XXreadStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_ReinputStatement XXreinputStatement;
		public Natural_ReinputStatement XXreinputStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_RejectStatement XXrejectStatement;
		public Natural_RejectStatement XXrejectStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_ReleaseStatement XXreleaseStatement;
		public Natural_ReleaseStatement XXreleaseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_RepeatStatement XXrepeatStatement;
		public Natural_RepeatStatement XXrepeatStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_SkipStatement XXskipStatement;
		public Natural_SkipStatement XXskipStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_SortStatement XXsortStatement;
		public Natural_SortStatement XXsortStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_StopStatement XXstopStatement;
		public Natural_StopStatement XXstopStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_StoreStatement XXstoreStatement;
		public Natural_StoreStatement XXstoreStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_SuspendStatement XXsuspendStatement;
		public Natural_SuspendStatement XXsuspendStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_UpdateStatement XXupdateStatement;
		public Natural_UpdateStatement XXupdateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_WriteStatement XXwriteStatement;
		public Natural_WriteStatement XXwriteStatement;
	}
}
