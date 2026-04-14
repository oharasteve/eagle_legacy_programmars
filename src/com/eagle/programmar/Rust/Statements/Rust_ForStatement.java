// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleRange;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Functions.Rust_RevMethod;
import com.eagle.programmar.Rust.Functions.Rust_StepByMethod;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/loop-expr.html#iterator-loops") @NEWLINE Rust_Keyword FOR =
			new Rust_Keyword("for");
	public @S(20) Rust_Variable variable;
	public @S(30) Rust_Keyword IN = new Rust_Keyword("in");
	public @S(40) Rust_Expression values;
	public @S(50) Rust_Statement statement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleRange range = interpreter.getRangeValue(values);
		int start = range._lowValue;
		int stop = range._highValue;
		int step = range._step;

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		int i = start;
		boolean backwards = false;
		if (step < 0)
		{
			// Careful!
			// 1..4 does 1, 2, 3
			// (1..4).rev() does 3, 2, 1
			backwards = true;
			i = stop + step; // Careful!
		}

		while (true)
		{
			if (backwards && i < start) break;
			if (!backwards && i >= stop) break;

			metric.iterate();
			interpreter.setSymbol(variable, variable.var.toString(), new EagleInteger(i));

			result = interpreter.tryToInterpret(statement);

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

			i += step; // Might be negative
		}

		_metrics.competedLoop(metric, backwards);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = values.getWhich();
		Rust_RangeExpression range = null;
		AbstractExpression initExpr = null;
		AbstractExpression termExpr = null;
		AbstractExpression incrExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_THAN;
		if (which instanceof Rust_RangeExpression)
		{
			range = (Rust_RangeExpression) which;
			initExpr = transformer.transformExpression(generator, range.lowExpression);
			termExpr = transformer.transformExpression(generator, range.highExpression);
		}
		if (which instanceof Rust_RevMethod)
		{
			Rust_RevMethod reversed = (Rust_RevMethod) which;
			if (reversed.left.getWhich() instanceof Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) reversed.left.getWhich();
				if (parens.expressions.first().getWhich() instanceof Rust_RangeExpression)
				{
					range = (Rust_RangeExpression) parens.expressions.first().getWhich();
					initExpr = transformer.transformExpression(generator, range.highExpression);
					AbstractExpression oneExpr = generator.newNumberExpression("1", null);
					initExpr = generator.newAdditiveExpression(null, initExpr, AdditiveEnum.MINUS, oneExpr, null);
					termExpr = transformer.transformExpression(generator, range.lowExpression);
					incrExpr = generator.newNumberExpression("-1", null);
					relOp = RelationalEnum.GREATER_EQUALS;
				}
			}
		}
		if (range == null)
		{
			throw new RuntimeException("FOR statement requires a Range of values, not " + which);
		}

		ArrayList<AbstractStatement> newStmts = Rust_Block_Statement.collectStatements(transformer, generator,
				statement);
		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		if (newStmts != null)
		{
			for (AbstractStatement stmt : newStmts)
			{
				actionList.add(stmt);
			}
		}

		AbstractVariable var = generator.newVariable(variable.var.getValue());
		return generator.newForRangeStatement(var, TypeEnum.INTEGER, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
	}

	public static Rust_Statement generateForLoopOne(Rust_Expression initExpression,
			Rust_Expression condExpression, Rust_Expression incrExpression,
			Rust_Statement action, AbstractToken source)
	{
		ArrayList<Rust_Statement> actions = new ArrayList<Rust_Statement>();
		actions.add(action);
		return generateForLoopMany(initExpression, condExpression, incrExpression,
				actions, source);
	}

	public static Rust_Statement generateForLoopMany(Rust_Expression initExpression,
			Rust_Expression condExpression, Rust_Expression incrExpression,
			ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		// Condition might be "! (x < 10)" or something. Oof.
		Rust_Expression condition = condExpression;
		if (condExpression.getWhich() instanceof Rust_NotExpression)
		{
			Rust_NotExpression notExpr = (Rust_NotExpression) condExpression.getWhich();
			if (notExpr.expr.getWhich() instanceof Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) notExpr.expr.getWhich();
				if (parens.expressions.first().getWhich() instanceof Rust_RelationalExpression)
				{
					// Ok, it matches, now reverse the relational operator. '<' becomes '>=' etc
					Rust_RelationalExpression rel = (Rust_RelationalExpression) parens.expressions.first().getWhich();
					String punct = rel.operator.getValue();
					Rust_PunctuationChoice newPunct = null;
					switch (punct)
					{
					case "<":
						newPunct = new Rust_PunctuationChoice(">=");
						break;
					case "<=":
						newPunct = new Rust_PunctuationChoice(">");
						break;
					case "==":
						newPunct = new Rust_PunctuationChoice("!=");
						break;
					case "!=", "<>":
						newPunct = new Rust_PunctuationChoice("==");
						break;
					case ">=":
						newPunct = new Rust_PunctuationChoice("<");
						break;
					case ">":
						newPunct = new Rust_PunctuationChoice("<=");
						break;
					}
					// Switch to this new inverted condition
					rel.operator = newPunct;
					condition = Rust_Generator.wrapExpression(rel);
				}
			}
		}

		// Let's just deal with easy case: for (i=0; i<10; i++) etc.
		if (!(initExpression.getWhich() instanceof Rust_AssignmentExpression) ||
				!(incrExpression.getWhich() instanceof Rust_AssignmentExpression))
		{
			throw new RuntimeException("For loops init and incr must be assignments");
		}
		
		AbstractToken whichCond = condition.getWhich();
		if (whichCond instanceof Rust_NotExpression)
		{
			Rust_NotExpression notExpr = (Rust_NotExpression) whichCond;
			whichCond = notExpr.expr.getWhich();
		}
		
		if (whichCond instanceof Rust_ParenthesizedExpression)
		{
			Rust_ParenthesizedExpression parenExpr = (Rust_ParenthesizedExpression) whichCond;
			whichCond = parenExpr.expressions.first().getWhich();
		}

		if (!(whichCond instanceof Rust_RelationalExpression))
		{
			throw new RuntimeException("For loop test must be a relational expression");
		}
		Rust_RelationalExpression cond = (Rust_RelationalExpression) whichCond;

		Rust_AssignmentExpression init = (Rust_AssignmentExpression) initExpression.getWhich();
		if (!init.operator.getValue().equals("=") ||
				!(init.var.getWhich() instanceof Rust_VariableExpression))
		{
			throw new RuntimeException("Assignment part too complicated for now.");
		}

		Rust_AssignmentExpression incr = (Rust_AssignmentExpression) incrExpression.getWhich();
		if (!(incr.var.getWhich() instanceof Rust_VariableExpression) ||
				!(incr.expr.getWhich() instanceof Rust_Number))
		{
			throw new RuntimeException("Increment part too complicated for now.");
		}
		String incrOper = incr.operator.getValue();
		Rust_Number number = (Rust_Number) incr.expr.getWhich();
		int delta = Integer.parseInt(number.getValue());
		switch (incrOper)
		{
		case "+=":
			break;
		case "-=":
			delta = -delta;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + incrOper);
		}

		if (!(cond.left.getWhich() instanceof Rust_VariableExpression))
		{
			throw new RuntimeException("Condition part too complicated for now.");
		}
		Rust_PunctuationChoice condOper = cond.operator;
		RelationalEnum relOper;
		switch (condOper.getValue())
		{
		case "=":
			relOper = RelationalEnum.EQUALS;
			break;
		case "<>", "!=":
			relOper = RelationalEnum.NOT_EQUALS;
			break;
		case "<":
			relOper = RelationalEnum.LESS_THAN;
			break;
		case "<=":
			relOper = RelationalEnum.LESS_EQUALS;
			break;
		case ">=":
			relOper = RelationalEnum.GREATER_EQUALS;
			break;
		case ">":
			relOper = RelationalEnum.GREATER_THAN;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + condOper.getValue());
		}

		Rust_VariableExpression initVarExp = (Rust_VariableExpression) init.var.getWhich();
		Rust_VariableExpression condVarExp = (Rust_VariableExpression) cond.left.getWhich();
		Rust_VariableExpression incrVarExp = (Rust_VariableExpression) incr.var.getWhich();

		Rust_Identifier_Reference initId = initVarExp.variable.var;
		Rust_Identifier_Reference condId = condVarExp.variable.var;
		Rust_Identifier_Reference incrId = incrVarExp.variable.var;
		String id = initId.getValue();
		if (!condId.getValue().equals(id) || !incrId.getValue().equals(id))
		{
			throw new RuntimeException("Must use the same variable in all parts: cond=" +
					condId.getValue() + " incr=" + incrId.getValue() + " init=" + id);
		}

		// Ok, made it through the gauntlet ....
		Rust_Number numb = Rust_Number.createNumber(delta);
		Rust_Expression deltaExp = new Rust_Expression();
		deltaExp.setWhich(numb);
		return generateForRangeMany(initVarExp.variable, TypeEnum.INTEGER,
				init.expr, relOper, cond.right, deltaExp, actions, source);
	}

	public static Rust_Statement generateForRangeOne(Rust_Variable var, TypeEnum type,
			Rust_Expression fromExpression, RelationalEnum relOp, Rust_Expression toExpression,
			Rust_Expression delta, Rust_Statement action, AbstractToken source)
	{
		ArrayList<Rust_Statement> actions = new ArrayList<Rust_Statement>();
		actions.add(action);
		return generateForRangeMany(var, TypeEnum.INTEGER, fromExpression, relOp, toExpression,
				delta, actions, source);
	}

	public static Rust_Statement generateForRangeMany(Rust_Variable var, TypeEnum type,
			Rust_Expression fromExpression, RelationalEnum relOper, Rust_Expression toExpression,
			Rust_Expression delta, ArrayList<Rust_Statement> actions, AbstractToken source)
	{
		Rust_ForStatement forStmt = new Rust_ForStatement();

		forStmt.variable = var;

		Rust_RangeExpression range = new Rust_RangeExpression();
		
		AbstractToken which1 = fromExpression.getWhich();
		if ((which1 instanceof Rust_Number) || (which1 instanceof Rust_VariableExpression))
		{
			range.lowExpression = fromExpression;
		}
		else
		{
			range.lowExpression = Rust_ParenthesizedExpression.generateParentheses(fromExpression, null);
		}
		
		range.dots.setValue("..=");

		AbstractToken which2 = toExpression.getWhich();
		if ((which2 instanceof Rust_Number) || (which2 instanceof Rust_VariableExpression))
		{
			range.highExpression = toExpression;
		}
		else
		{
			range.highExpression = Rust_ParenthesizedExpression.generateParentheses(toExpression, null);
		}
		range.highExpression.setPresent(true);

		int incr = 1;
		if (delta != null)
		{
			if (! (delta.getWhich() instanceof Rust_Number))
			{
				throw new RuntimeException("For loop increment must be a constant");
			}
			Rust_Number num = (Rust_Number) delta.getWhich();
			incr = Integer.parseInt(num.getValue());
		}
		
		// range.rev().stepby(n) and range.stepby(n).rev() can be different
		// Hence, we don't support negative step right now.

		Rust_Expression rangeExpr = Rust_Generator.wrapExpression(range);
		if (incr == -1)
		{
			// Ada, e.g., switched them already, so we have to switch them back.
			Rust_Expression tempExpr = range.highExpression;
			range.highExpression = range.lowExpression;
			range.lowExpression = tempExpr;
			range.highExpression.setPresent(true);	// Again :(
			Rust_Expression parenRev = Rust_ParenthesizedExpression.generateParentheses(rangeExpr, null);
			rangeExpr = Rust_RevMethod.generateRev(parenRev);
		}
		else if (incr > 1)
		{
			Rust_Expression parenStep = Rust_ParenthesizedExpression.generateParentheses(rangeExpr, null);
			rangeExpr = Rust_StepByMethod.generateStepBy(parenStep, delta);
		}
		else if (incr != 1)
		{
			throw new RuntimeException("Cannot handle negative steps other than -1");
		}

		forStmt.values = rangeExpr;
		forStmt.statement = Rust_Block_Statement.generateBlock(actions, source);
		return Rust_Generator.wrapStatement(forStmt);
	}
}
