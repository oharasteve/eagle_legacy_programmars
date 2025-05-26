// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_IfStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("control-structures.if.php") Perl_Keyword IF = new Perl_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_IfWhat condition;
	public @S(40) @OPT TokenList<Perl_Comment> comments1;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT TokenList<Perl_Comment> comments2;
	public @S(70) Perl_Statement thenStatement;
	public @S(80) @OPT TokenList<Perl_Comment> comments3;
	public @S(90) @OPT TokenList<Perl_IfElseIfClause> elseIfClauses;
	public @S(100) @OPT TokenList<Perl_Comment> comments4;
	public @S(110) @OPT Perl_IfElseClause elseClause;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Perl_IfWhat extends TokenChooser
	{
		public @LAST Perl_Expression XXcondition;

		public @CHOICE static class Perl_IfExists extends TokenSequence
		{
			public @S(10) @OPT Perl_Keyword NOT = new Perl_Keyword("not");
			public @S(20) PunctuationHyphen minus;
			public @S(30) Perl_KeywordChoice FD = new Perl_KeywordChoice("d", "f");
			public @S(40) Perl_Expression expr;
		}
	}

	public static class Perl_IfElseIfClause extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice ELSEIF = new Perl_KeywordChoice("elseif", "elsif");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) @OPT TokenList<Perl_Comment> comments;
		public @S(60) Perl_Statement elseIfStatement;
	}

	public static class Perl_IfElseClause extends TokenSequence
	{
		public @S(10) Perl_Keyword ELSE = new Perl_Keyword("else");
		public @S(20) @OPT TokenList<Perl_Comment> comments;
		public @S(30) Perl_Statement elseStatement;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Perl_Statement todo = null;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			
			if (elseIfClauses != null)
			{
				for (Perl_IfElseIfClause elif : elseIfClauses._elements)
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSEIF));
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
			int seq = 1;
			// Check for each 'else if'
			if (elseIfClauses != null)
			{
				for (Perl_IfElseIfClause elif : elseIfClauses._elements)
				{
					boolean cond2 = interpreter.getBoolValue(elif.condition);
					_metrics.get(seq).completedIf(cond2);
					seq++;
					if (cond2)
					{
						todo = elif.elseIfStatement;
						break;
					}
				}
			}

			// Check for 'else'
			if (todo == null)
			{
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(seq).completedIf(true);
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
}
