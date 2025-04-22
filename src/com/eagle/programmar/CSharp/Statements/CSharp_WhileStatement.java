// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_WhileStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @NEWLINE @DOC("statements.html#14.12") CSharp_Keyword WHILE = new CSharp_Keyword("while");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression condition;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @OPT CSharp_Comment comment;
	public @S(60) CSharp_Statement whileStatement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();
			result = interpreter.tryToInterpret(whileStatement);
			if (result == Eagle_Statement_Result.BREAK)
			{
				metric.broke();
				result = Eagle_Statement_Result.NORMAL;
				break;
			}
			else if (result == Eagle_Statement_Result.CONTINUE)
			{
				metric.continued();
				result = Eagle_Statement_Result.NORMAL;
			}
			else if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}
		}

		_metrics.competedLoop(metric);
		return result;
	}
	
	public static CSharp_WhileStatement createWhile(ArrayList<CSharp_Statement> actions,
			CSharp_Expression whileExpression, AbstractToken source)
	{
		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();

		whileStmt.leftParen = new PunctuationLeftParen();
		whileStmt.rightParen = new PunctuationRightParen();

		CSharp_StatementBlock body = new CSharp_StatementBlock();
		body.statements = new TokenList<CSharp_StatementOrComment>();
		body.leftBrace = new PunctuationLeftBrace();
		body.rightBrace = new PunctuationRightBrace();

		CSharp_Statement csStatement = new CSharp_Statement();
		whileStmt.whileStatement = csStatement;
		csStatement.setWhich(body);

		whileStmt.condition = whileExpression;

		for (CSharp_Statement action : actions)
		{
			CSharp_StatementOrComment wrapper = new CSharp_StatementOrComment();
			wrapper.setWhich(action);
			body.statements.addToken(wrapper);

			// If the parent block gets the 'while' as the parent, line numbers in the
			// side-by-side
			// report will pick up the 'while' instead of the first statement.
			if (csStatement.getTransformationSource() == null)
			{
				csStatement.setTransformationSource(action.getTransformationSource());
			}
		}

		whileStmt.setTransformationSource(source);
		return whileStmt;
	}
}
