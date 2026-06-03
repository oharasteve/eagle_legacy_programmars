// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2026

package com.eagle.programmar.Haskell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_Statement;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_StatementOrComment;
import com.eagle.programmar.Haskell.Terminals.Haskell_Comment;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Haskell_StatementBlock extends TokenChooser
		implements EagleTransformableStatementList
{
	public @CHOICE static class Haskell_SameLineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) SeparatedList<Haskell_Statement, PunctuationSemicolon> statements;
		public @S(20) @OPT Haskell_Comment comment;
		public @S(30) @OPT Haskell_EndOfLine eoln;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (int i = 0; i < statements.getPrimaryCount(); i++)
			{
				Haskell_Statement stmt = statements.getPrimaryElement(i);
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
				Haskell_Statement stmt3 = statements.getPrimaryElement(i);
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

	public @CHOICE static class Haskell_MultilineStatement extends TokenSequence
			implements EagleRunnableWithResult, EagleTransformableStatementList
	{
		public @S(10) @OPT Haskell_Comment comment;
		public @S(20) Haskell_EndOfLine eoln;
		public @S(30) TokenList<Haskell_ComplexStatement> statements;

		@Override
		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (Haskell_ComplexStatement stmt : statements._elements)
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
			for (Haskell_ComplexStatement stmt : statements._elements)
			{
				AbstractToken which = stmt.statementOrComment.getWhich();
				if (which instanceof Haskell_SameLineStatement)
				{
					Haskell_SameLineStatement same = (Haskell_SameLineStatement) which;
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
		if (which instanceof Haskell_SameLineStatement)
		{
			Haskell_SameLineStatement same = (Haskell_SameLineStatement) which;
			return same.transformStatement(transformer, generator);
		}
		if (which instanceof Haskell_MultilineStatement)
		{
			Haskell_MultilineStatement multi = (Haskell_MultilineStatement) which;
			return multi.transformStatement(transformer, generator);
		}

		throw new RuntimeException("Unable to handle " + which);
	}

	public Haskell_ComplexStatement addStatements(ArrayList<? extends AbstractStatement> statements)
	{
		Haskell_MultilineStatement multi = new Haskell_MultilineStatement();
		multi.statements = new TokenList<Haskell_ComplexStatement>();
		for (AbstractStatement stmt : statements)
		{
			multi.statements.addToken((Haskell_ComplexStatement) stmt);
		}
		this.setWhich(multi);
		Haskell_ComplexStatement wrapper = new Haskell_ComplexStatement();
		wrapper.statementOrComment = new Haskell_StatementOrComment();
		wrapper.statementOrComment.setWhich(multi);
		return wrapper;
	}
}
