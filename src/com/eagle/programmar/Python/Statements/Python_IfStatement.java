// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Python_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("compound_stmts.html#the-if-statement") @NOSPACE Python_Keyword IF = new Python_Keyword("if");
	public @S(20) Python_Expression condition;
	public @S(30) @NOSPACE PunctuationColon colon;
	public @S(40) @PYDENT Python_StatementBlock ifThenStatements;
	public @S(50) @OPT TokenList<Python_IfElif> ifElif;
	public @S(60) @OPT Python_IfElse ifElse;
	public @S(70) @OPT TokenList<Python_Comment> comments;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Python_IfElif extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) @NOSPACE Python_Keyword ELIF = new Python_Keyword("elif");
		public @S(40) Python_Expression condition;
		public @S(50) @NOSPACE PunctuationColon colon;
		public @S(60) @PYDENT Python_StatementBlock elifStatements;
	}

	public static class Python_IfElse extends TokenSequence
	{
		public @S(10) @OPT Python_EndOfLine eoln;
		public @S(20) @NEWLINE Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
		public @S(30) @NOSPACE Python_Keyword ELSE = new Python_Keyword("else");
		public @S(40) @NOSPACE PunctuationColon colon;
		public @S(50) @PYDENT Python_StatementBlock ifElseStatements;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Python_StatementBlock todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

			if (ifElif != null)
			{
				for (Python_IfElif elif : ifElif._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
				}
			}

			if (ifElse != null && ifElse.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, ifElse.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = ifThenStatements;
		}
		else
		{
			int seq = 1;
			// Check for each 'else if'
			if (ifElif != null)
			{
				for (Python_IfElif elif : ifElif._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.elifStatements;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (ifElse != null && ifElse.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = ifElse.ifElseStatements;
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

		ArrayList<AbstractStatement> thenParts = transformer.transformStatement(generator,
				ifThenStatements);

		ArrayList<AbstractStatement> elseParts = null;
		if (ifElse != null && ifElse.isPresent())
		{
			elseParts = transformer.transformStatement(generator,
					ifElse.ifElseStatements);
		}

		// Might need some "elif" blocks
		ArrayList<AbstractExpression> elseIfConds = null;
		ArrayList<ArrayList<AbstractStatement>> elseIfParts = null;
		if (ifElif != null && ifElif.size() > 0)
		{
			elseIfConds = new ArrayList<AbstractExpression>();
			elseIfParts = new ArrayList<ArrayList<AbstractStatement>>();
			for (Python_IfElif next : ifElif._elements)
			{
				elseIfConds.add(transformer.transformExpression(generator,
						next.condition));
				elseIfParts.add(transformer.transformStatement(generator,
						next.elifStatements));
			}
		}
		
		return generator.newIfElseIfStatement(cond, thenParts,
				elseIfConds, elseIfParts, elseParts, this);
	}

	public static Python_ComplexStatement generateIfElseOne(Python_Expression cond,
			Python_ComplexStatement thenStmt, Python_ComplexStatement elseStmt, AbstractToken source)
	{
		ArrayList<Python_ComplexStatement> thens = new ArrayList<Python_ComplexStatement>();
		thens.add(thenStmt);

		ArrayList<Python_ComplexStatement> elses = null;
		if (elseStmt != null)
		{
			elses = new ArrayList<Python_ComplexStatement>();
			elses.add(elseStmt);
		}

		return generateIfElseMany(cond, thens, elses, source);
	}

	public static Python_ComplexStatement generateIfElseMany(Python_Expression cond,
			ArrayList<Python_ComplexStatement> thenStmts,
			ArrayList<Python_ComplexStatement> elseStmts, AbstractToken source)
	{
		Python_IfStatement ifStmt = genIf(cond, thenStmts, elseStmts, source);
		return Python_Generator.wrapStatement(ifStmt);
	}

	private static Python_IfStatement genIf(Python_Expression cond,
			ArrayList<Python_ComplexStatement> thenStmts, ArrayList<Python_ComplexStatement> elseStmts,
			AbstractToken source)
	{
		Python_IfStatement ifStmt = new Python_IfStatement();
		ifStmt.condition = cond;
		ifStmt.colon = new PunctuationColon();

		ifStmt.ifThenStatements = new Python_StatementBlock();
		Python_MultilineStatement thenMulti = new Python_MultilineStatement();
		ifStmt.ifThenStatements.setWhich(thenMulti);
		thenMulti.statements = new TokenList<Python_ComplexStatement>();
		for (Python_ComplexStatement stmt : thenStmts)
		{
			thenMulti.statements.addToken(stmt);
		}

		if (elseStmts != null && elseStmts.size() > 0)
		{
			ifStmt.ifElse = new Python_IfElse();
			ifStmt.ifElse.setPresent(true);
			ifStmt.ifElse.colon = new PunctuationColon();
			ifStmt.ifElse.ifElseStatements = new Python_StatementBlock();
			Python_MultilineStatement elseMulti = new Python_MultilineStatement();
			ifStmt.ifElse.ifElseStatements.setWhich(elseMulti);
			elseMulti.statements = new TokenList<Python_ComplexStatement>();
			for (Python_ComplexStatement stmt : elseStmts)
			{
				elseMulti.statements.addToken(stmt);
			}
		}

		ifStmt.setTransformationSource(source);
		return ifStmt;
	}

	public static Python_ComplexStatement generateIfElseIfMany(Python_Expression cond,
			ArrayList<Python_ComplexStatement> ifTrue, ArrayList<Python_Expression> elseIfConds,
			ArrayList<ArrayList<Python_ComplexStatement>> elseIfParts,
			ArrayList<Python_ComplexStatement> ifFalse, AbstractToken source)
	{
		Python_IfStatement ifStmt =  genIf(cond, ifTrue, ifFalse, source);
		ifStmt.ifElif = new TokenList<Python_IfElif>();
		
		if (elseIfConds != null)
		{
			for (int i = 0; i < elseIfConds.size(); i++)
			{
				Python_IfElif next = new Python_IfElif();
				next.condition = elseIfConds.get(i);
				next.colon = new PunctuationColon();
				next.elifStatements = new Python_StatementBlock();
				Python_MultilineStatement elseMulti = new Python_MultilineStatement();
				elseMulti.statements = new TokenList<Python_ComplexStatement>();
				next.elifStatements.setWhich(elseMulti);
				for (Python_ComplexStatement stmt : elseIfParts.get(i))
				{
					elseMulti.statements.addToken(stmt);
				}
				ifStmt.ifElif.addToken(next);
			}
		}
		
		return Python_Generator.wrapStatement(ifStmt);
	}
}
