// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_IfStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.9") Java_Keyword IF = new Java_Keyword("if");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE Java_Expression condition;
	public @S(50) @OPT TokenList<Java_Comment> comment1;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	public @S(70) @OPT TokenList<Java_Comment> comment2;
	public @S(80) Java_Statement thenStatement;
	public @S(90) @OPT Java_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Java_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<Java_Comment> comment3;
		public @S(20) @NEWLINE Java_Keyword ELSE = new Java_Keyword("else");
		public @S(30) @OPT Java_Comment comment;
		public @S(40) Java_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Java_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause));
			}
		}

		boolean cond = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond);
		if (cond)
		{
			todo = thenStatement;
		}
		else
		{
			// Check for 'else'
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.get(1).completedIf(true);
				todo = elseClause.elseStatement;
			}
		}

		if (todo != null)
		{
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
	
	public static Java_IfStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> thenStatements,
			ArrayList<AbstractStatement> elseStatements, AbstractToken source)
	{
		Java_IfStatement ifStmt = new Java_IfStatement();
		ifStmt.leftParen = new PunctuationLeftParen();
		ifStmt.rightParen = new PunctuationRightParen();

		Java_Expression cond = (Java_Expression) condition;
		AbstractToken which = cond.getWhich();
		if (which instanceof Java_ParenthesizedExpression)
		{
			Java_ParenthesizedExpression parensExpr = (Java_ParenthesizedExpression) which;
			// Remove redundant parens
			ifStmt.condition = parensExpr.expression;
		}
		else
		{
			ifStmt.condition = cond;
		}

		Java_StatementBlock thenBlock = new Java_StatementBlock();
		thenBlock.statements = new TokenList<Java_StatementOrComment>();
		ifStmt.thenStatement = new Java_Statement();
		ifStmt.thenStatement.setWhich(thenBlock);
		for (AbstractStatement stmt : thenStatements)
		{
			Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
			stmtOrComment.setWhich((Java_Statement) stmt);
			thenBlock.statements.addToken(stmtOrComment);
		}
				
		if (elseStatements != null && elseStatements.size() > 0)
		{
			ifStmt.elseClause = new Java_IfElseClause();
			ifStmt.elseClause.setPresent(true);
			Java_StatementBlock elseBlock = new Java_StatementBlock();
			elseBlock.statements = new TokenList<Java_StatementOrComment>();
			ifStmt.elseClause.elseStatement = new Java_Statement();
			ifStmt.elseClause.elseStatement.setWhich(elseBlock);
			for (AbstractStatement stmt : elseStatements)
			{
				Java_StatementOrComment stmtOrComment = new Java_StatementOrComment();
				stmtOrComment.setWhich((Java_Statement) stmt);
				elseBlock.statements.addToken(stmtOrComment);
			}
		}

		ifStmt.setTransformationSource(source);
		return ifStmt;
	}
}
