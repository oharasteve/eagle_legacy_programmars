// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.PLI_Statement;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class PLI_DoStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				EagleTransformableStatement
{
	public @S(10) @OPT PLI_Label label1;
	public @S(20) @DOC("7.15") PLI_Keyword DO = new PLI_Keyword("DO");
	public @S(30) @OPT PLI_DoLoop doLoop;
	public @S(40) @OPT PLI_DoUntil doUntil;
	public @S(50) @OPT PLI_DoWhile doWhile;
	public @S(60) @OPT PLI_Keyword FOREVER = new PLI_Keyword("FOREVER");
	public @S(70) PunctuationSemicolon semicolon1;
	public @S(80) @OPT TokenList<PLI_StatementOrComment> statements;
	public @S(90) PLI_Keyword END = new PLI_Keyword("END");
	public @S(100) @OPT PLI_Identifier_Reference label2;
	public @S(110) PunctuationSemicolon semicolon2;

	public static class PLI_DoLoop extends TokenSequence
	{
		public @S(10) PLI_Identifier_Reference id;
		public @S(20) PunctuationEquals equals;
		public @S(30) PLI_Expression fromExpr;
		public @S(40) PLI_Keyword TO = new PLI_Keyword("TO");
		public @S(50) PLI_Expression toExpr;
		public @S(60) @OPT PLI_DoBy by;

		public static class PLI_DoBy extends TokenSequence
		{
			public @S(10) PLI_Keyword BY = new PLI_Keyword("BY");
			public @S(20) PLI_Expression byExpr;
		}
	}

	public static class PLI_DoUntil extends TokenSequence
	{
		public @S(10) PLI_Keyword UNTIL = new PLI_Keyword("UNTIL");
		public @S(20) PLI_Expression condition;
	}

	public static class PLI_DoWhile extends TokenSequence
	{
		public @S(10) PLI_Keyword WHILE = new PLI_Keyword("WHILE");
		public @S(20) PLI_Expression condition;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		boolean simpleDo = true;
		PLI_Expression whileCond = null;
		PLI_Expression untilCond = null;
		PLI_Identifier_Reference loopVar = null;
		int start = 0;
		int stop = 0;
		int step = 1;
		boolean hasLoop = false;
		
		if (doLoop != null && doLoop.isPresent())
		{
			hasLoop = true;
			loopVar = doLoop.id;
			start = interpreter.getIntValue(doLoop.fromExpr);
			stop = interpreter.getIntValue(doLoop.toExpr);
			if (doLoop.by != null && doLoop.by.isPresent())
			{
				step = interpreter.getIntValue(doLoop.by.byExpr);
			}
			simpleDo = false;
		}
		if (doWhile != null && doWhile.isPresent())
		{
			whileCond = doWhile.condition;
			simpleDo = false;
		}
		if (doUntil != null && doUntil.isPresent())
		{
			untilCond = doUntil.condition;
			simpleDo = false;
		}
		if (FOREVER != null && FOREVER.isPresent())
		{
			simpleDo = false;
		}
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		
		if (simpleDo)
		{
			// No iteration, no metrics, no logic, just a groups of statements
			for (PLI_StatementOrComment stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}
		
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, DO);
		}
		ForLoopMetric metric = new ForLoopMetric();
		
		int i = start;
		while (true)
		{
			if (whileCond != null)
			{
				boolean cond = interpreter.getBoolValue(whileCond);
				if (!cond) break;
			}
			if (hasLoop)
			{
				if (step > 0 && i > stop) break;
				if (step < 0 && i < stop) break;
				interpreter.setSymbol(loopVar, loopVar.getValue(), new EagleInteger(i));
			}

			metric.iterate();

			for (PLI_StatementOrComment stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}

			if (hasLoop)
			{
				i += step;
			}
			if (untilCond != null)
			{
				boolean cond = interpreter.getBoolValue(untilCond);
				if (cond) break;
			}
		}

		_metrics.competedLoop(metric);
		
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression whileCond = null;
		AbstractVariable loopVar = null;
		AbstractExpression startExpr = null;
		AbstractExpression stopExpr = null;
		AbstractExpression byExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_EQUALS;

		// Pick up options in the DO command
		if (doLoop != null && doLoop.isPresent())
		{
			loopVar = generator.newVariable(doLoop.id.getValue());
			startExpr = transformer.transformExpression(generator, doLoop.fromExpr);
			stopExpr = transformer.transformExpression(generator, doLoop.toExpr);
			if (doLoop.by != null && doLoop.by.isPresent())
			{
				byExpr = transformer.transformExpression(generator, doLoop.by.byExpr);
				if (doLoop.by.byExpr.getWhich() instanceof PLI_Number)
				{
					PLI_Number number = (PLI_Number) doLoop.by.byExpr.getWhich();
					if (number.getValue().startsWith("-"))
					{
						// What if it is a variable that happens to be negative? Yikes!
						relOp = RelationalEnum.GREATER_EQUALS;
					}
				}
			}
		}
		if (doWhile != null && doWhile.isPresent())
		{
			whileCond = transformer.transformExpression(generator, doWhile.condition);
		}
		if (doUntil != null && doUntil.isPresent())
		{
			AbstractExpression untilCond = transformer.transformExpression(generator, doUntil.condition);
			whileCond = generator.newNotExpression(untilCond, doUntil);
		}
		if (FOREVER != null && FOREVER.isPresent())
		{
			whileCond = generator.newBuiltInExpression(BuiltInEnum.TRUE, FOREVER);
		}
		
		// Body is the same for all types of 'DO'
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();
		for (PLI_StatementOrComment stmtComm : statements._elements)
		{
			AbstractToken which = stmtComm.getWhich();
			if (which instanceof PLI_Statement)
			{
				PLI_Statement stmt = (PLI_Statement) which;
				AbstractStatement newStmt = transformer.transformStatement1(generator, stmt);
				newStmts.add(newStmt);
			}
		}

		// And now generate the output code
		if (loopVar != null)
		{
			if (whileCond != null)
			{
				throw new RuntimeException("Can't handle both DO loop and while");
			}
			return generator.newForRangeStatement(loopVar, TypeEnum.VOID, startExpr,
					relOp, stopExpr, byExpr, newStmts, this);
		}
		else if (whileCond != null)
		{
			return generator.newWhileStatement(whileCond, newStmts, DO);
		}
		else
		{
			// Simple DO / END block
			return generator.newBlockStatement(newStmts, DO);
		}
	}
}
