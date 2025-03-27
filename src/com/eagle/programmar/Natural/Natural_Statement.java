// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Statements.Natural_AcceptStatement;
import com.eagle.programmar.Natural.Statements.Natural_AtStatement;
import com.eagle.programmar.Natural.Statements.Natural_CompressStatement;
import com.eagle.programmar.Natural.Statements.Natural_ComputeStatement;
import com.eagle.programmar.Natural.Statements.Natural_DefineStatement;
import com.eagle.programmar.Natural.Statements.Natural_DisplayStatement;
import com.eagle.programmar.Natural.Statements.Natural_DivideStatement;
import com.eagle.programmar.Natural.Statements.Natural_DoStatement;
import com.eagle.programmar.Natural.Statements.Natural_EndStatement;
import com.eagle.programmar.Natural.Statements.Natural_EnterStatement;
import com.eagle.programmar.Natural.Statements.Natural_EscapeStatement;
import com.eagle.programmar.Natural.Statements.Natural_FindStatement;
import com.eagle.programmar.Natural.Statements.Natural_FormatStatement;
import com.eagle.programmar.Natural.Statements.Natural_GetStatement;
import com.eagle.programmar.Natural.Statements.Natural_HistogramStatement;
import com.eagle.programmar.Natural.Statements.Natural_IfStatement;
import com.eagle.programmar.Natural.Statements.Natural_InputStatement;
import com.eagle.programmar.Natural.Statements.Natural_LimitStatement;
import com.eagle.programmar.Natural.Statements.Natural_MoveStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReadStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReinputStatement;
import com.eagle.programmar.Natural.Statements.Natural_RejectStatement;
import com.eagle.programmar.Natural.Statements.Natural_ReleaseStatement;
import com.eagle.programmar.Natural.Statements.Natural_RepeatStatement;
import com.eagle.programmar.Natural.Statements.Natural_SkipStatement;
import com.eagle.programmar.Natural.Statements.Natural_SortStatement;
import com.eagle.programmar.Natural.Statements.Natural_StopStatement;
import com.eagle.programmar.Natural.Statements.Natural_StoreStatement;
import com.eagle.programmar.Natural.Statements.Natural_SuspendStatement;
import com.eagle.programmar.Natural.Statements.Natural_UpdateStatement;
import com.eagle.programmar.Natural.Statements.Natural_WriteStatement;
import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.tokens.TokenChooser;

public class Natural_Statement extends TokenChooser
{
	// This one is a little "special".
	public @CHOICE Natural_Comment XXcomment;

	public @CHOICE Natural_AtStatement XXatStatement;
	public @CHOICE Natural_AcceptStatement XXacceptStatement;
	public @CHOICE Natural_CompressStatement XXcompressStatement;
	public @CHOICE Natural_ComputeStatement XXcomputeStatement;
	public @CHOICE Natural_DefineStatement XXdefineStatement;
	public @CHOICE Natural_DisplayStatement XXdisplayStatement;
	public @CHOICE Natural_DivideStatement XXdivideStatement;
	public @CHOICE Natural_DoStatement XXdoStatement;
	public @CHOICE Natural_EndStatement XXendStatement;
	public @CHOICE Natural_EnterStatement XXenterStatement;
	public @CHOICE Natural_EscapeStatement XXescapeStatement;
	public @CHOICE Natural_FindStatement XXfindStatement;
	public @CHOICE Natural_FormatStatement XXfrmatStatement;
	public @CHOICE Natural_GetStatement XXgetStatement;
	public @CHOICE Natural_HistogramStatement XXhistogramStatement;
	public @CHOICE Natural_IfStatement XXifStatement;
	public @CHOICE Natural_LimitStatement XXlimitStatement;
	public @CHOICE Natural_InputStatement XXinputStatement;
	public @CHOICE Natural_MoveStatement XXmoveStatement;
	public @CHOICE Natural_ReadStatement XXreadStatement;
	public @CHOICE Natural_ReinputStatement XXreinputStatement;
	public @CHOICE Natural_RejectStatement XXrejectStatement;
	public @CHOICE Natural_ReleaseStatement XXreleaseStatement;
	public @CHOICE Natural_RepeatStatement XXrepeatStatement;
	public @CHOICE Natural_SkipStatement XXskipStatement;
	public @CHOICE Natural_SortStatement XXsortStatement;
	public @CHOICE Natural_StopStatement XXstopStatement;
	public @CHOICE Natural_StoreStatement XXstoreStatement;
	public @CHOICE Natural_SuspendStatement XXsuspendStatement;
	public @CHOICE Natural_UpdateStatement XXupdateStatement;
	public @CHOICE Natural_WriteStatement XXwriteStatement;
}