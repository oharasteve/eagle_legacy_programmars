// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Python_StatementBlock extends TokenChooser
		implements EagleTransformableStatementList
{
	public @CHOICE Python_Punctuation XXdots = new Python_Punctuation("...");

	public @CHOICE static class Python_SameLineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) SeparatedList<Python_Statement, PunctuationSemicolon> statements;
		public @S(20) @OPT Python_Comment comment;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				Python_Statement stmt = statements.getPrimaryElement(i);
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
				EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			int numStmts2 = statements.getPrimaryCount();
			for (int i = 0; i < numStmts2; i++)
			{
				Python_Statement stmt3 = statements.getPrimaryElement(i);
				ArrayList<AbstractStatement> newStmts3 = transformer.transformStatement(generator, stmt3);
				if (newStmts3 != null)
				{
					for (AbstractStatement newStmt3 : newStmts3)
					{
						result.add(newStmt3);
					}
				}
			}
			return result;
		}
	}

	public @CHOICE static class Python_MultilineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) @OPT Python_Comment comment;
		public @S(20) Python_EndOfLine eoln;
		public @S(30) TokenList<Python_ComplexStatement> statements;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (Python_ComplexStatement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
				EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			for (Python_ComplexStatement stmt : statements._elements)
			{
				AbstractToken which = stmt.statementOrComment.getWhich();
				if (which instanceof Python_SameLineStatement)
				{
					Python_SameLineStatement same = (Python_SameLineStatement) which;
					ArrayList<AbstractStatement> stmts = same.transformStatement(transformer, generator);
					for (AbstractStatement newStmt : stmts)
					{
						result.add(newStmt);
					}
				}
			}
			return result;
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = getWhich();
		if (which instanceof Python_SameLineStatement)
		{
			Python_SameLineStatement same = (Python_SameLineStatement) which;
			return same.transformStatement(transformer, generator);
		}
		if (which instanceof Python_MultilineStatement)
		{
			Python_MultilineStatement multi = (Python_MultilineStatement) which;
			return multi.transformStatement(transformer, generator);
		}

		throw new RuntimeException("Unable to handle " + which);
	}

	public Python_ComplexStatement addStatements(ArrayList<? extends AbstractStatement> statements)
	{
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_ComplexStatement>();
		for (AbstractStatement stmt : statements)
		{
			multi.statements.addToken((Python_ComplexStatement) stmt);
		}
		this.setWhich(multi);
		Python_ComplexStatement wrapper = new Python_ComplexStatement();
		wrapper.statementOrComment = new Python_StatementOrComment();
		wrapper.statementOrComment.setWhich(multi);
		return wrapper;
	}
}
