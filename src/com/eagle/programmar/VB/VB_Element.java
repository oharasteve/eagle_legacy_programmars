// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.VB.Statements.VB_AssignmentStatement;
import com.eagle.programmar.VB.Statements.VB_AttributeStatement;
import com.eagle.programmar.VB.Statements.VB_BeginStatement;
import com.eagle.programmar.VB.Statements.VB_CallStatement;
import com.eagle.programmar.VB.Statements.VB_CloseStatement;
import com.eagle.programmar.VB.Statements.VB_DataDeclaration;
import com.eagle.programmar.VB.Statements.VB_DoStatement;
import com.eagle.programmar.VB.Statements.VB_ExitStatement;
import com.eagle.programmar.VB.Statements.VB_ExpressionStatement;
import com.eagle.programmar.VB.Statements.VB_ForEachStatement;
import com.eagle.programmar.VB.Statements.VB_ForStatement;
import com.eagle.programmar.VB.Statements.VB_Function;
import com.eagle.programmar.VB.Statements.VB_GotoStatement;
import com.eagle.programmar.VB.Statements.VB_IfStatement;
import com.eagle.programmar.VB.Statements.VB_MessageBoxStatment;
import com.eagle.programmar.VB.Statements.VB_OnStatement;
import com.eagle.programmar.VB.Statements.VB_OpenStatement;
import com.eagle.programmar.VB.Statements.VB_OptionStatement;
import com.eagle.programmar.VB.Statements.VB_PrintStatement;
import com.eagle.programmar.VB.Statements.VB_SetStatement;
import com.eagle.programmar.VB.Statements.VB_Subroutine;
import com.eagle.programmar.VB.Statements.VB_VersionStatement;
import com.eagle.programmar.VB.Statements.VB_WhileStatement;
import com.eagle.programmar.VB.Statements.VB_WscriptEcho;
import com.eagle.programmar.VB.Symbols.VB_Label_Definition;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class VB_Element extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) SeparatedList<VB_Statement,PunctuationColon> baseStatements;
	public @S(20) @OPT VB_Comment comment;
	public @S(30) VB_EndOfLine eoln;

	public static class VB_Statement extends TokenChooser
	{
		public @CHOICE VB_Comment XXcomment;

		public @CHOICE VB_AssignmentStatement XXassignmentStatement;
		public @CHOICE VB_AttributeStatement XXattributeStatement;
		public @CHOICE VB_BeginStatement XXbeginStatement;
		public @CHOICE VB_CallStatement XXcallStatement;
		public @CHOICE VB_CloseStatement XXcloseStatement;
		public @CHOICE VB_DataDeclaration XXdataDeclaration;
		public @CHOICE VB_ExitStatement XXexitStatement;
		public @CHOICE VB_DoStatement XXdoStatement;
		public @CHOICE VB_ForStatement XXforStatement;
		public @CHOICE VB_ForEachStatement XXforEachStatement;
		public @CHOICE VB_Function XXfunctionDefinition;
		public @CHOICE VB_GotoStatement XXgotoStatement;
		public @CHOICE VB_IfStatement XXifStatement;
		public @CHOICE VB_MessageBoxStatment XXmessageBoxStatment;
		public @CHOICE VB_OnStatement XXonStatement;
		public @CHOICE VB_OpenStatement XXopenStatement;
		public @CHOICE VB_OptionStatement XXoptionStatement;
		public @CHOICE VB_PrintStatement XXprintStatement;
		public @CHOICE VB_SetStatement XXsetStatement;
		public @CHOICE VB_Subroutine XXsubDefinition;
		public @CHOICE VB_VersionStatement XXversionStatement;
		public @CHOICE VB_WhileStatement XXwhileStatement;
		public @CHOICE VB_WscriptEcho XXwscriptEcho;

		public @LAST VB_ExpressionStatement XXexpressionStatement;

		public @CHOICE static class VB_Label extends TokenSequence
		{
			public @S(10) VB_Label_Definition lbl;
			public @S(20) PunctuationColon colon;
		}
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
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
