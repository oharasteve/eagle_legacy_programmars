// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.TCL.Expressions.TCL_ExpressionStatement;
import com.eagle.programmar.TCL.Statements.TCL_BlockStatement;
import com.eagle.programmar.TCL.Statements.TCL_BreakStatement;
import com.eagle.programmar.TCL.Statements.TCL_ForStatement;
import com.eagle.programmar.TCL.Statements.TCL_FunctionCall;
import com.eagle.programmar.TCL.Statements.TCL_IfStatement;
import com.eagle.programmar.TCL.Statements.TCL_IncrStatement;
import com.eagle.programmar.TCL.Statements.TCL_NamespaceStatement;
import com.eagle.programmar.TCL.Statements.TCL_PutsStatement;
import com.eagle.programmar.TCL.Statements.TCL_ReturnStatement;
import com.eagle.programmar.TCL.Statements.TCL_SetStatement;
import com.eagle.programmar.TCL.Statements.TCL_VariableStatement;
import com.eagle.programmar.TCL.Terminals.TCL_Comment;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class TCL_Statement extends TokenSequence implements EagleRunnableWithResult
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
		public @CHOICE TCL_Comment XXcomment;
		public @CHOICE TCL_BlockStatement XXblockStatement;
		public @CHOICE TCL_Procedure XXprocedure;

		public @CHOICE TCL_BreakStatement XXbreakStatement;
		public @CHOICE TCL_ForStatement XXforStatement;
		public @CHOICE TCL_IfStatement XXifStatement;
		public @CHOICE TCL_IncrStatement XXincrStatement;
		public @CHOICE TCL_NamespaceStatement XXnamespaceStatement;
		public @CHOICE TCL_PutsStatement XXputsStatement;
		public @CHOICE TCL_ReturnStatement XXreturnStatement;
		public @CHOICE TCL_SetStatement XXsetStatement;
		public @CHOICE TCL_VariableStatement XXvariableStatement;

		public @LAST TCL_FunctionCall XXfunctionCall;
		public @LAST TCL_ExpressionStatement XXexpressionStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = 0; i < compoundStatement.statements.getPrimaryCount(); i++)
		{
			TCL_BaseStatement stmt = compoundStatement.statements.getPrimaryElement(i);
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
}
