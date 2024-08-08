// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Powershell_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#843-the-for-statement") Powershell_Keyword FOR =
			new Powershell_Keyword("For");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Variable var;
	public @S(40) PunctuationEquals equals;
	public @S(50) Powershell_Expression init;
	public @S(60) PunctuationSemicolon semicolon1;
	public @S(70) Powershell_Expression stopCondition;
	public @S(80) PunctuationSemicolon semicolon2;
	public @S(90) Powershell_Expression iterate;
	public @S(100) PunctuationRightParen rightParen;

	public @S(110) PunctuationLeftBrace leftBrace;
	public @S(120) @OPT Powershell_EndOfLine eoln;
	public @S(130) TokenList<Powershell_Statement> stmts;
	public @S(140) PunctuationRightBrace rightBrace;
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int start = interpreter.getIntValue(init);
		interpreter.setSymbol(this.getFileName(), this.getStartLine(), this.getStartChar(),
				var.id.getValue(), new EagleInteger(start));

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(stopCondition);
			if (!keepGoing) break;
			metric.iterate();

			for (Powershell_Statement stmt : stmts._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
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
	
			interpreter.tryToInterpret(iterate);
		}
		
		_metrics.competedLoop(metric);
		return result;
	}
}
