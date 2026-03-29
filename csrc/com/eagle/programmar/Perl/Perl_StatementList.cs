// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.Perl
{
	using Perl_BreakStatement = com.eagle.programmar.Perl.Statements.Perl_BreakStatement;
	using Perl_ChdirStatement = com.eagle.programmar.Perl.Statements.Perl_ChdirStatement;
	using Perl_ChmodStatement = com.eagle.programmar.Perl.Statements.Perl_ChmodStatement;
	using Perl_ChompCommand = com.eagle.programmar.Perl.Statements.Perl_ChompCommand;
	using Perl_CloseStatement = com.eagle.programmar.Perl.Statements.Perl_CloseStatement;
	using Perl_ContinueStatement = com.eagle.programmar.Perl.Statements.Perl_ContinueStatement;
	using Perl_DieStatement = com.eagle.programmar.Perl.Statements.Perl_DieStatement;
	using Perl_DoStatement = com.eagle.programmar.Perl.Statements.Perl_DoStatement;
	using Perl_EchoStatement = com.eagle.programmar.Perl.Statements.Perl_EchoStatement;
	using Perl_EvalStatement = com.eagle.programmar.Perl.Statements.Perl_EvalStatement;
	using Perl_ExitStatement = com.eagle.programmar.Perl.Statements.Perl_ExitStatement;
	using Perl_GlobalStatement = com.eagle.programmar.Perl.Statements.Perl_GlobalStatement;
	using Perl_IncludeStatement = com.eagle.programmar.Perl.Statements.Perl_IncludeStatement;
	using Perl_MyStatement = com.eagle.programmar.Perl.Statements.Perl_MyStatement;
	using Perl_NextStatement = com.eagle.programmar.Perl.Statements.Perl_NextStatement;
	using Perl_OpenStatement = com.eagle.programmar.Perl.Statements.Perl_OpenStatement;
	using Perl_PackageStatement = com.eagle.programmar.Perl.Statements.Perl_PackageStatement;
	using Perl_PrintStatement = com.eagle.programmar.Perl.Statements.Perl_PrintStatement;
	using Perl_RequireStatement = com.eagle.programmar.Perl.Statements.Perl_RequireStatement;
	using Perl_ReturnStatement = com.eagle.programmar.Perl.Statements.Perl_ReturnStatement;
	using Perl_ShiftStatement = com.eagle.programmar.Perl.Statements.Perl_ShiftStatement;
	using Perl_SleepStatement = com.eagle.programmar.Perl.Statements.Perl_SleepStatement;
	using Perl_ThrowStatement = com.eagle.programmar.Perl.Statements.Perl_ThrowStatement;
	using Perl_UnlinkStatement = com.eagle.programmar.Perl.Statements.Perl_UnlinkStatement;
	using Perl_UseStatement = com.eagle.programmar.Perl.Statements.Perl_UseStatement;
	using Perl_VarStatement = com.eagle.programmar.Perl.Statements.Perl_VarStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Perl_StatementList : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_BreakStatement XXbreakStatement;
		public Perl_BreakStatement XXbreakStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ChdirStatement XXchdirStatement;
		public Perl_ChdirStatement XXchdirStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ChmodStatement XXchmodStatement;
		public Perl_ChmodStatement XXchmodStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ChompCommand XXchompCommand;
		public Perl_ChompCommand XXchompCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_CloseStatement XXcloseStatement;
		public Perl_CloseStatement XXcloseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ContinueStatement XXcontinueStatement;
		public Perl_ContinueStatement XXcontinueStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_DieStatement XXdieStatement;
		public Perl_DieStatement XXdieStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_DoStatement XXdoStatement;
		public Perl_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_EchoStatement XXechoStatement;
		public Perl_EchoStatement XXechoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_EvalStatement XXevalStatement;
		public Perl_EvalStatement XXevalStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ExitStatement XXexitStatement;
		public Perl_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_GlobalStatement XXglobalStatement;
		public Perl_GlobalStatement XXglobalStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_IncludeStatement XXincludeStatement;
		public Perl_IncludeStatement XXincludeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_MyStatement XXmyStatement;
		public Perl_MyStatement XXmyStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_NextStatement XXnextStatement;
		public Perl_NextStatement XXnextStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_OpenStatement XXopenStatement;
		public Perl_OpenStatement XXopenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_PackageStatement XXpackageStatement;
		public Perl_PackageStatement XXpackageStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_PrintStatement XXprintStatement;
		public Perl_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_RequireStatement XXrequireStatement;
		public Perl_RequireStatement XXrequireStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ReturnStatement XXreturnStatement;
		public Perl_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ShiftStatement XXshiftStatement;
		public Perl_ShiftStatement XXshiftStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_SleepStatement XXsleepStatement;
		public Perl_SleepStatement XXsleepStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_ThrowStatement XXthrowStatement;
		public Perl_ThrowStatement XXthrowStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_UnlinkStatement XXunlinkStatement;
		public Perl_UnlinkStatement XXunlinkStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_UseStatement XXuseStatement;
		public Perl_UseStatement XXuseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_VarStatement XXvarStatement;
		public Perl_VarStatement XXvarStatement;
	}
}
