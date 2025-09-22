// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2015

package com.eagle.programmar.AWK.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class AWK_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("#For-Statement") AWK_Keyword FOR = new AWK_Keyword("for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Variable loopVar;
	public @S(40) AWK_Punctuation equals = new AWK_Punctuation("=");
	public @S(50) AWK_Expression initialize;
	public @S(60) PunctuationSemicolon semicolon1;
	public @S(70) AWK_Expression test;
	public @S(80) PunctuationSemicolon semicolon2;
	public @S(90) AWK_Expression increment;
	public @S(100) PunctuationRightParen rightParen;
	public @S(110) @OPT AWK_EndOfLine eoln;
	public @S(120) AWK_ForBlock block;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class AWK_ForBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement XXstmt;
		public @CHOICE AWK_Action XXactions;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue init = interpreter.getEagleValue(initialize);
		interpreter.setSymbol(this, loopVar.id.getValue(), init);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(test);
			if (! keepGoing)
			{
				break;
			}

			metric.iterate();
			AWK_Action stmts = (AWK_Action) block.getWhich();

			for (AWK_StatementOrComment stmt : stmts.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

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

			interpreter.tryToInterpret(increment);
		}

		_metrics.competedLoop(metric);
		return result;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String varName = loopVar.id.getValue();

		AbstractExpression fromExpr = transformer.transformExpression(generator, initialize);
		AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);
		
		AbstractExpression termExpr = transformer.transformExpression(generator, test);
		AbstractExpression delta = transformer.transformExpression(generator, increment);
		ArrayList<AbstractStatement> newActions = transformer.transformStatement(generator, block);
		return generator.newForLoopStatement(asgExpr, termExpr, delta, newActions, this);
	}
}
