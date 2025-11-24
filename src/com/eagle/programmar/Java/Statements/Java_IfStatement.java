// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
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

	public static class Java_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<Java_Comment> comment3;
		public @S(20) @NEWLINE Java_Keyword ELSE = new Java_Keyword("else");
		public @S(30) @OPT Java_Comment comment;
		public @S(40) Java_Statement elseStatement;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Java_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
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
			result = interpreter.tryToInterpret(todo.getWhich());
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator,
				condition);
		AbstractStatement thenPart = Java_StatementBlock.collectStatements(transformer, generator, thenStatement);

		AbstractStatement elsePart = null;
		if (elseClause != null && elseClause.isPresent())
		{
			elsePart = Java_StatementBlock.collectStatements(transformer, generator, elseClause.elseStatement);
		}

		return generator.newIfStatement1(cond, thenPart, elsePart, this);
	}

	public Java_Statement generateIfElse1(Java_Expression cond,
			Java_Statement thenStmt, Java_Statement elseStmt, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();

		AbstractToken which = cond.getWhich();
		if (which instanceof Java_ParenthesizedExpression)
		{
			Java_ParenthesizedExpression parensExpr = (Java_ParenthesizedExpression) which;
			// Remove redundant parens
			this.condition = parensExpr.expression;
		}
		else
		{
			this.condition = cond;
		}

		this.thenStatement = thenStmt;

		if (elseStmt != null)
		{
			this.elseClause = new Java_IfElseClause();
			this.elseClause.setPresent(true);
			this.elseClause.elseStatement = elseStmt;
			this.elseClause.elseStatement.setPresent(true);
		}

		this.setTransformationSource(source);
		return Java_Generator.wrapStatement(this);
	}

	public Java_Statement generateIfElse(Java_Expression cond,
			ArrayList<Java_Statement> thenStatements,
			ArrayList<Java_Statement> elseStatements, AbstractToken source)
	{
		Java_StatementBlock thenBlock = new Java_StatementBlock();
		Java_Statement blockTrue = thenBlock.generateBlock(thenStatements, source);

		Java_Statement blockElse = null;
		if (elseStatements != null && elseStatements.size() > 0)
		{
			Java_StatementBlock elseBlock = new Java_StatementBlock();
			blockElse = elseBlock.generateBlock(elseStatements, source);
		}

		return generateIfElse1(cond, blockTrue, blockElse, source);
	}
}
