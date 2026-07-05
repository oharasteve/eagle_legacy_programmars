// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.IncrementEnum;
import com.eagle.generate.RelationalEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
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
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		int current = interpreter.getIntValue(from);
		int stop = interpreter.getIntValue(to);
		boolean reverse = TO_DOWNTO.getValue().equals("DownTo");

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			if (reverse)
			{
				if (current < stop) break;
			}
			else
			{
				if (current > stop) break;
			}

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
			}
			else
			{
				current++;
			}
		}

		_metrics.completedLoop(metric, reverse);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression fromExpr = transformer.transformExpression(generator, this.from);
		AbstractExpression toExpr = transformer.transformExpression(generator, this.to);
		String varName = this.var.getValue();
		AbstractExpression varExpr = generator.newVariableExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, null);
		AbstractStatement newAction = transformer.transformStatement1(
				generator, this.stmt);
		AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, fromExpr, null);

		String toDownto = this.TO_DOWNTO.getValue();
		AbstractExpression delta;
		AbstractExpression term;
		Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
		switch (this.TO_DOWNTO.getValue().toLowerCase())
		{
		case "to":
			delta = generator.newPostIncrementExpression(varName,
					SubscriptEnum.FIRST_IS_ZERO, null, IncrementEnum.INCREMENT, null);
			term = generator.newRelationalExpression(types, varExpr,
					RelationalEnum.LESS_EQUALS, toExpr, null);
			break;
		case "downto":
			delta = generator.newPostIncrementExpression(varName,
					SubscriptEnum.FIRST_IS_ZERO, null, IncrementEnum.DECREMENT, null);
			term = generator.newRelationalExpression(types, varExpr,
					RelationalEnum.GREATER_EQUALS, toExpr, null);
			break;
		default:
			throw new RuntimeException("Expected TO or DOWNTO, not " + toDownto);
		}

		return generator.newForLoopStatement1(asgExpr, null, term,
				delta, newAction, this);
	}
}
