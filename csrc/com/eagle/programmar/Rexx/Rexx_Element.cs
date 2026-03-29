// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Rexx_AssignmentStatement = com.eagle.programmar.Rexx.Statements.Rexx_AssignmentStatement;
	using Rexx_CallStatement = com.eagle.programmar.Rexx.Statements.Rexx_CallStatement;
	using Rexx_DoStatement = com.eagle.programmar.Rexx.Statements.Rexx_DoStatement;
	using Rexx_ExitStatement = com.eagle.programmar.Rexx.Statements.Rexx_ExitStatement;
	using Rexx_IfStatement = com.eagle.programmar.Rexx.Statements.Rexx_IfStatement;
	using Rexx_LeaveStatement = com.eagle.programmar.Rexx.Statements.Rexx_LeaveStatement;
	using Rexx_ReturnStatement = com.eagle.programmar.Rexx.Statements.Rexx_ReturnStatement;
	using Rexx_SayStatement = com.eagle.programmar.Rexx.Statements.Rexx_SayStatement;
	using Rexx_Comment = com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
	using Rexx_EndOfLine = com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Rexx_Element : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Rexx_Statement baseStatement;
		public Rexx_Statement baseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Rexx_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln;
		public Rexx_EndOfLine eoln;

		public class Rexx_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_Comment XXcomment;
			public Rexx_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_AssignmentStatement XXassignmentStatement;
			public Rexx_AssignmentStatement XXassignmentStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_CallStatement XXcallStatement;
			public Rexx_CallStatement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_DoStatement XXdoStatement;
			public Rexx_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_ExitStatement XXexitStatement;
			public Rexx_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_IfStatement XXifStatement;
			public Rexx_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_LeaveStatement XXleaveStatement;
			public Rexx_LeaveStatement XXleaveStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_ReturnStatement XXreturnStatement;
			public Rexx_ReturnStatement XXreturnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Rexx_SayStatement XXsayStatement;
			public Rexx_SayStatement XXsayStatement;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return interpreter.tryToInterpret(baseStatement);
		}
	}

}
