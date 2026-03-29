// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Statement = com.eagle.programmar.Ada.Ada_Statement;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
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

	public class Ada_IfStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword IF = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("if");
		public Ada_Keyword IF = new Ada_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Ada_Expression condition;
		public Ada_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Terminals.Ada_Keyword THEN = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("then");
		public Ada_Keyword THEN = new Ada_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> thenStatements;
		public TokenList<Ada_Statement> thenStatements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Ada_ElseIfClause> elseIfClauses;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Ada_IfElseClause elseClause;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ada.Terminals.Ada_Keyword END = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("end");
		public Ada_Keyword END = new Ada_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Ada.Terminals.Ada_Keyword IF2 = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("if");
		public Ada_Keyword IF2 = new Ada_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public class Ada_IfElseClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword ELSE = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("else");
			public Ada_Keyword ELSE = new Ada_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> elseStatements;
			public TokenList<Ada_Statement> elseStatements;
		}

		public class Ada_ElseIfClause : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword ELSIF = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("elsif");
			public Ada_Keyword ELSIF = new Ada_Keyword("elsif");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Ada_Expression condition;
			public Ada_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Terminals.Ada_Keyword THEN = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("then");
			public Ada_Keyword THEN = new Ada_Keyword("then");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> elseStatements;
			public TokenList<Ada_Statement> elseStatements;
		}

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			TokenList<Ada_Statement> todo = null;

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

			bool cond1 = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond1);
			if (cond1)
			{
				todo = thenStatements;
			}
			else
			{
				// Check for 'else'
				if (elseClause != null && elseClause.isPresent())
				{
					_metrics.get(1).completedIf(true);
					todo = elseClause.elseStatements;
				}
			}

			if (todo != null)
			{
				result = Eagle_Statement_Result.NORMAL;
				foreach (Ada_Statement stmt in todo._elements)
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

			foreach (Ada_Statement thenStatement in thenStatements._elements)
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

			if (elseClause != null && elseClause.isPresent())
			{
				foreach (Ada_Statement elseStatement in elseClause.elseStatements._elements)
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
