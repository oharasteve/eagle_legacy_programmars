// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

namespace com.eagle.programmar.Perl.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control-structures.if.php") com.eagle.programmar.Perl.Terminals.Perl_Keyword IF = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("if");
		public @DOC("control-structures.if.php") Perl_Keyword IF = new Perl_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Perl_IfWhat condition;
		public Perl_IfWhat condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments1;
		public @OPT TokenList<Perl_Comment> comments1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments2;
		public @OPT TokenList<Perl_Comment> comments2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Perl.Perl_Statement thenStatement;
		public Perl_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments3;
		public @OPT TokenList<Perl_Comment> comments3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<Perl_IfElseIfClause> elseIfClauses;
		public @OPT TokenList<Perl_IfElseIfClause> elseIfClauses;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments4;
		public @OPT TokenList<Perl_Comment> comments4;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT Perl_IfElseClause elseClause;
		public @OPT Perl_IfElseClause elseClause;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Perl_IfWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Perl_Expression XXcondition;
			public Perl_Expression XXcondition;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_IfExists extends com.eagle.tokens.TokenSequence
			public static class Perl_IfExists extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Perl_Keyword NOT = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("not");
				public @OPT Perl_Keyword NOT = new Perl_Keyword("not");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationHyphen minus;
				public PunctuationHyphen minus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice FD = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("d", "f");
				public Perl_KeywordChoice FD = new Perl_KeywordChoice("d", "f");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Perl.Perl_Expression expr;
				public Perl_Expression expr;
			}
		}

		public static class Perl_IfElseIfClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice ELSEIF = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("elseif", "elsif");
			public Perl_KeywordChoice ELSEIF = new Perl_KeywordChoice("elseif", "elsif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression condition;
			public Perl_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments;
			public @OPT TokenList<Perl_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Perl.Perl_Statement elseIfStatement;
			public Perl_Statement elseIfStatement;
		}

		public static class Perl_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword ELSE = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("else");
			public Perl_Keyword ELSE = new Perl_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Perl.Terminals.Perl_Comment> comments;
			public @OPT TokenList<Perl_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Statement elseStatement;
			public Perl_Statement elseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Perl_Statement todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (elseIfClauses != null)
				{
					foreach (Perl_IfElseIfClause elif in elseIfClauses._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELSEIF));
					}
				}

				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			bool cond1 = interpreter.getBoolValue(condition);
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
					foreach (Perl_IfElseIfClause elif in elseIfClauses._elements)
					{
						bool cond2 = interpreter.getBoolValue(elif.condition);
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

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(condition.getWhich() is Perl_Expression))
			{
				throw new Exception("Can only handle simple conditions");
			}
			Perl_Expression cond = (Perl_Expression) condition.getWhich();
			AbstractExpression newCond = transformer.transformExpression(generator, cond);

			if (elseIfClauses != null && elseIfClauses.size() > 0)
			{
				throw new Exception("if/elif is not yet implemented in Perl");
			}

			List<AbstractStatement> thenParts = transformer.transformStatement(generator, thenStatement.getWhich());

			List<AbstractStatement> elseParts = null;
			if (elseClause != null && elseClause.isPresent())
			{
				elseParts = transformer.transformStatement(generator, elseClause.elseStatement.getWhich());
			}

			return generator.newIfStatement(newCond, thenParts, elseParts, this);
		}
	}

}
