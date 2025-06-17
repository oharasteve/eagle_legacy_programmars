// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
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
import com.eagle.programmar.TCL.Statements.TCL_WhileStatement;
import com.eagle.programmar.TCL.Terminals.TCL_Comment;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_Element extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) SeparatedList<TCL_Statement, PunctuationSemicolon> statements;
	public @S(20) @OPT TCL_Comment comment;
	public @S(30) @OPT TCL_EndOfLine eoln;

	public static class TCL_Statement extends TokenChooser
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
		public @CHOICE TCL_WhileStatement XXwhileStatement;

		public @LAST TCL_FunctionCall XXfunctionCall;
		public @LAST TCL_ExpressionStatement XXexpressionStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (int i = 0; i < statements.getPrimaryCount(); i++)
		{
			TCL_Statement stmt = statements.getPrimaryElement(i);
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<AbstractStatement> stmts = new ArrayList<AbstractStatement>();
		if (statements != null && statements.isPresent())
		{
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				TCL_Statement stmt = statements.getPrimaryElement(i);
				AbstractStatement newStmt = transformer.transformStatement1(generator, stmt);
				if (newStmt != null)
				{
					stmts.add(newStmt);
				}
			}
		}

		return generator.newBlockStatement(stmts, this);
	}
}
