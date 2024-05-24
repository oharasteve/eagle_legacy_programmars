// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Statements.TCL_BreakStatement;
import com.eagle.programmar.TCL.Statements.TCL_ForStatement;
import com.eagle.programmar.TCL.Statements.TCL_IfStatement;
import com.eagle.programmar.TCL.Statements.TCL_IncrStatement;
import com.eagle.programmar.TCL.Statements.TCL_NamespaceStatement;
import com.eagle.programmar.TCL.Statements.TCL_PutsStatement;
import com.eagle.programmar.TCL.Statements.TCL_ReturnStatement;
import com.eagle.programmar.TCL.Statements.TCL_SetStatement;
import com.eagle.programmar.TCL.Statements.TCL_VariableStatement;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Reference;
import com.eagle.programmar.TCL.Terminals.TCL_Comment;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class TCL_Statement extends TokenSequence
{
	public @S(10) TCL_Compound_Statement compoundStatement;
	public @S(20) @OPT TCL_Comment comment;
	public @S(30) @OPT TCL_EndOfLine eoln;

	public static class TCL_Compound_Statement extends TokenSequence
	{
		public @S(10) SeparatedList<TCL_BaseStatement, PunctuationSemicolon> statements;
	}

	public static class TCL_BaseStatement extends TokenChooser
	{
		public @CHOICE TCL_Comment comment;
		public @CHOICE TCL_BlockStatement blockStatement;
		public @CHOICE TCL_Procedure procedure;

		public @CHOICE TCL_BreakStatement breakStatement;
		public @CHOICE TCL_ForStatement forStatement;
		public @CHOICE TCL_IfStatement ifStatement;
		public @CHOICE TCL_IncrStatement incrStatement;
		public @CHOICE TCL_NamespaceStatement namespaceStatement;
		public @CHOICE TCL_PutsStatement putsStatement;
		public @CHOICE TCL_ReturnStatement returnStatement;
		public @CHOICE TCL_SetStatement setStatement;
		public @CHOICE TCL_VariableStatement variableStatement;

		public @LAST static class TCL_FunctionCall extends TokenSequence
		{
			public @S(10) TCL_Function_Reference function;
			public @S(20) TokenList<TCL_Expression> values;
		}
	}

	public static class TCL_BlockStatement extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TCL_EndOfLine endOfLine;
		public @S(30) @OPT TCL_GlobalVariables globals;
		public @S(40) @OPT TokenList<TCL_Statement> statements;
		public @S(50) PunctuationRightBrace rightBrace;

		public static class TCL_GlobalVariables extends TokenSequence
		{
			public @S(10) TCL_Keyword GLOBAL = new TCL_Keyword("global");
			public @S(20) TokenList<TCL_Variable> vars;
			public @S(30) TCL_EndOfLine endOfLine;
		}
	}
}
