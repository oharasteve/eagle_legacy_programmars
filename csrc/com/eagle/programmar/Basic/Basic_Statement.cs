// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Basic_AssignmentStatement = com.eagle.programmar.Basic.Statements.Basic_AssignmentStatement;
	using Basic_DataStatement = com.eagle.programmar.Basic.Statements.Basic_DataStatement;
	using Basic_DimStatement = com.eagle.programmar.Basic.Statements.Basic_DimStatement;
	using Basic_EndStatement = com.eagle.programmar.Basic.Statements.Basic_EndStatement;
	using Basic_ForStatement = com.eagle.programmar.Basic.Statements.Basic_ForStatement;
	using Basic_GoSubStatement = com.eagle.programmar.Basic.Statements.Basic_GoSubStatement;
	using Basic_GoToStatement = com.eagle.programmar.Basic.Statements.Basic_GoToStatement;
	using Basic_IfStatement = com.eagle.programmar.Basic.Statements.Basic_IfStatement;
	using Basic_InputStatement = com.eagle.programmar.Basic.Statements.Basic_InputStatement;
	using Basic_OnGoToStatement = com.eagle.programmar.Basic.Statements.Basic_OnGoToStatement;
	using Basic_PrintStatement = com.eagle.programmar.Basic.Statements.Basic_PrintStatement;
	using Basic_ReadStatement = com.eagle.programmar.Basic.Statements.Basic_ReadStatement;
	using Basic_RemStatement = com.eagle.programmar.Basic.Statements.Basic_RemStatement;
	using Basic_ReturnStatement = com.eagle.programmar.Basic.Statements.Basic_ReturnStatement;
	using Basic_EndOfLine = com.eagle.programmar.Basic.Terminals.Basic_EndOfLine;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationBackSlash = com.eagle.tokens.punctuation.PunctuationBackSlash;

	public class Basic_Statement : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Number label;
		public Basic_Number label;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Basic_BaseStatement, com.eagle.tokens.punctuation.PunctuationBackSlash> statements;
		public SeparatedList<Basic_BaseStatement, PunctuationBackSlash> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Terminals.Basic_EndOfLine eoln;
		public Basic_EndOfLine eoln;

		public class Basic_BaseStatement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_DataStatement XXdataStatement;
			public Basic_DataStatement XXdataStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_DimStatement XXdimStatement;
			public Basic_DimStatement XXdimStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_EndStatement XXendStatement;
			public Basic_EndStatement XXendStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_ForStatement XXforStatement;
			public Basic_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_GoSubStatement XXgosubStatement;
			public Basic_GoSubStatement XXgosubStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_GoToStatement XXgotoStatement;
			public Basic_GoToStatement XXgotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_IfStatement XXifStatement;
			public Basic_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_InputStatement XXinputStatement;
			public Basic_InputStatement XXinputStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_OnGoToStatement XXongotoStatement;
			public Basic_OnGoToStatement XXongotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_PrintStatement XXprintStatement;
			public Basic_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_ReadStatement XXreadStatement;
			public Basic_ReadStatement XXreadStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_RemStatement XXremStatement;
			public Basic_RemStatement XXremStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Basic_ReturnStatement XXreturnStatement;
			public Basic_ReturnStatement XXreturnStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Basic_AssignmentStatement XXassignmentStatement;
			public Basic_AssignmentStatement XXassignmentStatement;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				Basic_BaseStatement stmt = statements.getPrimaryElement(i);
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}
	}

}
