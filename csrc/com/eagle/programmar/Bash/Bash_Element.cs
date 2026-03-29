// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

namespace com.eagle.programmar.Bash
{
	using Bash_Assignment = com.eagle.programmar.Bash.Commands.Bash_Assignment;
	using Bash_AwkCommand = com.eagle.programmar.Bash.Commands.Bash_AwkCommand;
	using Bash_BashProgram = com.eagle.programmar.Bash.Commands.Bash_BashProgram;
	using Bash_BreakStatement = com.eagle.programmar.Bash.Commands.Bash_BreakStatement;
	using Bash_CaseCommand = com.eagle.programmar.Bash.Commands.Bash_CaseCommand;
	using Bash_CatCommand = com.eagle.programmar.Bash.Commands.Bash_CatCommand;
	using Bash_ChmodCommand = com.eagle.programmar.Bash.Commands.Bash_ChmodCommand;
	using Bash_CpCommand = com.eagle.programmar.Bash.Commands.Bash_CpCommand;
	using Bash_CurlCommand = com.eagle.programmar.Bash.Commands.Bash_CurlCommand;
	using Bash_DiffCommand = com.eagle.programmar.Bash.Commands.Bash_DiffCommand;
	using Bash_DirnameCommand = com.eagle.programmar.Bash.Commands.Bash_DirnameCommand;
	using Bash_EchoCommand = com.eagle.programmar.Bash.Commands.Bash_EchoCommand;
	using Bash_EvaluateCommand = com.eagle.programmar.Bash.Commands.Bash_EvaluateCommand;
	using Bash_ExportCommand = com.eagle.programmar.Bash.Commands.Bash_ExportCommand;
	using Bash_ForStatement = com.eagle.programmar.Bash.Commands.Bash_ForStatement;
	using Bash_Function = com.eagle.programmar.Bash.Commands.Bash_Function;
	using Bash_FunctionCall = com.eagle.programmar.Bash.Commands.Bash_FunctionCall;
	using Bash_GccCommand = com.eagle.programmar.Bash.Commands.Bash_GccCommand;
	using Bash_GitCommand = com.eagle.programmar.Bash.Commands.Bash_GitCommand;
	using Bash_GrepCommand = com.eagle.programmar.Bash.Commands.Bash_GrepCommand;
	using Bash_IfStatement = com.eagle.programmar.Bash.Commands.Bash_IfStatement;
	using Bash_LispCommand = com.eagle.programmar.Bash.Commands.Bash_LispCommand;
	using Bash_LnCommand = com.eagle.programmar.Bash.Commands.Bash_LnCommand;
	using Bash_MkTempCommand = com.eagle.programmar.Bash.Commands.Bash_MkTempCommand;
	using Bash_MkdirCommand = com.eagle.programmar.Bash.Commands.Bash_MkdirCommand;
	using Bash_PerlProgram = com.eagle.programmar.Bash.Commands.Bash_PerlProgram;
	using Bash_PwdCommand = com.eagle.programmar.Bash.Commands.Bash_PwdCommand;
	using Bash_PythonProgram = com.eagle.programmar.Bash.Commands.Bash_PythonProgram;
	using Bash_ReadCommand = com.eagle.programmar.Bash.Commands.Bash_ReadCommand;
	using Bash_ReturnCommand = com.eagle.programmar.Bash.Commands.Bash_ReturnCommand;
	using Bash_RmCommand = com.eagle.programmar.Bash.Commands.Bash_RmCommand;
	using Bash_SedCommand = com.eagle.programmar.Bash.Commands.Bash_SedCommand;
	using Bash_SetCommand = com.eagle.programmar.Bash.Commands.Bash_SetCommand;
	using Bash_SortCommand = com.eagle.programmar.Bash.Commands.Bash_SortCommand;
	using Bash_TeeCommand = com.eagle.programmar.Bash.Commands.Bash_TeeCommand;
	using Bash_UniqCommand = com.eagle.programmar.Bash.Commands.Bash_UniqCommand;
	using Bash_WcCommand = com.eagle.programmar.Bash.Commands.Bash_WcCommand;
	using Bash_WhileStatement = com.eagle.programmar.Bash.Commands.Bash_WhileStatement;
	using Bash_ZipCommand = com.eagle.programmar.Bash.Commands.Bash_ZipCommand;
	using Bash_Comment = com.eagle.programmar.Bash.Terminals.Bash_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Bash_Element : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Bash_Statement element;
		public Bash_Statement element;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Bash_EndOfLine eoln;
		public  OPT;

