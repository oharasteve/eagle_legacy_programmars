// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_While;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
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
		implements AbstractStatement, EagleRunnableWithResult,
				Eagle_Generate_While<CSharp_Statement, CSharp_Expression>
{
	public @S(10) @NEWLINE @DOC("statements/iteration-statements") CSharp_Keyword WHILE = new CSharp_Keyword("while");
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
			_metrics = new ForLoopMetrics(interpreter._metrics, WHILE);
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

	@Override
	public CSharp_Statement generateWhile1(CSharp_Expression cond,
			CSharp_Statement action, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();

		this.whileStatement = action;
		this.condition = cond;

		this.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(this);
	}
	
	@Override
	public CSharp_Statement generateWhile(CSharp_Expression cond,
			ArrayList<CSharp_Statement> actions, AbstractToken source)
	{
		CSharp_StatementBlock body = new CSharp_StatementBlock();
		body.statements = new TokenList<CSharp_StatementOrComment>();
		body.leftBrace = new PunctuationLeftBrace();
		body.rightBrace = new PunctuationRightBrace();

		CSharp_Statement csStatement = new CSharp_Statement();
		this.whileStatement = csStatement;
		csStatement.setWhich(body);

		for (CSharp_Statement stmt : actions)
		{
			CSharp_StatementOrComment wrapper = new CSharp_StatementOrComment();
			wrapper.setWhich(stmt);
			body.statements.addToken(wrapper);

			// If the parent block gets the 'while' as the parent, line numbers in the
			// side-by-side will pick up the 'while' instead of the first statement.
			if (csStatement.getTransformationSource() == null)
			{
				csStatement.setTransformationSource(stmt.getTransformationSource());
			}
		}

		CSharp_Statement action = CSharp_Generator.wrapStatement(body);
		return generateWhile1(cond, action, source);
	}
}
