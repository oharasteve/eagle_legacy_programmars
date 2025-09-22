// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class AWK_Action extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatementList
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT AWK_EndOfLine eoln1;
	public @S(30) @OPT TokenList<AWK_StatementOrComment> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT AWK_EndOfLine eoln2;

	public static class AWK_StatementOrComment extends TokenChooser
	{
		public @CHOICE AWK_Statements XXstatements;
		public @CHOICE AWK_Comment XXcomment;
		public @CHOICE AWK_Action XXaction;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (AWK_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (AWK_StatementOrComment stmt1 : statements._elements)
		{
			AbstractToken which3 = stmt1.getWhich();
			if (which3 instanceof AWK_Statements)
			{
				AWK_Statements stmts2 = (AWK_Statements) which3;
				int numStmt = stmts2.statements.getPrimaryCount();
				for (int i = 0; i < numStmt; i++)
				{
					AWK_Statement stmt3 = stmts2.statements.getPrimaryElement(i);
					Collection<AbstractStatement> newStmts = transformer.transformStatement(
							generator, stmt3.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							result.add(newStmt);
						}
					}
				}
			}
		}
		return result;
	}
}