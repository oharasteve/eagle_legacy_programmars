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
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

	public static CSharp_Statement generateIfElseOne(CSharp_Expression cond,
			CSharp_Statement thenStmt,
			CSharp_Statement elseStmt, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		ifStmt.leftParen = new PunctuationLeftParen();
		ifStmt.rightParen = new PunctuationRightParen();

		AbstractToken which = cond.getWhich();
		if (which instanceof CSharp_ParenthesizedExpression)
		{
			CSharp_ParenthesizedExpression parensExpr = (CSharp_ParenthesizedExpression) which;
			// Remove redundant parens
			ifStmt.condition = parensExpr.expression;
		}
		else
		{
			ifStmt.condition = cond;
		}

		ifStmt.thenStatement = thenStmt;

		if (elseStmt != null)
		{
			ifStmt.elseClause = new CSharp_IfElseClause();
			ifStmt.elseClause.setPresent(true);
			ifStmt.elseClause.elseStatement = elseStmt;
			ifStmt.elseClause.elseStatement.setPresent(true);
		}

		ifStmt.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(ifStmt);
	}

	public static CSharp_Statement generateIfElseMany(CSharp_Expression cond,
			ArrayList<CSharp_Statement> thenStatements,
			ArrayList<CSharp_Statement> elseStatements, AbstractToken source)
	{
		CSharp_Statement blockThen = CSharp_StatementBlock.generateBlock(thenStatements, source);

		CSharp_Statement blockElse = null;
		if (elseStatements != null && elseStatements.size() > 0)
		{
			blockElse = CSharp_StatementBlock.generateBlock(elseStatements, source);
		}

		return generateIfElseOne(cond, blockThen, blockElse, source);
	}

	public static CSharp_Statement generateIfElseIfMany(CSharp_Expression cond,
			ArrayList<CSharp_Statement> ifTrue, ArrayList<CSharp_Expression> elseIfConds,
			ArrayList<ArrayList<CSharp_Statement>> elseIfParts, ArrayList<CSharp_Statement> ifFalse,
			AbstractToken source)
	{
		CSharp_Statement thenBlock;
		CSharp_Statement currElse = null;
		if (ifFalse != null)
		{
			currElse = CSharp_StatementBlock.generateBlock(ifFalse, source);
		}

		// Work from the bottom (last else) up to the top (first condition)
		if (elseIfConds != null)
		{
			int countElIfs = elseIfConds.size();
			for (int i = countElIfs-1; i >= 0; i--)
			{
				CSharp_Expression currCond = elseIfConds.get(i);
				ArrayList<CSharp_Statement> currThen = elseIfParts.get(i);
				thenBlock = CSharp_StatementBlock.generateBlock(currThen, source);
				currElse = generateIfElseOne(currCond, thenBlock, currElse, source);
			}
		}
		
		thenBlock = CSharp_StatementBlock.generateBlock(ifTrue, source);
		return generateIfElseOne(cond, thenBlock, currElse, source);
	}
}
