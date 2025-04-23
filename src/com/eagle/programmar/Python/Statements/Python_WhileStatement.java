// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.EagleGenerator.BuiltInEnum;
import com.eagle.generate.Statements.Eagle_Generate_DoUntil;
import com.eagle.generate.Statements.Eagle_Generate_While;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_MultilineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_StatementBlock;
import com.eagle.programmar.Python.Expressions.Python_BuiltIn;
import com.eagle.programmar.Python.Expressions.Python_Literals;
import com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression.Python_Or_Operation;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression.Python_Relational_Operator;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression.Python_Relational_Operator.Python_IN_Operator;
import com.eagle.programmar.Python.Functions.Python_Locals_Function;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_WhileStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				Eagle_Generate_While<Python_Statement, Python_Expression>,
				Eagle_Generate_DoUntil<Python_Statement, Python_Expression>
{
	public @S(10) @DOC("compound_stmts.html#the-while-statement") @NOSPACE Python_Keyword WHILE = new Python_Keyword(
			"while");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) Python_StatementBlock statements;
	public @S(50) @OPT Python_WhileElse whileElse;

	public static class Python_WhileElse extends TokenSequence
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
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		while (true)
		{
			if (! interpreter.getBoolValue(condition))
			{
				break;
			}
			
			metric.iterate();

			result = interpreter.tryToInterpret(statements);
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
		}

		_metrics.competedLoop(metric);
		return result;
	}

	@Override
	public Python_Statement generateDoUntil(Python_Expression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		String oddName = "_not_first_time_at_line_" + source.getStartLine() + "_";
		
		Python_Parenthesized_Expression parensExpr = new Python_Parenthesized_Expression();
		parensExpr.leftParen = new PunctuationLeftParen();
		parensExpr.list = new Python_List();
		parensExpr.list.expr = condition;
		parensExpr.rightParen = new PunctuationRightParen();
		
		Python_Logical_Not_Expression notExpr = new Python_Logical_Not_Expression();
		notExpr.expr = Python_Generator.wrapExpression(parensExpr);
		notExpr.NOT = new Python_Keyword("not");
		
		Python_Literals lits = Python_Literals.generateExpression(oddName, null);
		Python_Locals_Function localsFn = new Python_Locals_Function();
		localsFn.LOCALS = new Python_KeywordChoice("locals");
		Python_IN_Operator inOper = new Python_IN_Operator();
		inOper.NOT = new Python_Keyword("not");
		inOper.NOT.setPresent(true);
		inOper.IN = new Python_Keyword("in");
		
		Python_Relational_Expression inExpr = new Python_Relational_Expression();
		inExpr.left = Python_Generator.wrapExpression(lits);
		inExpr.right = Python_Generator.wrapExpression(localsFn);
		inExpr.relOp = new Python_Relational_Operator();
		inExpr.relOp.setWhich(inOper);
		
		Python_Logical_Or_Expression orExpr = new Python_Logical_Or_Expression();
		orExpr.left = Python_Generator.wrapExpression(inExpr);
		Python_Keyword OR = new Python_Keyword("or");
		orExpr.operator = new Python_Or_Operation();
		orExpr.operator.setWhich(OR);
		orExpr.right = Python_Generator.wrapExpression(notExpr);
		
		Python_Expression whileCond = Python_Generator.wrapExpression(orExpr);
		
		Python_BuiltIn built = new Python_BuiltIn();
		Python_Expression trueExpr = built.generateBuiltIn(BuiltInEnum.TRUE, null);
		Python_ExpressionStatement asgExprStmt = Python_Assignment.generateAssignment(
				oddName, null, AssignmentEnum.EQUALS, trueExpr, null, source);
		Python_Statement asgStmt = Python_Generator.wrapStatement(asgExprStmt);
		
		actions.add(0, asgStmt);	// Destructive. Is that ok?
		return generateWhile(whileCond, actions, source);
	}
	
	public Python_Statement generateWhile(Python_Expression condition,
			ArrayList<AbstractStatement> actions, AbstractToken source)
	{
		this.colon = new PunctuationColon();

		this.condition = condition;
		this.statements = new Python_StatementBlock();
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_Statement>();
		this.statements.setWhich(multi);

		for (AbstractStatement action : actions)
		{
			Python_Statement stmt = (Python_Statement) action;
			multi.statements.addToken(stmt);

			// If the parent block gets the 'while' as the parent, line numbers in the
			// side-by-side will pick up the 'while' instead of the first statement.
			if (this.getTransformationSource() == null)
			{
				this.setTransformationSource(stmt.getTransformationSource());
			}
		}

		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