		public class Bash_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_AwkCommand XXawkCommand;
			public Bash_AwkCommand XXawkCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_BreakStatement XXbreakStatement;
			public Bash_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_CaseCommand XXcaseCommand;
			public Bash_CaseCommand XXcaseCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_CatCommand XXcatCommand;
			public Bash_CatCommand XXcatCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ChmodCommand XXchmodCommand;
			public Bash_ChmodCommand XXchmodCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Comment XXcomment;
			public Bash_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_CpCommand XXcpCommand;
			public Bash_CpCommand XXcpCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_CurlCommand XXcurlCommand;
			public Bash_CurlCommand XXcurlCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_DiffCommand XXdiffCommand;
			public Bash_DiffCommand XXdiffCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_DirnameCommand XXdirnameCommand;
			public Bash_DirnameCommand XXdirnameCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_EchoCommand XXechoCommand;
			public Bash_EchoCommand XXechoCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_EvaluateCommand XXevaluateCommand;
			public Bash_EvaluateCommand XXevaluateCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ExportCommand XXexportCommand;
			public Bash_ExportCommand XXexportCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ForStatement XXforStatement;
			public Bash_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_GccCommand XXgccCommand;
			public Bash_GccCommand XXgccCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_GitCommand XXgitCommand;
			public Bash_GitCommand XXgitCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_GrepCommand XXgrepCommand;
			public Bash_GrepCommand XXgrepCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_IfStatement XXifStatement;
			public Bash_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_LispCommand XXlispCommand;
			public Bash_LispCommand XXlispCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_LnCommand XXlnCommand;
			public Bash_LnCommand XXlnCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_MkdirCommand XXmkdirCommand;
			public Bash_MkdirCommand XXmkdirCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_MkTempCommand XXmktempCommand;
			public Bash_MkTempCommand XXmktempCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_PwdCommand XXpwdCommand;
			public Bash_PwdCommand XXpwdCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ReadCommand XXreadCommand;
			public Bash_ReadCommand XXreadCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ReturnCommand XXreturnCommand;
			public Bash_ReturnCommand XXreturnCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_RmCommand XXrmCommand;
			public Bash_RmCommand XXrmCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_SedCommand XXsedCommand;
			public Bash_SedCommand XXsedCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_SetCommand XXsetCommand;
			public Bash_SetCommand XXsetCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_SortCommand XXsortCommand;
			public Bash_SortCommand XXsortCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_TeeCommand XXteeCommand;
			public Bash_TeeCommand XXteeCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_UniqCommand XXuniqCommand;
			public Bash_UniqCommand XXuniqCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_WcCommand XXwcCommand;
			public Bash_WcCommand XXwcCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_WhileStatement XXwhileStatement;
			public Bash_WhileStatement XXwhileStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_ZipCommand XXzipCommand;
			public Bash_ZipCommand XXzipCommand;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_BashProgram XXbashProgram;
			public Bash_BashProgram XXbashProgram;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_PerlProgram XXperlProgram;
			public Bash_PerlProgram XXperlProgram;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_PythonProgram XXpythonProgram;
			public Bash_PythonProgram XXpythonProgram;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_Assignment XXassignment;
			public Bash_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_FunctionCall XXfunctionCall;
			public Bash_FunctionCall XXfunctionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_Condition XXcondition;
			public Bash_Condition XXcondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_Function XXfunction;
			public Bash_Function XXfunction;
		}
	}

}
