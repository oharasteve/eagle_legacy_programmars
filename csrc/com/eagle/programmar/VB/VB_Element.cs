// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using VB_AssignmentStatement = com.eagle.programmar.VB.Statements.VB_AssignmentStatement;
	using VB_AttributeStatement = com.eagle.programmar.VB.Statements.VB_AttributeStatement;
	using VB_BeginStatement = com.eagle.programmar.VB.Statements.VB_BeginStatement;
	using VB_CallStatement = com.eagle.programmar.VB.Statements.VB_CallStatement;
	using VB_CloseStatement = com.eagle.programmar.VB.Statements.VB_CloseStatement;
	using VB_DataDeclaration = com.eagle.programmar.VB.Statements.VB_DataDeclaration;
	using VB_DoStatement = com.eagle.programmar.VB.Statements.VB_DoStatement;
	using VB_ExitStatement = com.eagle.programmar.VB.Statements.VB_ExitStatement;
	using VB_ExpressionStatement = com.eagle.programmar.VB.Statements.VB_ExpressionStatement;
	using VB_ForEachStatement = com.eagle.programmar.VB.Statements.VB_ForEachStatement;
	using VB_ForStatement = com.eagle.programmar.VB.Statements.VB_ForStatement;
	using VB_Function = com.eagle.programmar.VB.Statements.VB_Function;
	using VB_GotoStatement = com.eagle.programmar.VB.Statements.VB_GotoStatement;
	using VB_IfStatement = com.eagle.programmar.VB.Statements.VB_IfStatement;
	using VB_MessageBoxStatment = com.eagle.programmar.VB.Statements.VB_MessageBoxStatment;
	using VB_OnStatement = com.eagle.programmar.VB.Statements.VB_OnStatement;
	using VB_OpenStatement = com.eagle.programmar.VB.Statements.VB_OpenStatement;
	using VB_OptionStatement = com.eagle.programmar.VB.Statements.VB_OptionStatement;
	using VB_PrintStatement = com.eagle.programmar.VB.Statements.VB_PrintStatement;
	using VB_SetStatement = com.eagle.programmar.VB.Statements.VB_SetStatement;
	using VB_Subroutine = com.eagle.programmar.VB.Statements.VB_Subroutine;
	using VB_VersionStatement = com.eagle.programmar.VB.Statements.VB_VersionStatement;
	using VB_WhileStatement = com.eagle.programmar.VB.Statements.VB_WhileStatement;
	using VB_WscriptEcho = com.eagle.programmar.VB.Statements.VB_WscriptEcho;
	using VB_Label_Definition = com.eagle.programmar.VB.Symbols.VB_Label_Definition;
	using VB_Comment = com.eagle.programmar.VB.Terminals.VB_Comment;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;

	public class VB_Element : TokenSequence, AbstractStatement, EagleRunnableWithResult
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<VB_Statement,com.eagle.tokens.punctuation.PunctuationColon> baseStatements;
		public SeparatedList<VB_Statement, PunctuationColon> baseStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT VB_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;

		public class VB_Statement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_Comment XXcomment;
			public VB_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_AssignmentStatement XXassignmentStatement;
			public VB_AssignmentStatement XXassignmentStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_AttributeStatement XXattributeStatement;
			public VB_AttributeStatement XXattributeStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_BeginStatement XXbeginStatement;
			public VB_BeginStatement XXbeginStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_CallStatement XXcallStatement;
			public VB_CallStatement XXcallStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_CloseStatement XXcloseStatement;
			public VB_CloseStatement XXcloseStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_DataDeclaration XXdataDeclaration;
			public VB_DataDeclaration XXdataDeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_ExitStatement XXexitStatement;
			public VB_ExitStatement XXexitStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_DoStatement XXdoStatement;
			public VB_DoStatement XXdoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_ForStatement XXforStatement;
			public VB_ForStatement XXforStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_ForEachStatement XXforEachStatement;
			public VB_ForEachStatement XXforEachStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_Function XXfunctionDefinition;
			public VB_Function XXfunctionDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_GotoStatement XXgotoStatement;
			public VB_GotoStatement XXgotoStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_IfStatement XXifStatement;
			public VB_IfStatement XXifStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_MessageBoxStatment XXmessageBoxStatment;
			public VB_MessageBoxStatment XXmessageBoxStatment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_OnStatement XXonStatement;
			public VB_OnStatement XXonStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_OpenStatement XXopenStatement;
			public VB_OpenStatement XXopenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_OptionStatement XXoptionStatement;
			public VB_OptionStatement XXoptionStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_PrintStatement XXprintStatement;
			public VB_PrintStatement XXprintStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_SetStatement XXsetStatement;
			public VB_SetStatement XXsetStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_Subroutine XXsubDefinition;
			public VB_Subroutine XXsubDefinition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_VersionStatement XXversionStatement;
			public VB_VersionStatement XXversionStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_WhileStatement XXwhileStatement;
			public VB_WhileStatement XXwhileStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE VB_WscriptEcho XXwscriptEcho;
			public VB_WscriptEcho XXwscriptEcho;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST VB_ExpressionStatement XXexpressionStatement;
			public VB_ExpressionStatement XXexpressionStatement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class VB_Label extends com.eagle.tokens.TokenSequence
			public class VB_Label : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Symbols.VB_Label_Definition lbl;
				public VB_Label_Definition lbl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
			}
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = baseStatements.getPrimaryElement(i);
				result = interpreter.tryToInterpret(baseStatement);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			return result;
		}
	}

}
