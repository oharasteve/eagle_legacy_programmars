// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Statement = com.eagle.programmar.Algol68.Algol68_Statement;
	using Algol68_Keyword = com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_IfStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword IF = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("IF");
		public Algol68_Keyword IF = new Algol68_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Expression condition;
		public Algol68_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword THEN = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("THEN");
		public Algol68_Keyword THEN = new Algol68_Keyword("THEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Algol68.Algol68_Statement> thenStatements;
		public TokenList<Algol68_Statement> thenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Algol68_IfElifClause> elifClause;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Algol68_IfElseClause elseClause;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword END = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("FI");
		public Algol68_Keyword END = new Algol68_Keyword("FI");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT PunctuationSemicolon semicolon;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public class Algol68_IfElifClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword ELIF = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("ELIF");
			public Algol68_Keyword ELIF = new Algol68_Keyword("ELIF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Expression condition;
			public Algol68_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword THEN = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("THEN");
			public Algol68_Keyword THEN = new Algol68_Keyword("THEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Algol68.Algol68_Statement> elifStatements;
			public TokenList<Algol68_Statement> elifStatements;
		}

		public class Algol68_IfElseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_Keyword ELSE = new com.eagle.programmar.Algol68.Terminals.Algol68_Keyword("ELSE");
			public Algol68_Keyword ELSE = new Algol68_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Algol68.Algol68_Statement> elseStatements;
			public TokenList<Algol68_Statement> elseStatements;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Algol68_Statement> todo = null;

			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));

				if (elifClause != null)
				{
					foreach (Algol68_IfElifClause elif in elifClause._elements)
					{
						_metrics.add(new IfCondMetrics(interpreter._metrics, elif.ELIF));
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
				todo = thenStatements;
			}
			else
			{
				int seq = 1;
				// Check for each 'else if'
				if (elifClause != null)
				{
					foreach (Algol68_IfElifClause elif in elifClause._elements)
					{
						bool cond2 = interpreter.getBoolValue(elif.condition);
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
					if (elseClause != null && elseClause.isPresent())
					{
						_metrics.get(seq).completedIf(true);
						todo = elseClause.elseStatements;
					}
				}
			}

			if (todo != null)
			{
				result = Eagle_Statement_Result.NORMAL;
				foreach (Algol68_Statement stmt in todo._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}

			return result;
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			foreach (Algol68_Statement thenStatement in thenStatements._elements)
			{
				List<AbstractStatement> stmts = transformer.transformStatement(generator, thenStatement.getWhich());
				if (stmts != null)
				{
					foreach (AbstractStatement stmt in stmts)
					{
						ifTrue.Add(stmt);
					}
				}
			}

			if (elifClause != null && elifClause.size() > 0)
			{
				throw new Exception("Can't handle Algol68 ELIF yet.");
			}

			if (elseClause != null && elseClause.isPresent())
			{
				foreach (Algol68_Statement elseStatement in elseClause.elseStatements._elements)
				{
					foreach (AbstractStatement stmt in transformer.transformStatement(generator, elseStatement.getWhich()))
					{
						ifFalse.Add(stmt);
					}
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
