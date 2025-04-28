// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_For_Statement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
				EagleTransformableStatement
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#For_Statements") Delphi_Keyword FOR = new Delphi_Keyword(
			"For");
	public @S(20) Delphi_Identifier_Reference var;
	public @S(30) Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
	public @S(40) Delphi_Expression from;
	public @S(50) Delphi_KeywordChoice TO_DOWNTO = new Delphi_KeywordChoice("To", "DownTo");
	public @S(60) Delphi_Expression to;
	public @S(70) Delphi_Keyword DO = new Delphi_Keyword("Do");
	public @S(80) @OPT TokenList<Delphi_Comment> comments;
	public @S(90) Delphi_Statement stmt;
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		int current = interpreter.getIntValue(from);
		int stop = interpreter.getIntValue(to);
		boolean reverse = TO_DOWNTO.getValue().equals("DownTo");
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			metric.iterate();
			interpreter.setSymbol(this, var.getValue(), new EagleInteger(current));

			result = interpreter.tryToInterpret(stmt);
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

			if (reverse)
			{
				current--;
				if (current < stop) break;
			}
			else
			{
				current++;
				if (current > stop) break;
			}
		}

		_metrics.competedLoop(metric);
		return result;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression fromExpr = transformer.transformExpression(generator, this.from);
		AbstractExpression toExpr = transformer.transformExpression(generator, this.to);
		String var = this.var.getValue();
		AbstractStatement newAction = transformer.transformStatement1(
				generator, this.stmt);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var, null,
				AssignmentEnum.EQUALS, fromExpr, null);

		String toDownto = this.TO_DOWNTO.getValue();
		AbstractExpression delta;
		switch (this.TO_DOWNTO.getValue().toLowerCase())
		{
		case "to":
			delta = null;
			break;
		case "downto":
			delta = generator.newNumberExpression("-1", null);
			break;
		default:
			throw new RuntimeException("Expected TO or DOWNTO, not " + toDownto);
		}

		return generator.newForLoopStatement1(asgExpr, toExpr, delta,
				newAction, this);
	}
}
