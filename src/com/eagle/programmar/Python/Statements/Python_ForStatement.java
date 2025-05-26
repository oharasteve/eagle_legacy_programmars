// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator.AdditiveEnum;
import com.eagle.generate.Statements.Eagle_Generate_ForLoop;
import com.eagle.generate.Statements.Eagle_Generate_ForRange;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_ExpressionList;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Python_VariableList;
import com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableAndSubscript;
import com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Function_Call;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Expressions.Python_RangeExpression;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				Eagle_Generate_ForLoop<Python_ComplexStatement, Python_Expression>,
				Eagle_Generate_ForRange<Python_ComplexStatement, Python_Expression>
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
	public Python_ComplexStatement generateForLoop1(Python_Expression initExpression,
			Python_Expression condExpression, Python_Expression incrExpression,
			Python_ComplexStatement action, AbstractToken source)
	{
		ArrayList<Python_ComplexStatement> actions = new ArrayList<Python_ComplexStatement>();
		actions.add(action);
		return generateForLoop(initExpression, condExpression, incrExpression,
				actions, source);
	}
	
	@Override
	public Python_ComplexStatement generateForLoop(Python_Expression initExpression,
			Python_Expression condExpression, Python_Expression incrExpression,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		throw new RuntimeException("Need to implement");
	}

	@Override
	public Python_ComplexStatement generateForRange1(String varName, Python_Expression fromExpression,
			Python_Expression toExpression, Python_Expression delta,
			Python_ComplexStatement action, AbstractToken source)
	{
		Python_ForStatement forStmt = new Python_ForStatement();
		forStmt.colon = new PunctuationColon();
		forStmt.forBlock = new Python_StatementBlock();

		AbstractToken which = action.statementOrComment.getWhich();
		if (which instanceof Python_SameLineStatement)
		{
			Python_SameLineStatement statementList = (Python_SameLineStatement) which;
			Python_MultilineStatement multi = new Python_MultilineStatement();
			multi.statements = new TokenList<Python_ComplexStatement>();
			multi.eoln = new Python_EndOfLine();

			Python_StatementBlock singleOrMulti = new Python_StatementBlock();
			singleOrMulti.setWhich(multi);

			Python_ComplexStatement stmt = new Python_ComplexStatement();
			stmt.statementOrComment = new Python_StatementOrComment();
			stmt.statementOrComment.setWhich(statementList);
			multi.statements.addToken(stmt);
			forStmt.forBlock = singleOrMulti;
		}
		else if (which instanceof Python_MultilineStatement)
		{
			Python_MultilineStatement multi = (Python_MultilineStatement) which;
			forStmt.forBlock.setWhich(multi);
		}
		else
			throw new RuntimeException("Need to implement");

		forStmt.what = new Python_ForWhat();
		Python_VariableList varList = new Python_VariableList();
		forStmt.what.setWhich(varList);
		varList.vars = new SeparatedList<Python_VariableOrList, PunctuationComma>();

		Python_VariableOrList varOrList = new Python_VariableOrList();
		Python_Variable var = Python_Variable.newVariable(varName);
		SeparatedList<Python_VariableAndSubscript,PunctuationPeriod> vars =
				new SeparatedList<Python_VariableAndSubscript,PunctuationPeriod>();
		Python_VariableAndSubscript varSub = new Python_VariableAndSubscript();
		varSub.variable = var;
		vars.addPrimaryElement(varSub);
		Python_Just_Var justVar = new Python_Just_Var();
		justVar.variable = vars;
		varOrList.setWhich(justVar);
		varList.vars.addPrimaryElement(varOrList);

		SeparatedList<Python_Expression, PunctuationComma> argList = new SeparatedList<Python_Expression, PunctuationComma>();
		argList.addPrimaryElement(fromExpression);
		
		// range(3,6,1) generates 3,4,5
		// range(6,3,-1) generates 6,5,4
		// Need to add 1 if delta > 0 or delta = null, subtract 1 if delta < 0
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
		Python_Expression addExpr = add.generateAdditive(parenExpr, oper, oneExpr, null);
		argList.addSecondaryElement(new PunctuationComma());
		argList.addPrimaryElement(addExpr);

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

		forStmt.expressionList = new Python_ExpressionList();
		forStmt.expressionList.expressions = new SeparatedList<Python_Expression, PunctuationComma>();
		forStmt.expressionList.expressions.addPrimaryElement(rangeExpr);

		forStmt.setTransformationSource(source);
		return Python_Generator.wrapStatement(forStmt);
	}
	
	@Override
	public Python_ComplexStatement generateForRange(String varName, Python_Expression fromExpression,
			Python_Expression toExpression, Python_Expression delta,
			ArrayList<Python_ComplexStatement> actions, AbstractToken source)
	{
		throw new RuntimeException("need to implement");
	}
}
