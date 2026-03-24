// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult,
		EagleTransformableStatement
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#83-the-if-statement") Powershell_Keyword IF = new Powershell_Keyword(
			"If");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Powershell_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT Powershell_EndOfLine eoln1;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT Powershell_EndOfLine eoln2;
	public @S(80) @OPT TokenList<Powershell_Element> statements;
	public @S(90) PunctuationRightBrace rightBrace;
	public @S(100) @OPT Powershell_EndOfLine eoln3;
	public @S(110) @OPT TokenList<Powershell_IfElseIfStatement> elseIfStmts;
	public @S(120) @OPT Powershell_IfElseStatement elseStmt;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Powershell_IfElseIfStatement extends TokenSequence
	{
		public @S(10) Powershell_Keyword ELSEIF = new Powershell_Keyword("ElseIf");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Powershell_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) PunctuationLeftBrace leftBrace;
		public @S(60) @OPT Powershell_EndOfLine eoln;
		public @S(70) @OPT TokenList<Powershell_Element> statements;
		public @S(80) PunctuationRightBrace rightBrace;
		public @S(90) @OPT Powershell_EndOfLine eoln2;
	}

	public static class Powershell_IfElseStatement extends TokenSequence
	{
		public @S(10) Powershell_Keyword ELSE = new Powershell_Keyword("Else");
		public @S(20) PunctuationLeftBrace leftBrace;
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) @OPT TokenList<Powershell_Element> statements;
		public @S(50) PunctuationRightBrace rightBrace;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		TokenList<Powershell_Element> todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

			if (elseIfStmts != null)
			{
				for (Powershell_IfElseIfStatement elif : elseIfStmts._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSEIF));
				}
			}

			if (elseStmt != null && elseStmt.isPresent())
			{
				_metrics.add(new IfCondMetrics(interpreter._metrics, elseStmt.ELSE));
			}
		}

		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);
		if (cond1)
		{
			todo = statements;
		}
		else
		{
			int seq = 1;
			// Check for each 'else if'
			if (elseIfStmts != null)
			{
				for (Powershell_IfElseIfStatement elif : elseIfStmts._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.statements;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (elseStmt != null && elseStmt.isPresent())
				{
					_metrics.get(seq).completedIf(true);
					todo = elseStmt.statements;
				}
			}
		}

		if (todo != null)
		{
			result = Eagle_Statement_Result.NORMAL;
			for (Powershell_Element stmt : todo._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression newCond = transformer.transformExpression(generator, condition);

		if (elseIfStmts != null && elseIfStmts.size() > 0)
		{
			throw new RuntimeException("if/elif is not yet implemented in Powershell");
		}

		ArrayList<AbstractStatement> thenParts = new ArrayList<AbstractStatement>();
		for (Powershell_Element stmt1 : statements._elements)
		{
			for (AbstractStatement stmt2 : transformer.transformStatement(generator,
					stmt1.element.getWhich()))
			{
				thenParts.add(stmt2);
			}
		}

		ArrayList<AbstractStatement> elseParts = null;
		if (elseStmt != null && elseStmt.isPresent())
		{
			elseParts = new ArrayList<AbstractStatement>();
			for (Powershell_Element stmt3 : elseStmt.statements._elements)
			{
				for (AbstractStatement stmt4 : transformer.transformStatement(generator,
						stmt3.element.getWhich()))
				{
					elseParts.add(stmt4);
				}
			}
		}

		return generator.newIfStatement(newCond, thenParts, elseParts, this);
	}
}
