// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

namespace com.eagle.programmar.PLI
{
	using PLI_AllocateStatement = com.eagle.programmar.PLI.Statements.PLI_AllocateStatement;
	using PLI_AnswerStatement = com.eagle.programmar.PLI.Statements.PLI_AnswerStatement;
	using PLI_AssignmentStatement = com.eagle.programmar.PLI.Statements.PLI_AssignmentStatement;
	using PLI_BeginStatement = com.eagle.programmar.PLI.Statements.PLI_BeginStatement;
	using PLI_CallStatement = com.eagle.programmar.PLI.Statements.PLI_CallStatement;
	using PLI_DoStatement = com.eagle.programmar.PLI.Statements.PLI_DoStatement;
	using PLI_FormatStatement = com.eagle.programmar.PLI.Statements.PLI_FormatStatement;
	using PLI_FreeStatement = com.eagle.programmar.PLI.Statements.PLI_FreeStatement;
	using PLI_GetStatement = com.eagle.programmar.PLI.Statements.PLI_GetStatement;
	using PLI_GoStatement = com.eagle.programmar.PLI.Statements.PLI_GoStatement;
	using PLI_IfStatement = com.eagle.programmar.PLI.Statements.PLI_IfStatement;
	using PLI_IterateStatement = com.eagle.programmar.PLI.Statements.PLI_IterateStatement;
	using PLI_LeaveStatement = com.eagle.programmar.PLI.Statements.PLI_LeaveStatement;
	using PLI_NoteStatement = com.eagle.programmar.PLI.Statements.PLI_NoteStatement;
	using PLI_OnStatement = com.eagle.programmar.PLI.Statements.PLI_OnStatement;
	using PLI_OpenStatement = com.eagle.programmar.PLI.Statements.PLI_OpenStatement;
	using PLI_PercentStatement = com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
	using PLI_PutStatement = com.eagle.programmar.PLI.Statements.PLI_PutStatement;
	using PLI_ReturnStatement = com.eagle.programmar.PLI.Statements.PLI_ReturnStatement;
	using PLI_RevertStatement = com.eagle.programmar.PLI.Statements.PLI_RevertStatement;
	using PLI_SelectStatement = com.eagle.programmar.PLI.Statements.PLI_SelectStatement;
	using PLI_SignalStatement = com.eagle.programmar.PLI.Statements.PLI_SignalStatement;
	using PLI_StopStatement = com.eagle.programmar.PLI.Statements.PLI_StopStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
		public PunctuationSemicolon XXsemicolon; // Empty statement

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST PLI_AssignmentStatement XXassignmentStatement;
		public PLI_AssignmentStatement XXassignmentStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_AllocateStatement XXallocateStatement;
		public PLI_AllocateStatement XXallocateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_AnswerStatement XXanswerStatement;
		public PLI_AnswerStatement XXanswerStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_BeginStatement XXbeginStatement;
		public PLI_BeginStatement XXbeginStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_CallStatement XXcallStatement;
		public PLI_CallStatement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_DoStatement XXdoStatement;
		public PLI_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_FormatStatement XXformatStatement;
		public PLI_FormatStatement XXformatStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_FreeStatement XXfreeStatement;
		public PLI_FreeStatement XXfreeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_GetStatement XXgetStatement;
		public PLI_GetStatement XXgetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_GoStatement XXgoStatement;
		public PLI_GoStatement XXgoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_IfStatement XXifStatement;
		public PLI_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_IterateStatement XXiterateStatement;
		public PLI_IterateStatement XXiterateStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_LeaveStatement XXleaveStatement;
		public PLI_LeaveStatement XXleaveStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_NoteStatement XXnoteStatement;
		public PLI_NoteStatement XXnoteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_OnStatement XXonStatement;
		public PLI_OnStatement XXonStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_OpenStatement XXopenStatement;
		public PLI_OpenStatement XXopenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_PercentStatement XXpercentStmt;
		public PLI_PercentStatement XXpercentStmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_PutStatement XXputStatement;
		public PLI_PutStatement XXputStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_ReturnStatement XXreturnStatement;
		public PLI_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_RevertStatement XXrevertStatement;
		public PLI_RevertStatement XXrevertStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_SignalStatement XXsignalStatement;
		public PLI_SignalStatement XXsignalStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_SelectStatement XXselectStatement;
		public PLI_SelectStatement XXselectStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_StopStatement XXstopStatement;
		public PLI_StopStatement XXstopStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Procedure XXinnerProcedure;
		public PLI_Procedure XXinnerProcedure;
	}

}
