// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Syntax;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, EagleScopeInterface, EagleTransformableStatement
{
	public @S(10) @INDENT PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<CSharp_StatementOrComment> statements;
	public @S(30) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (CSharp_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>(); 
		for (CSharp_StatementOrComment stmtOrComment : statements._elements)
		{
			if (stmtOrComment.getWhich() instanceof CSharp_Statement)
			{
				CSharp_Statement stmt1 = (CSharp_Statement) stmtOrComment.getWhich();
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
	
	public CSharp_Statement generateBlock(
			ArrayList<CSharp_Statement> stmts, AbstractToken source)
	{
		this.leftBrace = new PunctuationLeftBrace();
		this.rightBrace = new PunctuationRightBrace();
		this.statements = new TokenList<CSharp_StatementOrComment>();
		this.statements.setPresent(true);
		for (CSharp_Statement stmt : stmts)
		{
			CSharp_StatementOrComment stmtComm = new CSharp_StatementOrComment();
			stmtComm.setWhich(stmt);
			this.statements.addToken(stmtComm);
		}
		return CSharp_Generator.wrapStatement(this);
	}
	
	public static AbstractStatement collectStatements(EagleTransformer transformer,
			EagleGenerator generator, CSharp_Statement statement)
	{
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();

		if (statement.getWhich() instanceof CSharp_StatementBlock)
		{
			CSharp_StatementBlock block = (CSharp_StatementBlock) statement.getWhich();
			for (CSharp_StatementOrComment stmt1 : block.statements._elements)
			{
				if (stmt1.getWhich() instanceof CSharp_Statement)
				{
					CSharp_Statement stmt2 = (CSharp_Statement) stmt1.getWhich();
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
