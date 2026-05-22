// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_BlockStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Go_EOLN eoln1;
	public @S(30) TokenList<Go_Statement> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Go_EOLN eoln2;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Go_Statement stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (Go_Statement statement : statements._elements)
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Go_Statement statement)
	{
		// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
		ArrayList<AbstractStatement> newStmts;
		if (statement.getWhich() instanceof Go_BlockStatement)
		{
			Go_BlockStatement block = (Go_BlockStatement) statement.getWhich();
			newStmts = new ArrayList<AbstractStatement>();
			for (Go_Statement blockStmt : block.statements._elements)
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
