// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Syntax;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_BlockStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleScopeInterface, EagleTransformableStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Scala_EOLN eoln1;
	public @S(30) @OPT TokenList<Scala_Statement> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Scala_EOLN eoln2;

	private @SKIP EagleScope _scope = new EagleScope(this, Scala_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Scala_Statement stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (Scala_Statement statement : statements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, statement.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt : stmts)
				{
					result.add(stmt);
				}
			}
		}

		return generator.newBlockStatement(result, this);
	}

	public static ArrayList<AbstractStatement> collectStatements(EagleTransformer transformer,
			EagleGenerator generator, Scala_Statement statement)
	{
		// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
		ArrayList<AbstractStatement> newStmts;
		if (statement.getWhich() instanceof Scala_BlockStatement)
		{
			Scala_BlockStatement block = (Scala_BlockStatement) statement.getWhich();
			newStmts = new ArrayList<AbstractStatement>();
			for (Scala_Statement blockStmt : block.statements._elements)
			{
				ArrayList<AbstractStatement> oneStmt = transformer.transformStatement(generator, blockStmt.getWhich());
				if (oneStmt != null)
				{
					for (AbstractStatement newStmt : oneStmt)
					{
						newStmts.add(newStmt);
					}
				}
			}
		}
		else
		{
			// Rare case I think, def fn = stmt, with no braces
			newStmts = transformer.transformStatement(generator, statement.getWhich());
		}
		return newStmts;
	}
}
