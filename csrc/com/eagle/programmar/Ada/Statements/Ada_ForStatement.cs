// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Label = com.eagle.programmar.Ada.Ada_Label;
	using Ada_Statement = com.eagle.programmar.Ada.Ada_Statement;
	using Ada_Variable = com.eagle.programmar.Ada.Ada_Variable;
	using Ada_RangeExpression = com.eagle.programmar.Ada.Expressions.Ada_RangeExpression;
	using Ada_Label_Reference = com.eagle.programmar.Ada.Symbols.Ada_Label_Reference;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Ada_Label label1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_Keyword FOR = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("for");
		public Ada_Keyword FOR = new Ada_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Variable var;
		public Ada_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ada.Terminals.Ada_Keyword IN = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("in");
		public Ada_Keyword IN = new Ada_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Ada_Keyword INTEGER = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("Integer");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Ada_Keyword RANGE = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("range");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Ada_Keyword REVERSE = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("reverse");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Ada.Ada_Expression values;
		public Ada_Expression values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Ada.Terminals.Ada_Keyword LOOP = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("loop");
		public Ada_Keyword LOOP = new Ada_Keyword("loop");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.TokenList<com.eagle.programmar.Ada.Ada_Statement> statements;
		public TokenList<Ada_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.Ada.Terminals.Ada_Keyword END = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("end");
		public Ada_Keyword END = new Ada_Keyword("end");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.Ada.Terminals.Ada_Keyword LOOP2 = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("loop");
		public Ada_Keyword LOOP2 = new Ada_Keyword("loop");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT Ada_Label_Reference label2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(140) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (!(values.getWhich() is Ada_RangeExpression))
			{
				throw new Exception("FOR statement requires a Range of values");
			}
			Ada_RangeExpression range = (Ada_RangeExpression) values.getWhich();
			int start = interpreter.getIntValue(range.left);
			int stop = interpreter.getIntValue(range.right);

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			bool backwards = REVERSE.isPresent();

			int i = start;
			if (backwards)
			{
				i = stop;
			}
			while (true)
			{
				if (!backwards && i > stop)
				{
					break;
				}
				if (backwards && i < start)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

				foreach (Ada_Statement stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
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

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			bool reversed = false;
			if (REVERSE != null && REVERSE.isPresent())
			{
				reversed = true;
			}

			if (!(values.getWhich() is Ada_RangeExpression))
			{
				throw new Exception("FOR statement requires a Range of values");
			}
			Ada_RangeExpression range = (Ada_RangeExpression) values.getWhich();
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_EQUALS;
			AbstractExpression initExpr = null;
			AbstractExpression termExpr = null;
			AbstractExpression incrExpr = null;

			if (reversed)
			{
				initExpr = transformer.transformExpression(generator, range.right);
				termExpr = transformer.transformExpression(generator, range.left);
				incrExpr = generator.newNumberExpression("-1", null);
				relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
			}
			else
			{
				initExpr = transformer.transformExpression(generator, range.left);
				termExpr = transformer.transformExpression(generator, range.right);
			}

			List<AbstractStatement> actionList = new List<AbstractStatement>();
			foreach (Ada_Statement statement in statements._elements)
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
