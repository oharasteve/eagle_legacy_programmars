// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Statement = com.eagle.programmar.Ruby.Ruby_Statement;
	using Ruby_Variable = com.eagle.programmar.Ruby.Ruby_Variable;
	using Ruby_RangeExpression = com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
	using Ruby_DownToMethod = com.eagle.programmar.Ruby.Functions.Ruby_DownToMethod;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_Keyword = com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ruby_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("control_expressions_rdoc.html#label-for+Loop") com.eagle.programmar.Ruby.Terminals.Ruby_Keyword FOR = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("for");
		public @DOC("control_expressions_rdoc.html#label-for+Loop") Ruby_Keyword FOR = new Ruby_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Ruby_Variable var;
		public Ruby_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword IN = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("in");
		public Ruby_Keyword IN = new Ruby_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ruby.Ruby_Expression values;
		public Ruby_Expression values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln1;
		public Ruby_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.Ruby.Ruby_Statement> statements;
		public TokenList<Ruby_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword END = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("end");
		public Ruby_Keyword END = new Ruby_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln2;
		public Ruby_EOLN eoln2;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			AbstractToken which = values.getWhich();
			bool backwards = false;
			int start = 0;
			int stop = 0;
			if (which is Ruby_RangeExpression)
			{
				Ruby_RangeExpression range = (Ruby_RangeExpression) which;
				start = interpreter.getIntValue(range.left);
				stop = interpreter.getIntValue(range.right);
			}
			else if (which is Ruby_DownToMethod)
			{
				// Could look like this: (3).downto(1)
				Ruby_DownToMethod reversed = (Ruby_DownToMethod) which;
				start = interpreter.getIntValue(reversed.init);
				stop = interpreter.getIntValue(reversed.stop);
				backwards = true;
			}
			else
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

			int i = start;
			while (true)
			{
				if (!backwards && i > stop)
				{
					break;
				}
				if (backwards && i < stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

				foreach (Ruby_Statement statement in statements._elements)
				{
					result = interpreter.tryToInterpret(statement);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				if (result == Eagle_Statement_Result.BREAK)
				{
					metric.broke();
					result = Eagle_Statement_Result.NORMAL;
					break;
				}
				else if (result == Eagle_Statement_Result.CONTINUE)
				{
					metric.continued();
					result = Eagle_Statement_Result.NORMAL;
				}
				else if (result == Eagle_Statement_Result.RETURN)
				{
					break;
				}

				if (backwards)
				{
					i--;
				}
				else
				{
					i++;
				}
			}

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = values.getWhich();
			Ruby_RangeExpression range = null;
			AbstractExpression initExpr = null;
			AbstractExpression termExpr = null;
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
			if (which is Ruby_RangeExpression)
			{
				range = (Ruby_RangeExpression) which;
				initExpr = transformer.transformExpression(generator, range.left);
				termExpr = transformer.transformExpression(generator, range.right);
			}
			else if (which is Ruby_DownToMethod)
			{
				Ruby_DownToMethod reversed = (Ruby_DownToMethod) which;
				initExpr = transformer.transformExpression(generator, reversed.init);
				termExpr = transformer.transformExpression(generator, reversed.stop);
				incrExpr = generator.newNumberExpression("-1", null);
				relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
			}
			else
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			foreach (Ruby_Statement statement in statements._elements)
			{
				List<AbstractStatement> newStmts = transformer.transformStatement(generator, statement.getWhich());
				if (newStmts != null)
				{
					foreach (AbstractStatement stmt in newStmts)
					{
						actionList.Add(stmt);
					}
				}
			}

			AbstractVariable varName = generator.newVariable(var.vars.first().getValue());
			return generator.newForRangeStatement(varName, null, initExpr, relOp, termExpr, incrExpr, actionList, this);
		}
	}

}
