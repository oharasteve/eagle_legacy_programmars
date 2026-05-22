// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Javascript.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_Label;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Javascript_StatementBlock extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @OPT Javascript_Label label;
	public @S(20) @INDENT PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Javascript_StatementOrComment> statements;
	public @S(40) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon1;
	public @S(50) @OUTDENT PunctuationRightBrace rightBrace;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Javascript_StatementOrComment stmt : statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		return result;
	}

	public static AbstractStatement collectStatements(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Javascript_Statement statement)
	{
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();

		if (statement.getWhich() instanceof Javascript_StatementBlock)
		{
			Javascript_StatementBlock block = (Javascript_StatementBlock) statement.getWhich();
			for (Javascript_StatementOrComment stmt1 : block.statements._elements)
			{
				if (stmt1.getWhich() instanceof Javascript_Statement)
				{
					Javascript_Statement stmt2 = (Javascript_Statement) stmt1.getWhich();
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

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		for (Javascript_StatementOrComment stmtOrComment : statements._elements)
		{
			if (stmtOrComment.getWhich() instanceof Javascript_Statement)
			{
				Javascript_Statement stmt1 = (Javascript_Statement) stmtOrComment.getWhich();
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
}
