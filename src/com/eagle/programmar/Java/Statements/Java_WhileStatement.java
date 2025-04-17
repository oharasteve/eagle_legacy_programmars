// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_WhileStatement extends TokenSequence implements
		AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.12") Java_Keyword WHILE = new Java_Keyword("while");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE Java_Expression condition;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	public @S(60) @OPT Java_Comment comment;
	public @S(70) Java_Statement whileStatement;

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
	
	public static Java_WhileStatement createWhile(ArrayList<Java_Statement> actions, Java_Expression whileExpression,
			AbstractToken source)
	{
		Java_WhileStatement whileStmt = new Java_WhileStatement();

		whileStmt.leftParen = new PunctuationLeftParen();
		whileStmt.rightParen = new PunctuationRightParen();

		Java_StatementBlock body = new Java_StatementBlock();
		body.statements = new TokenList<Java_StatementOrComment>();
		body.leftBrace = new PunctuationLeftBrace();
		body.rightBrace = new PunctuationRightBrace();

		Java_Statement javaStatement = new Java_Statement();
		whileStmt.whileStatement = javaStatement;
		javaStatement.setWhich(body);

		whileStmt.condition = whileExpression;

		for (Java_Statement action : actions)
		{
			Java_StatementOrComment wrapper = new Java_StatementOrComment();
			wrapper.setWhich(action);
			body.statements.addToken(wrapper);

			// If the parent block gets the 'while' as the parent, line numbers in the
			// side-by-side report will pick up the 'while', not the first statement.
			if (javaStatement.getTransformationSource() == null)
			{
				javaStatement.setTransformationSource(action.getTransformationSource());
			}
		}

		whileStmt.setTransformationSource(source);
		return whileStmt;
	}
}
