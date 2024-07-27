// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
import com.eagle.programmar.Python.Expressions.Python_RangeExpression;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(20) @DOC("compound_stmts.html#the-for-statement") @NOSPACE Python_Keyword FOR = new Python_Keyword("for");
	public @S(30) Python_ForWhat what;
	public @S(40) Python_Keyword IN = new Python_Keyword("in");
	public @S(50) Python_ExpressionList expressionList;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) @OPT Python_Comment comment;
	public @S(80) Python_StatementBlock forBlock;
	public @S(90) @OPT Python_ForElse forElseStatement;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Python_ForWhat extends TokenChooser
	{
		public @CHOICE Python_VariableList varList;

		public @CHOICE static class Python_ForList extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Python_VariableList varList;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}

	public static class Python_ForElse extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) Python_Keyword ELSE = new Python_Keyword("else");
		public @S(40) PunctuationColon colon;
		public @S(50) Python_StatementBlock doWhat;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Python_RangeExpression rangeExpr = null;
		if (expressionList.expressions.getPrimaryCount() == 1)
		{
			Python_Expression expr = expressionList.expressions.first();
			if (expr.getWhich() instanceof Python_RangeExpression)
			{
				rangeExpr = (Python_RangeExpression) expr.getWhich();
			}
		}
		
		if (rangeExpr == null)
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}
		
		int start = interpreter.getIntValue(rangeExpr.start);
		int stop = interpreter.getIntValue(rangeExpr.stop);
		int incr = 1;
		if (rangeExpr.increment != null && rangeExpr.increment.isPresent())
		{
			incr = interpreter.getIntValue(rangeExpr.increment.incr);
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (incr > 0 && i >= stop) break;
			if (incr < 0 && i <= stop) break;

			Python_Variable var = null;
			String varName = "unknown";
			if (what.getWhich() instanceof Python_VariableList)
			{
				Python_VariableList varList = (Python_VariableList) what.getWhich();
				Python_VariableOrList varOrList = varList.vars.first();
				if (varOrList.getWhich() instanceof Python_Just_Var)
				{
					Python_Just_Var justVar = (Python_Just_Var) varOrList.getWhich();
					var = justVar.variable.first();
					if (var.var.getWhich() instanceof Python_Identifier_Reference)
					{
						Python_Identifier_Reference id = (Python_Identifier_Reference) var.var.getWhich();
						varName = id.getValue();
					}
				}
			}
			
			metric.iterate();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					varName, new EagleInteger(i));

			result = interpreter.tryToInterpret(forBlock);

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

			i += incr;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
