// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.generate.Statements.Eagle_Generate_IfElse;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Python_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
				Eagle_Generate_IfElse<Python_Statement, Python_Expression>
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
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
			
			if (ifElif != null)
			{
				for (Python_IfElif elif : ifElif._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif));
				}
			}
			
			if (ifElse != null && ifElse.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, ifElse));
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
	public Python_Statement generateIfElse1(Python_Expression cond,
			Python_Statement thenStmt, Python_Statement elseStmt, AbstractToken source)
	{
		ArrayList<Python_Statement> thens = new ArrayList<Python_Statement>();
		thens.add(thenStmt);
		
		ArrayList<Python_Statement> elses = null;
		if (elseStmt != null)
		{
			elses = new ArrayList<Python_Statement>();
			elses.add(elseStmt);
		}

		return generateIfElse(cond, thens, elses, source);
	}
	
	@Override
	public Python_Statement generateIfElse(Python_Expression cond,
			ArrayList<Python_Statement> thenStmts, ArrayList<Python_Statement> elseSmts, AbstractToken source)
	{
		this.condition = cond;
		this.colon = new PunctuationColon();

		this.ifThenStatements = new Python_StatementBlock();
		Python_MultilineStatement thenMulti = new Python_MultilineStatement();
		this.ifThenStatements.setWhich(thenMulti);
		thenMulti.statements = new TokenList<Python_Statement>();
		for (AbstractStatement stmt : thenStmts)
		{
			thenMulti.statements.addToken((Python_Statement) stmt);
		}
				
		if (elseSmts != null && elseSmts.size() > 0)
		{
			this.ifElse = new Python_IfElse();
			this.ifElse.setPresent(true);
			this.ifElse.colon = new PunctuationColon();
			this.ifElse.ifElseStatements = new Python_StatementBlock();
			Python_MultilineStatement elseMulti = new Python_MultilineStatement();
			this.ifElse.ifElseStatements.setWhich(elseMulti);
			elseMulti.statements = new TokenList<Python_Statement>();
			for (AbstractStatement stmt : elseSmts)
			{
				elseMulti.statements.addToken((Python_Statement) stmt);
			}
		}

		this.setTransformationSource(source);
		return Python_Generator.wrapStatement(this);
	}
}
