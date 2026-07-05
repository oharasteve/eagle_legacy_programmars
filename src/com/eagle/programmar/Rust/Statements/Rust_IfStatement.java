// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_IfStatement extends TokenSequence
		implements EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/if-expr.html") @NEWLINE Rust_Keyword IF = new Rust_Keyword("if");
	public @S(20) Rust_Expression condition;
	public @S(30) Rust_Block_Statement thenStatement;
	public @S(40) @OPT TokenList<Rust_ElseIfClause> elseIfClauses;
	public @S(50) @OPT @NEWLINE Rust_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Rust_IfElseClause extends TokenSequence implements AbstractStatement
	{
		public @S(10) Rust_Keyword ELSE = new Rust_Keyword("else");
		public @S(20) Rust_Block_Statement elseStatement;
	}

	public static class Rust_ElseIfClause extends TokenSequence
	{
		public @S(10) Rust_Keyword ELSE = new Rust_Keyword("else");
		public @S(20) Rust_Keyword IF = new Rust_Keyword("if");
		public @S(30) Rust_Expression condition;
		public @S(40) Rust_Block_Statement elseIfStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Rust_Block_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (Rust_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseIfClause.IF));
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = thenStatement;
		}
		else
		{
			boolean matched = false;
			int seq = 1;
			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				for (Rust_ElseIfClause elseIfClause : elseIfClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elseIfClause.condition);
					_metrics.get(seq).completedIf(cond2);
					if (cond2)
					{
						todo = elseIfClause.elseIfStatement;
						matched = true;
						break;
					}
					seq++;
				}
			}
			
			if (!matched)
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = elseClause.elseStatement;
				}
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
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> ifTrue = new ArrayList<AbstractStatement>();
		ArrayList<AbstractStatement> ifFalse = new ArrayList<AbstractStatement>();

		ArrayList<AbstractStatement> stmts1 = transformer.transformStatement(generator, thenStatement);
		if (stmts1 != null)
		{
			for (AbstractStatement stmt : stmts1)
			{
				ifTrue.add(stmt);
			}
		}

		if (elseClause != null && elseClause.isPresent())
		{
			for (AbstractStatement stmt : transformer.transformStatement(generator,
					elseClause.elseStatement))
			{
				ifFalse.add(stmt);
			}
		}

		if (elseIfClauses == null || elseIfClauses.size() == 0)
		{
			return generator.newIfStatement(cond, ifTrue, ifFalse, this);
		}

		// Dang, need some "else if" blocks
		ArrayList<AbstractExpression> elseIfConds =
				new ArrayList<AbstractExpression>();
		ArrayList<ArrayList<AbstractStatement>> elseIfParts =
				new ArrayList<ArrayList<AbstractStatement>>();
		for (Rust_ElseIfClause nextElIf : elseIfClauses._elements)
		{
			elseIfConds.add(transformer.transformExpression(generator,
					nextElIf.condition));
			ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator,
					nextElIf.elseIfStatement);
			elseIfParts.add(stmts2);
		}
		return generator.newIfElseIfStatement(cond, ifTrue,
				elseIfConds, elseIfParts, ifFalse, this);
	}

	public static Rust_Statement generateIfElseOne(Rust_Expression cond,
			Rust_Statement thenStmt, Rust_Statement elseStmt, AbstractToken source)
	{
		Rust_IfStatement ifStmt = new Rust_IfStatement();
		AbstractToken which = cond.getWhich();
		if (which instanceof Rust_ParenthesizedExpression)
		{
			Rust_ParenthesizedExpression parensExpr = (Rust_ParenthesizedExpression) which;
			// Remove redundant parens
			ifStmt.condition = parensExpr.expressions.first();
		}
		else
		{
			ifStmt.condition = cond;
		}

		ifStmt.thenStatement = Rust_Block_Statement.generateBlock1(thenStmt, null);

		if (elseStmt != null)
		{
			ifStmt.elseClause = new Rust_IfElseClause();
			ifStmt.elseClause.setPresent(true);
			ifStmt.elseClause.elseStatement = Rust_Block_Statement.generateBlock1(elseStmt, null);
			ifStmt.elseClause.elseStatement.setPresent(true);
		}

		ifStmt.setTransformationSource(source);
		return Rust_Generator.wrapStatement(ifStmt);
	}

	public static Rust_Statement generateIfElseMany(Rust_Expression cond,
			ArrayList<Rust_Statement> thenStatements,
			ArrayList<Rust_Statement> elseStatements, AbstractToken source)
	{
		Rust_Statement blockTrue = Rust_Block_Statement.generateBlock(thenStatements, source);

		Rust_Statement blockElse = null;
		if (elseStatements != null && elseStatements.size() > 0)
		{
			blockElse = Rust_Block_Statement.generateBlock(elseStatements, source);
		}

		return generateIfElseOne(cond, blockTrue, blockElse, source);
	}

	public static Rust_Statement generateIfElseIfMany(Rust_Expression cond,
			ArrayList<Rust_Statement> ifTrue, ArrayList<Rust_Expression> elseIfConds,
			ArrayList<ArrayList<Rust_Statement>> elseIfParts,
			ArrayList<Rust_Statement> ifFalse, AbstractToken source)
	{
		Rust_Statement thenBlock;
		Rust_Statement currElse = null;
		if (ifFalse != null)
		{
			currElse = Rust_Block_Statement.generateBlock(ifFalse, source);
		}

		// Work from the bottom (last else) up to the top (first condition)
		if (elseIfConds != null)
		{
			int countElIfs = elseIfConds.size();
			for (int i = countElIfs-1; i >= 0; i--)
			{
				Rust_Expression currCond = elseIfConds.get(i);
				ArrayList<Rust_Statement> currThen = elseIfParts.get(i);
				thenBlock = Rust_Block_Statement.generateBlock(currThen, source);
				currElse = generateIfElseOne(currCond, thenBlock, currElse, source);
			}
		}
		
		thenBlock = Rust_Block_Statement.generateBlock(ifTrue, source);
		return generateIfElseOne(cond, thenBlock, currElse, source);
	}
}
