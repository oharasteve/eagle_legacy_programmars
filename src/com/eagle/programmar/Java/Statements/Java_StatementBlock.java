// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2024

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, EagleScopeInterface, EagleTransformableStatement
{
	public @S(10) @OPT Java_Label label;
	public @S(20) @INDENT PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Java_StatementOrComment> statements;
	public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon1;
	public @S(50) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Java_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>(); 
		for (Java_StatementOrComment stmtOrComment : statements._elements)
		{
			if (stmtOrComment.getWhich() instanceof Java_Statement)
			{
				Java_Statement stmt1 = (Java_Statement) stmtOrComment.getWhich();
				ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
				if (stmts2 != null)
				{
					for (AbstractStatement stmt2 : stmts2)
					{
						result.add(stmt2);
					}
				}
			}
		}
		
		return generator.newBlockStatement(result, this);
	}
	
	public Java_Statement generateBlock(ArrayList<Java_Statement> stmts,
			AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.statements = new TokenList<Java_StatementOrComment>();
		this.statements.setPresent(true);
		for (Java_Statement stmt : stmts)
		{
			Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
			stmtOrComment.setWhich(stmt);
			this.statements.addToken(stmtOrComment);
		}
		return Java_Generator.wrapStatement(this);
	}

	public static AbstractStatement collectStatements(EagleTransformer transformer,
			EagleGenerator generator, Java_Statement statement)
	{
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();

		if (statement.getWhich() instanceof Java_StatementBlock)
		{
			Java_StatementBlock block = (Java_StatementBlock) statement.getWhich();
			for (Java_StatementOrComment stmt1 : block.statements._elements)
			{
				if (stmt1.getWhich() instanceof Java_Statement)
				{
					Java_Statement stmt2 = (Java_Statement) stmt1.getWhich();
					ArrayList<AbstractStatement> stmts3 = transformer.transformStatement(generator, stmt2.getWhich());
					for (AbstractStatement stmt3 : stmts3)
					{
						newStmts.add(stmt3);
					}
				}
			}
		}
		else
		{
			ArrayList<AbstractStatement> stmts4 = transformer.transformStatement(generator, statement.getWhich());
			for (AbstractStatement stmt4 : stmts4)
			{
				newStmts.add(stmt4);
			}
		}
		
		return generator.newBlockStatement(newStmts, statement);
	}
}
