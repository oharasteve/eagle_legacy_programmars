// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.IntegerValue;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Interpreter;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_For_Block extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) Eaglish_Keyword FOR = new Eaglish_Keyword("FOR");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Eaglish_Expression startValue;
	public @S(50) Eaglish_KeywordChoice TO = new Eaglish_KeywordChoice("TO", "DOWN_TO");
	public @S(60) Eaglish_Expression stopValue;
	public @S(70) Eaglish_EndOfLine eoln1;

	public @S(80) @OPT TokenList<Eaglish_Statement> statements;

	public @S(90) Eaglish_Keyword END_FOR = new Eaglish_Keyword("END_FOR");
	public @S(100) Eaglish_EndOfLine eoln2;
	
	private @SKIP ForLoopMetrics _metrics = null;
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interp)
	{
		Eaglish_Interpreter interpreter = (Eaglish_Interpreter) interp;
		int start = interpreter.getIntValue(startValue);
		int stop = interpreter.getIntValue(stopValue);
		
		if (_metrics == null)
		{
			_metrics =  new ForLoopMetrics(getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();
		
		String which = TO.getValue();
		switch (which)
		{
		case "TO":
			for (int i = start; i <= stop; i++)
			{
				// 100% identical to "DOWN_TO" below
				metric.iterate();
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(),
						var.getStartChar(), var.toString(), new IntegerValue(i));
				Eagle_Statement_Result result = interpreter.interpretBlock(statements._elements);
				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
				}
			}
			break;
		case "DOWN_TO":
			for (int i = start; i >= stop; i--)
			{
				// 100% identical to "TO" above
				metric.iterate();
				interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(),
						var.getStartChar(), var.toString(), new IntegerValue(i));
				Eagle_Statement_Result result = interpreter.interpretBlock(statements._elements);
				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
				}
			}
			break;
		default:
			throw new RuntimeException("Unable to handle " + which);
		}
		
		_metrics.competedLoop(metric);
		return Eagle_Statement_Result.NORMAL;
	}
}
