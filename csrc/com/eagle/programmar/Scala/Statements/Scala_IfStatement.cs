// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Statement = com.eagle.programmar.Scala.Scala_Statement;
	using Scala_EOLN = com.eagle.programmar.Scala.Terminals.Scala_EOLN;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("taste-control-structures.html#ifelse") com.eagle.programmar.Scala.Terminals.Scala_Keyword IF = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("if");
		public @DOC("taste-control-structures.html#ifelse") Scala_Keyword IF = new Scala_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Expression condition;
		public Scala_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Scala.Scala_Statement thenStatement;
		public Scala_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Scala_IfElseClause elseClause;
		public @OPT Scala_IfElseClause elseClause;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public static class Scala_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Terminals.Scala_Keyword ELSE = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("else");
			public Scala_Keyword ELSE = new Scala_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Scala_EOLN eoln;
			public @OPT Scala_EOLN eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Statement elseStatement;
			public Scala_Statement elseStatement;
		}

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Scala_Statement todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.add(new IfCondMetrics(interpreter._metrics, elseClause.ELSE));
				}
			}

			bool cond = interpreter.getBoolValue(condition);
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

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			List<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
			if (stmts != null)
			{
				foreach (AbstractStatement stmt in stmts)
				{
					ifTrue.Add(stmt);
				}
			}

			if (elseClause != null && elseClause.isPresent())
			{
				foreach (AbstractStatement stmt in transformer.transformStatement(generator, elseClause.elseStatement.getWhich()))
				{
					ifFalse.Add(stmt);
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
