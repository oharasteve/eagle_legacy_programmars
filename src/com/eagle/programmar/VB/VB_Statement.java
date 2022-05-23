// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Statements.VB_AssignmentStatement;
import com.eagle.programmar.VB.Statements.VB_AttributeStatement;
import com.eagle.programmar.VB.Statements.VB_BeginStatement;
import com.eagle.programmar.VB.Statements.VB_CallStatement;
import com.eagle.programmar.VB.Statements.VB_CloseStatement;
import com.eagle.programmar.VB.Statements.VB_DataDeclaration;
import com.eagle.programmar.VB.Statements.VB_ExitStatement;
import com.eagle.programmar.VB.Statements.VB_ForEachStatement;
import com.eagle.programmar.VB.Statements.VB_ForStatement;
import com.eagle.programmar.VB.Statements.VB_FunctionDeclaration;
import com.eagle.programmar.VB.Statements.VB_GotoStatement;
import com.eagle.programmar.VB.Statements.VB_IfStatement;
import com.eagle.programmar.VB.Statements.VB_MessageBoxStatment;
import com.eagle.programmar.VB.Statements.VB_OnStatement;
import com.eagle.programmar.VB.Statements.VB_OpenStatement;
import com.eagle.programmar.VB.Statements.VB_OptionStatement;
import com.eagle.programmar.VB.Statements.VB_PrintStatement;
import com.eagle.programmar.VB.Statements.VB_SetStatement;
import com.eagle.programmar.VB.Statements.VB_SubDeclaration;
import com.eagle.programmar.VB.Statements.VB_VersionStatement;
import com.eagle.programmar.VB.Statements.VB_WscriptEcho;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Symbols.VB_Label_Definition;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_Statement extends TokenSequence
{
	public @S(10) VB_BaseStatement baseStatement;
	public @S(20) @OPT VB_Comment comment;
	public @S(30) VB_EndOfLine eoln;
	
	public static class VB_BaseStatement extends TokenChooser
	{
		public @CHOICE VB_Comment comment;
		
		public @CHOICE VB_AssignmentStatement assignmentStatement;
		public @CHOICE VB_AttributeStatement attributeStatement;
		public @CHOICE VB_BeginStatement beginStatement;
		public @CHOICE VB_CallStatement callStatement;
		public @CHOICE VB_CloseStatement closeStatement;
		public @CHOICE VB_DataDeclaration dataDeclaration;
		public @CHOICE VB_ExitStatement exitStatement;
		public @CHOICE VB_ForStatement forStatement;
		public @CHOICE VB_ForEachStatement forEachStatement;
		public @CHOICE VB_FunctionDeclaration functionDefinition;
		public @CHOICE VB_GotoStatement gotoStatement;
		public @CHOICE VB_IfStatement ifStatement;
		public @CHOICE VB_MessageBoxStatment messageBoxStatment;
		public @CHOICE VB_OnStatement onStatement;
		public @CHOICE VB_OpenStatement openStatement;
		public @CHOICE VB_OptionStatement optionStatement;
		public @CHOICE VB_PrintStatement printStatement;
		public @CHOICE VB_SetStatement setStatement;
		public @CHOICE VB_SubDeclaration subDefinition;
		public @CHOICE VB_VersionStatement versionStatement;
		public @CHOICE VB_WscriptEcho wscriptEcho;
		
		public @LAST static class VB_InvokeFunction extends TokenSequence
		{
			public @S(10) SeparatedList<VB_Identifier_Reference,PunctuationPeriod> names;
			public @S(20) @OPT VB_InvokeFunctionArgs args;
			
			public static class VB_InvokeFunctionArgs extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) @OPT SeparatedList<VB_Expression,PunctuationComma> args;
				public @S(30) PunctuationRightParen rightParen;
			}
		}
		
		public @CHOICE static class VB_Label extends TokenSequence
		{
			public @S(10) VB_Label_Definition lbl;
			public @S(20) PunctuationColon colon;
		}
	}
}
