// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
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

public class CSharp_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
				EagleTransformableStatement
{
	public @S(10) @NEWLINE @DOC("statements/selection-statements") CSharp_Keyword IF = new CSharp_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression condition;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<CSharp_Comment> comments1;
	public @S(60) CSharp_Statement thenStatement;
	public @S(70) @OPT CSharp_IfElseClause elseClause;

	public static class CSharp_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<CSharp_Comment> comments2;
		public @S(20) @NEWLINE CSharp_Keyword ELSE = new CSharp_Keyword("else");
		public @S(30) @OPT TokenList<CSharp_Comment> comments3;
		public @S(40) CSharp_Statement elseStatement;
	}

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		CSharp_Statement todo = null;

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
			result = interpreter.tryToInterpret(todo);
		}

		return result;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator,
				condition);
		AbstractStatement thenPart = CSharp_StatementBlock.collectStatements(transformer, generator, thenStatement);

		AbstractStatement elsePart = null;
		if (elseClause != null && elseClause.isPresent())
		{
			elsePart = CSharp_StatementBlock.collectStatements(transformer, generator, elseClause.elseStatement);
		}

		return generator.newIfStatement1(cond, thenPart, elsePart, this);
	}

	public CSharp_Statement generateIfElse1(CSharp_Expression cond,
			CSharp_Statement thenStmt,
			CSharp_Statement elseStmt, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();

		AbstractToken which = cond.getWhich();
		if (which instanceof CSharp_ParenthesizedExpression)
		{
			CSharp_ParenthesizedExpression parensExpr = (CSharp_ParenthesizedExpression) which;
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
			this.elseClause = new CSharp_IfElseClause();
			this.elseClause.setPresent(true);
			this.elseClause.elseStatement = elseStmt;
			this.elseClause.elseStatement.setPresent(true);
		}

		this.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(this);
	}
	
	public CSharp_Statement generateIfElse(CSharp_Expression cond,
			ArrayList<CSharp_Statement> thenStatements,
			ArrayList<CSharp_Statement> elseStatements, AbstractToken source)
	{
		CSharp_StatementBlock thenBlock = new CSharp_StatementBlock();
		CSharp_Statement block1 = thenBlock.generateBlock(thenStatements, source);
				
		CSharp_Statement block2 = null;
		if (elseStatements != null && elseStatements.size() > 0)
		{
			CSharp_StatementBlock elseBlock = new CSharp_StatementBlock();
			block2 = elseBlock.generateBlock(elseStatements, source);
		}

		return generateIfElse1(cond, block1, block2, source);
	}
}
