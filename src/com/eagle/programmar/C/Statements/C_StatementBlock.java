// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.C_Syntax;
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

public class C_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
				EagleScopeInterface, EagleTransformableStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<C_StatementOrComment> statements;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (C_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		return result;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, C_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>(); 
		for (C_StatementOrComment statement : statements._elements)
		{
			if (statement.getWhich() instanceof C_Statement)
			{
				C_Statement stmt1 = (C_Statement) statement.getWhich();
				ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator, stmt1.getWhich());
				if (stmts != null)
				{
					for (AbstractStatement stmt2 : stmts)
					{
						result.add(stmt2);
					}
				}
			}
		}
		
		return generator.newBlockStatement(result, this);
	}
}
