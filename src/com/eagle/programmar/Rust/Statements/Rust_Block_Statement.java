// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 24, 2025

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_Block_Statement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) TokenList<Rust_Statement> statements;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Rust_Statement stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (Rust_Statement statement : statements._elements)
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

	public Rust_Statement generateBlock(ArrayList<Rust_Statement> stmts,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.statements = new TokenList<Rust_Statement>();
		this.statements.setPresent(true);
		for (Rust_Statement stmt : stmts)
		{
			this.statements.addToken(stmt);
		}
		return Rust_Generator.wrapStatement(this);
	}
	
	public static ArrayList<AbstractStatement> collectStatements(EagleTransformer transformer,
			EagleGenerator generator, Rust_Statement statement)
	{
		// Lots of extra work here to avoid duplicated braces; {{stmts}} is not nice.
		ArrayList<AbstractStatement> newStmts;
		if (statement.getWhich() instanceof Rust_Block_Statement)
		{
			Rust_Block_Statement block = (Rust_Block_Statement) statement.getWhich();
			newStmts = new ArrayList<AbstractStatement>();
			for (Rust_Statement blockStmt : block.statements._elements)
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
