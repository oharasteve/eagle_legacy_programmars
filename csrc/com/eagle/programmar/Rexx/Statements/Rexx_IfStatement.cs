// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using IfCondMetrics = com.eagle.metrics.IfCondMetrics;
	using Rexx_Statement = com.eagle.programmar.Rexx.Rexx_Element.Rexx_Statement;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Comment = com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
	using Rexx_EndOfLine = com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
	using Rexx_Keyword = com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_IfStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("instructions-if") com.eagle.programmar.Rexx.Terminals.Rexx_Keyword IF = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("IF");
		public @DOC("instructions-if") Rexx_Keyword IF = new Rexx_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Rexx_Expression condition;
		public Rexx_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword THEN = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("THEN");
		public Rexx_Keyword THEN = new Rexx_Keyword("THEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln;
		public Rexx_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Rexx.Rexx_Element.Rexx_Statement thenStatement;
		public Rexx_Statement thenStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Rexx_IfElseClause elseClause;
		public @OPT Rexx_IfElseClause elseClause;

		public static class Rexx_IfElseClause extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.Rexx.Terminals.Rexx_Comment> comments;
			public @OPT TokenList<Rexx_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln1;
			public Rexx_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rexx.Terminals.Rexx_Keyword ELSE = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("ELSE");
			public Rexx_Keyword ELSE = new Rexx_Keyword("ELSE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine eoln2;
			public Rexx_EndOfLine eoln2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Rexx.Rexx_Element.Rexx_Statement elseStatement;
			public Rexx_Statement elseStatement;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArrayList<com.eagle.metrics.IfCondMetrics> _metrics = null;
		private List<IfCondMetrics> _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				// Had to delay to make sure line number etc are all set
				_metrics = new List<IfCondMetrics>();
				_metrics.add(new IfCondMetrics(interpreter._metrics, IF));
			}

			bool cond = interpreter.getBoolValue(condition);
			_metrics.get(0).completedIf(cond);

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			if (cond)
			{
				result = interpreter.tryToInterpret(thenStatement);
			}
			else if (elseClause != null && elseClause.isPresent())
			{
				result = interpreter.tryToInterpret(elseClause.elseStatement);
			}
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression cond = transformer.transformExpression(generator, condition);
			List<AbstractStatement> ifTrue = new List<AbstractStatement>();
			List<AbstractStatement> ifFalse = new List<AbstractStatement>();

			List<AbstractStatement> stmts = transformer.transformStatement(generator, this.thenStatement.getWhich());
			if (stmts != null)
			{
				foreach (AbstractStatement stmt in stmts)
				{
					ifTrue.Add(stmt);
				}
			}

			if (this.elseClause != null && this.elseClause.isPresent())
			{
				foreach (AbstractStatement stmt in transformer.transformStatement(generator, this.elseClause.elseStatement.getWhich()))
				{
					ifFalse.Add(stmt);
				}
			}

			AbstractStatement stmt = generator.newIfStatement(cond, ifTrue, ifFalse, this);
			return stmt;
		}
	}

}
