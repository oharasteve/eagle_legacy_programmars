// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 13, 2022

namespace com.eagle.programmar.Fortran
{
	using Fortran_Assignment = com.eagle.programmar.Fortran.Statements.Fortran_Assignment;
	using Fortran_CallStatement = com.eagle.programmar.Fortran.Statements.Fortran_CallStatement;
	using Fortran_Common = com.eagle.programmar.Fortran.Statements.Fortran_Common;
	using Fortran_Data = com.eagle.programmar.Fortran.Statements.Fortran_Data;
	using Fortran_DoStatement = com.eagle.programmar.Fortran.Statements.Fortran_DoStatement;
	using Fortran_ExitStatement = com.eagle.programmar.Fortran.Statements.Fortran_ExitStatement;
	using Fortran_Function = com.eagle.programmar.Fortran.Statements.Fortran_Function;
	using Fortran_IfStatement = com.eagle.programmar.Fortran.Statements.Fortran_IfStatement;
	using Fortran_Implicit = com.eagle.programmar.Fortran.Statements.Fortran_Implicit;
	using Fortran_PrintStatement = com.eagle.programmar.Fortran.Statements.Fortran_PrintStatement;
	using Fortran_ProgramBlock = com.eagle.programmar.Fortran.Statements.Fortran_ProgramBlock;
	using Fortran_Subroutine = com.eagle.programmar.Fortran.Statements.Fortran_Subroutine;
	using Fortran_WhileStatement = com.eagle.programmar.Fortran.Statements.Fortran_WhileStatement;
	using Fortran_WriteStatement = com.eagle.programmar.Fortran.Statements.Fortran_WriteStatement;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Fortran_Statement : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Assignment XXassignment;
		public Fortran_Assignment XXassignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_CallStatement XXcallStatement;
		public Fortran_CallStatement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Common XXcommon;
		public Fortran_Common XXcommon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Data XXdata;
		public Fortran_Data XXdata;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_DoStatement XXdoStatement;
		public Fortran_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_ExitStatement XXexitStatement;
		public Fortran_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Function XXfunction;
		public Fortran_Function XXfunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_IfStatement XXifStatement;
		public Fortran_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Implicit XXimplicit;
		public Fortran_Implicit XXimplicit;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_PrintStatement XXprintStatement;
		public Fortran_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_ProgramBlock XXprogramBlock;
		public Fortran_ProgramBlock XXprogramBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_Subroutine XXsubroutine;
		public Fortran_Subroutine XXsubroutine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_WriteStatement XXwriteStatement;
		public Fortran_WriteStatement XXwriteStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_WhileStatement XXwhileStatement;
		public Fortran_WhileStatement XXwhileStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Fortran_CommentEOLN XXcommentEOLN;
		public Fortran_CommentEOLN XXcommentEOLN;
	}

}
