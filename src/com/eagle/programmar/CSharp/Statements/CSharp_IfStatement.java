// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
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

public class CSharp_IfStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.9") CSharp_Keyword IF = new CSharp_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression condition;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<CSharp_Comment> comments1;
	public @S(60) CSharp_Statement thenStatement;
	public @S(70) @OPT CSharp_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class CSharp_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<CSharp_Comment> comments2;
		public @S(20) @NEWLINE CSharp_Keyword ELSE = new CSharp_Keyword("else");
		public @S(30) @OPT TokenList<CSharp_Comment> comments3;
		public @S(40) CSharp_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		CSharp_Statement todo = null;

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
	
	public static CSharp_IfStatement newIfStatement(AbstractExpression condition, ArrayList<AbstractStatement> thenStatements,
			ArrayList<AbstractStatement> elseStatements, AbstractToken source)
	{
		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
		ifStmt.leftParen = new PunctuationLeftParen();
		ifStmt.rightParen = new PunctuationRightParen();

		CSharp_Expression cond = (CSharp_Expression) condition;
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

		CSharp_StatementBlock thenBlock = new CSharp_StatementBlock();
		thenBlock.statements = new TokenList<CSharp_StatementOrComment>();
		ifStmt.thenStatement = new CSharp_Statement();
		ifStmt.thenStatement.setWhich(thenBlock);
		for (AbstractStatement stmt : thenStatements)
		{
			CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
			stmtOrComment.setWhich((CSharp_Statement) stmt);
			thenBlock.statements.addToken(stmtOrComment);
		}
				
		if (elseStatements != null && elseStatements.size() > 0)
		{
			ifStmt.elseClause = new CSharp_IfElseClause();
			ifStmt.elseClause.setPresent(true);
			CSharp_StatementBlock elseBlock = new CSharp_StatementBlock();
			elseBlock.statements = new TokenList<CSharp_StatementOrComment>();
			ifStmt.elseClause.elseStatement = new CSharp_Statement();
			ifStmt.elseClause.elseStatement.setWhich(elseBlock);
			for (AbstractStatement stmt : elseStatements)
			{
				CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
				stmtOrComment.setWhich((CSharp_Statement) stmt);
				elseBlock.statements.addToken(stmtOrComment);
			}
		}

		ifStmt.setTransformationSource(source);
		return ifStmt;
	}
}
