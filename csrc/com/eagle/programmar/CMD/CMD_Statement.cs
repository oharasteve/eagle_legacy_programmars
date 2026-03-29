// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2024

namespace com.eagle.programmar.CMD
{
	using CMD_Awk_Statement = com.eagle.programmar.CMD.Statements.CMD_Awk_Statement;
	using CMD_BlockStatement = com.eagle.programmar.CMD.Statements.CMD_BlockStatement;
	using CMD_CD_Statement = com.eagle.programmar.CMD.Statements.CMD_CD_Statement;
	using CMD_Call_Statement = com.eagle.programmar.CMD.Statements.CMD_Call_Statement;
	using CMD_Copy_Statement = com.eagle.programmar.CMD.Statements.CMD_Copy_Statement;
	using CMD_Del_Statement = com.eagle.programmar.CMD.Statements.CMD_Del_Statement;
	using CMD_Dir_Statement = com.eagle.programmar.CMD.Statements.CMD_Dir_Statement;
	using CMD_Echo_Statement = com.eagle.programmar.CMD.Statements.CMD_Echo_Statement;
	using CMD_Exit_Statement = com.eagle.programmar.CMD.Statements.CMD_Exit_Statement;
	using CMD_FindStr_Statement = com.eagle.programmar.CMD.Statements.CMD_FindStr_Statement;
	using CMD_For_Statement = com.eagle.programmar.CMD.Statements.CMD_For_Statement;
	using CMD_GCC_Statement = com.eagle.programmar.CMD.Statements.CMD_GCC_Statement;
	using CMD_GenericStatement = com.eagle.programmar.CMD.Statements.CMD_GenericStatement;
	using CMD_Goto_Statement = com.eagle.programmar.CMD.Statements.CMD_Goto_Statement;
	using CMD_Grep_Statement = com.eagle.programmar.CMD.Statements.CMD_Grep_Statement;
	using CMD_If_Statement = com.eagle.programmar.CMD.Statements.CMD_If_Statement;
	using CMD_Mkdir_Statement = com.eagle.programmar.CMD.Statements.CMD_Mkdir_Statement;
	using CMD_NMake_Statement = com.eagle.programmar.CMD.Statements.CMD_NMake_Statement;
	using CMD_Perl_Statement = com.eagle.programmar.CMD.Statements.CMD_Perl_Statement;
	using CMD_Popd_Statement = com.eagle.programmar.CMD.Statements.CMD_Popd_Statement;
	using CMD_Pushd_Statement = com.eagle.programmar.CMD.Statements.CMD_Pushd_Statement;
	using CMD_Rem_Statement = com.eagle.programmar.CMD.Statements.CMD_Rem_Statement;
	using CMD_Rmdir_Statement = com.eagle.programmar.CMD.Statements.CMD_Rmdir_Statement;
	using CMD_SetLocal_Statement = com.eagle.programmar.CMD.Statements.CMD_SetLocal_Statement;
	using CMD_Set_Statement = com.eagle.programmar.CMD.Statements.CMD_Set_Statement;
	using CMD_Shift_Statement = com.eagle.programmar.CMD.Statements.CMD_Shift_Statement;
	using CMD_Xcopy_Statement = com.eagle.programmar.CMD.Statements.CMD_Xcopy_Statement;
	using CMD_Comment = com.eagle.programmar.CMD.Terminals.CMD_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class CMD_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Comment XXcomment;
		public CMD_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_BlockStatement XXblockStatement;
		public CMD_BlockStatement XXblockStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Awk_Statement XXawkCommand;
		public CMD_Awk_Statement XXawkCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Call_Statement XXcallCommand;
		public CMD_Call_Statement XXcallCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_CD_Statement XXcdCommand;
		public CMD_CD_Statement XXcdCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Copy_Statement XXcopyCommand;
		public CMD_Copy_Statement XXcopyCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Del_Statement XXdelCommand;
		public CMD_Del_Statement XXdelCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Dir_Statement XXdirCommand;
		public CMD_Dir_Statement XXdirCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Echo_Statement XXechoCommand;
		public CMD_Echo_Statement XXechoCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Exit_Statement XXexitCommand;
		public CMD_Exit_Statement XXexitCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_FindStr_Statement XXfindstrCommand;
		public CMD_FindStr_Statement XXfindstrCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_For_Statement XXforCommand;
		public CMD_For_Statement XXforCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_GCC_Statement XXgccCommand;
		public CMD_GCC_Statement XXgccCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Goto_Statement XXgotoCommand;
		public CMD_Goto_Statement XXgotoCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Grep_Statement XXgrepCommand;
		public CMD_Grep_Statement XXgrepCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_If_Statement XXifCommand;
		public CMD_If_Statement XXifCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Mkdir_Statement XXmkdirCommand;
		public CMD_Mkdir_Statement XXmkdirCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_NMake_Statement XXnmakeCommand;
		public CMD_NMake_Statement XXnmakeCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Perl_Statement XXperlCommand;
		public CMD_Perl_Statement XXperlCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Popd_Statement XXpopdCommand;
		public CMD_Popd_Statement XXpopdCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Pushd_Statement XXpushdCommand;
		public CMD_Pushd_Statement XXpushdCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Rem_Statement XXremCommand;
		public CMD_Rem_Statement XXremCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Rmdir_Statement XXrmdirCommand;
		public CMD_Rmdir_Statement XXrmdirCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Set_Statement XXsetCommand;
		public CMD_Set_Statement XXsetCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_SetLocal_Statement XXsetLocalCommand;
		public CMD_SetLocal_Statement XXsetLocalCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Shift_Statement XXshiftCommand;
		public CMD_Shift_Statement XXshiftCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Xcopy_Statement XXxcopyCommand;
		public CMD_Xcopy_Statement XXxcopyCommand;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMD_GenericStatement XXgenericStatement;
		public CMD_GenericStatement XXgenericStatement;
	}

}
