// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableAndSubscript;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_Function_Call;
import com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Expressions.Python_RangeExpression;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Python_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				EagleTransformableStatement
{
	public @S(10) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(20) @DOC("compound_stmts.html#the-for-statement") @NOSPACE Python_Keyword FOR = new Python_Keyword("for");
	public @S(30) Python_ForWhat what;
	public @S(40) Python_Keyword IN = new Python_Keyword("in");
	public @S(50) Python_ExpressionList expressionList;
	public @S(60) @NOSPACE PunctuationColon colon;
	public @S(70) @OPT Python_Comment comment;
	public @S(80) @PYDENT Python_StatementBlock forBlock;
	public @S(90) @OPT Python_ForElse forElseStatement;

	public static class Python_ForWhat extends TokenChooser
	{
		public @CHOICE Python_VariableList XXvarList;

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

	private @SKIP ForLoopMetrics _metrics = null;

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
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
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
					var = justVar.variable.first().variable;
					if (var.var.getWhich() instanceof Python_Identifier_Reference)
					{
						Python_Identifier_Reference id = (Python_Identifier_Reference) var.var.getWhich();
						varName = id.getValue();
					}
				}
			}
			
			metric.iterate();
			interpreter.setSymbol(var, varName, new EagleInteger(i));

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
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
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
			throw new RuntimeException("Python FOR statement requires a Range of values");
		}

		AbstractExpression initExpr = transformer.transformExpression(generator, rangeExpr.start);
		AbstractExpression termExpr = transformer.transformExpression(generator, rangeExpr.stop);
		AbstractExpression incrExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_THAN;
		if (rangeExpr.increment != null && rangeExpr.increment.isPresent())
		{
			incrExpr = transformer.transformExpression(generator, rangeExpr.increment.incr);
			AbstractToken whichIncr = rangeExpr.increment.incr.getWhich();
			if (whichIncr instanceof Python_Negative_Expression)
			{
				relOp = RelationalEnum.GREATER_THAN;
			}
			else if (whichIncr instanceof Python_Number)
			{
				Python_Number num = (Python_Number) whichIncr;
				if (num.getValue().startsWith("-"))
				{
					relOp = RelationalEnum.GREATER_THAN;
				}
			}
		}

		ArrayList<AbstractStatement> newStmts = forBlock.transformStatement(transformer, generator);
		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		if (newStmts != null)
		{
			for (AbstractStatement stmt : newStmts)
			{
				actionList.add(stmt);
			}
		}
		
		AbstractVariable newVar = null;
		if (what.getWhich() instanceof Python_VariableList)
		{
			Python_VariableList varList = (Python_VariableList) what.getWhich();
			Python_VariableOrList varOrList = varList.vars.first();
			if (varOrList.getWhich() instanceof Python_Just_Var)
			{
				Python_Just_Var justVar = (Python_Just_Var) varOrList.getWhich();
				Python_Variable pyVar = justVar.variable.first().variable;
				AbstractToken whichVar = pyVar.var.getWhich();
				if (whichVar instanceof Python_Identifier_Reference)
				{
					Python_Identifier_Reference id = (Python_Identifier_Reference) whichVar;
					newVar = generator.newVariable(id.getValue());
				}
			}
		}

		return generator.newForRangeStatement(newVar, TypeEnum.INTEGER, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
	}

	public Python_ComplexStatement generateForLoop1(Python_Expression initExpression,
			Python_Expression condExpression, Python_Expression incrExpression,
			Python_ComplexStatement action, AbstractToken source)
	{
		ArrayList<Python_ComplexStatement> actions = new ArrayList<Python_ComplexStatement>();
		actions.add(action);
		return generateForLoop(initExpression, condExpression, incrExpression,
				actions, source);
	}
	
	public Python_ComplexStatement generateForLoop(Python_Expression initExpression,
			Python_Expression condExpression, Python_Expression incrExpression,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		// Condition might be "! (x < 10)" or something. Oof.
		Python_Expression condition = condExpression;
		if (condExpression.getWhich() instanceof Python_Logical_Not_Expression)
		{
			Python_Logical_Not_Expression notExpr = (Python_Logical_Not_Expression) condExpression.getWhich();
			if (notExpr.expr.getWhich() instanceof Python_Parenthesized_Expression)
			{
				Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) notExpr.expr.getWhich();
				if (parens.list.expr.getWhich() instanceof Python_Relational_Expression)
				{
					// Ok, it matches, now reverse the relational operator. '<' becomes '>=' etc
					Python_Relational_Expression rel = (Python_Relational_Expression) parens.list.expr.getWhich();
					if (rel.operator.getWhich() instanceof Python_PunctuationChoice)
					{
						Python_PunctuationChoice punct = (Python_PunctuationChoice) rel.operator.getWhich();
						Python_PunctuationChoice newPunct = null;
						switch (punct.getValue())
						{
						case "<":
							newPunct = new Python_PunctuationChoice(">=");
							break;
						case "<=":
							newPunct = new Python_PunctuationChoice(">");
							break;
						case "==":
							newPunct = new Python_PunctuationChoice("!=");
							break;
						case "!=", "<>":
							newPunct = new Python_PunctuationChoice("==");
							break;
						case ">=":
							newPunct = new Python_PunctuationChoice("<");
							break;
						case ">":
							newPunct = new Python_PunctuationChoice("<=");
							break;
						}
						// Switch to this new inverted condition
						rel.operator.setWhich(newPunct);
						condition = Python_Generator.wrapExpression(rel);
					}
				}
			}
		}
		
		// Let's just deal with easy case: for (i=0; i<10; i++) etc.
		if (! (initExpression.getWhich() instanceof Python_Assignment_Expression) ||
				! (condition.getWhich() instanceof Python_Relational_Expression) ||
				! (incrExpression.getWhich() instanceof Python_Assignment_Expression))
		{
			throw new RuntimeException("Need to implement");
		}
		
		Python_Assignment_Expression init = (Python_Assignment_Expression) initExpression.getWhich();
		if (! init.operator.getValue().equals("=") ||
				! (init.left.getWhich() instanceof Python_VariableExpression))
		{
			throw new RuntimeException("Assignment part too complicated for now.");
		}
		
		Python_Assignment_Expression incr = (Python_Assignment_Expression) incrExpression.getWhich();
		if (! (incr.left.getWhich() instanceof Python_VariableExpression) ||
				! (incr.right.getWhich() instanceof Python_Number))
		{
			throw new RuntimeException("Increment part too complicated for now.");
		}
		String incrOper = incr.operator.getValue();
		Python_Number number = (Python_Number) incr.right.getWhich();
		int delta = Integer.parseInt(number.getValue());
		switch (incrOper)
		{
		case "+=":
			break;
		case "-=":
			delta = - delta;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + incrOper);
		}
		
		Python_Relational_Expression cond = (Python_Relational_Expression) condition.getWhich();
		if (! (cond.operator.getWhich() instanceof Python_PunctuationChoice) ||
				! (cond.left.getWhich() instanceof Python_VariableExpression))
		{
			throw new RuntimeException("Condition part too complicated for now.");
		}
		Python_PunctuationChoice condOper = (Python_PunctuationChoice) cond.operator.getWhich();
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
		
		Python_VariableExpression initVarExp = (Python_VariableExpression) init.left.getWhich();
		Python_VariableExpression condVarExp = (Python_VariableExpression) cond.left.getWhich();
		Python_VariableExpression incrVarExp = (Python_VariableExpression) incr.left.getWhich();
		
		AbstractToken initWhich = initVarExp.variable.var.getWhich();
		AbstractToken condWhich = condVarExp.variable.var.getWhich();
		AbstractToken incrWhich = incrVarExp.variable.var.getWhich();
		if (! (initWhich instanceof Python_Identifier_Reference) ||
				! (condWhich instanceof Python_Identifier_Reference) ||
				! (incrWhich instanceof Python_Identifier_Reference))
		{
			throw new RuntimeException("Must use variables in all parts");
		}
		
		Python_Identifier_Reference initId = (Python_Identifier_Reference) initWhich;
		Python_Identifier_Reference condId = (Python_Identifier_Reference) condWhich;
		Python_Identifier_Reference incrId = (Python_Identifier_Reference) incrWhich;
		String id = initId.getValue();
		if (! condId.getValue().equals(id) || ! incrId.getValue().equals(id))
		{
			throw new RuntimeException("Must use the same variable in all parts");
		}
		
		// Ok, made it through the gauntlet ....
		Python_Number numb = Python_Number.createNumber(delta);
		Python_Expression deltaExp = new Python_Expression();
		deltaExp.setWhich(numb);
		return generateForRange(initVarExp.variable, init.right, relOper, cond.right, deltaExp, actions, source);
	}

	public Python_ComplexStatement generateForRange1(Python_Variable var, Python_Expression fromExpression,
			RelationalEnum relOp, Python_Expression toExpression, Python_Expression delta,
			Python_ComplexStatement action, AbstractToken source)
	{
		ArrayList<Python_ComplexStatement> actions = new ArrayList<Python_ComplexStatement>();
		actions.add(action);
		return generateForRange(var, fromExpression, relOp, toExpression, delta, actions, source);
	}

	public Python_ComplexStatement generateForRange(Python_Variable var, Python_Expression fromExpression,
			RelationalEnum relOper, Python_Expression toExpression, Python_Expression delta,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		this.colon = new PunctuationColon();
		this.forBlock = new Python_StatementBlock();
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_ComplexStatement>();
		this.forBlock.setWhich(multi);
		
		this.what = new Python_ForWhat();
		Python_VariableList varList = new Python_VariableList();
		varList.vars = new SeparatedList<Python_VariableOrList, PunctuationComma>();
		Python_VariableOrList varOrList = new Python_VariableOrList();
		Python_Just_Var justVar = new Python_Just_Var();
		justVar.variable = new SeparatedList<Python_VariableAndSubscript, PunctuationPeriod>();
		Python_VariableAndSubscript varAndSub = new Python_VariableAndSubscript();
		varAndSub.variable = var;
		justVar.variable.addPrimaryElement(varAndSub);
		varOrList.setWhich(justVar);
		varList.vars.addPrimaryElement(varOrList);
		this.what.setWhich(varList);

		for (Python_ComplexStatement stmt : actions)
		{
			multi.statements.addToken(stmt);

			// If the parent block gets the 'while' as the parent, line numbers in the
			// side-by-side will pick up the 'while' instead of the first statement.
			if (this.getTransformationSource() == null)
			{
				this.setTransformationSource(stmt.getTransformationSource());
			}
		}

		SeparatedList<Python_Expression, PunctuationComma> argList =
				new SeparatedList<Python_Expression, PunctuationComma>();
		argList.addPrimaryElement(fromExpression);
		
		// range(3,6,1) generates 3,4,5
		// range(6,3,-1) generates 6,5,4
		// Need to add 1 if delta > 0 or delta = null, subtract 1 if delta < 0
		Python_Expression high = toExpression;
		switch (relOper)
		{
		case LESS_EQUALS:
		case GREATER_EQUALS:
			// Have to switch to x+1 or x-1
			AdditiveEnum oper = AdditiveEnum.PLUS;
			if (delta != null)
			{
				AbstractToken whichDelta = delta.getWhich();
				if (whichDelta instanceof Python_Number)
				{
					Python_Number num = (Python_Number) whichDelta; // It is still a string
					if (num.getValue().trim().startsWith("-")) oper = AdditiveEnum.MINUS;
				}
				else
				{
					throw new RuntimeException("need to implement");
				}
			}

			Python_Number one = new Python_Number();
			Python_Expression oneExpr = Python_Generator.wrapExpression(one.generateNumber("1", null));
		
			Python_Parenthesized_Expression parens = new Python_Parenthesized_Expression();
			Python_Expression parenExpr = parens.generateParentheses(toExpression, null);
			
			Python_Additive_Expression add = new Python_Additive_Expression();
			Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
			high = add.generateAdditive(types, parenExpr, oper, oneExpr, null);
			break;
		default:
			// No change needed for =, <>, <= or >=
		}
		argList.addSecondaryElement(new PunctuationComma());
		argList.addPrimaryElement(high);

		if (delta != null)
		{
			argList.addSecondaryElement(new PunctuationComma());
			argList.addPrimaryElement(delta);
		}

		Python_Function_Call fnCall = new Python_Function_Call();
		fnCall.fnName = Python_Variable.newVariable("range");
		fnCall.leftParen = new PunctuationLeftParen();
		fnCall.argList = argList;
		fnCall.rightParen = new PunctuationRightParen();

		Python_Expression rangeExpr = new Python_Expression();
		rangeExpr.setWhich(fnCall);

		this.expressionList = new Python_ExpressionList();
		this.expressionList.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
		this.expressionList.expressions.addPrimaryElement(rangeExpr);

		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
