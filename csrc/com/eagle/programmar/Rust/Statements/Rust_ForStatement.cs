// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleRange = com.eagle.math.EagleRange;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Statement = com.eagle.programmar.Rust.Rust_Statement;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using Rust_AssignmentExpression = com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
	using Rust_NotExpression = com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
	using Rust_ParenthesizedExpression = com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
	using Rust_RangeExpression = com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
	using Rust_RangeModifier = com.eagle.programmar.Rust.Expressions.Rust_RangeExpression.Rust_RangeModifier;
	using Rust_RangeReverse = com.eagle.programmar.Rust.Expressions.Rust_RangeExpression.Rust_RangeReverse;
	using Rust_RangeStepBy = com.eagle.programmar.Rust.Expressions.Rust_RangeExpression.Rust_RangeStepBy;
	using Rust_RelationalExpression = com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
	using Rust_VariableExpression = com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
	using Rust_RevMethod = com.eagle.programmar.Rust.Functions.Rust_RevMethod;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_Number = com.eagle.programmar.Rust.Terminals.Rust_Number;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_ForStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("expressions/loop-expr.html#iterator-loops") @NEWLINE Rust_Keyword FOR = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("for");
		public @DOC("expressions/loop-expr.html#iterator-loops") Rust_Keyword FOR = new Rust_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Rust_Variable variable;
		public Rust_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Terminals.Rust_Keyword IN = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("in");
		public Rust_Keyword IN = new Rust_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Rust.Rust_Expression values;
		public Rust_Expression values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Rust.Rust_Statement statement;
		public Rust_Statement statement;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			EagleRange range = interpreter.getRangeValue(values);
			int start = range._lowValue;
			int stop = range._highValue;
			int step = range._step;

			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			int i = start;
			bool backwards = false;
			if (step < 0)
			{
				// Careful!
				// 1..4 does 1, 2, 3
				// (1..4).rev() does 3, 2, 1
				backwards = true;
				i = stop + step; // Careful!
			}

			while (true)
			{
				if (backwards && i < start)
				{
					break;
				}
				if (!backwards && i >= stop)
				{
					break;
				}

				metric.iterate();
				interpreter.setSymbol(variable, variable.var.ToString(), new EagleInteger(i));

				result = interpreter.tryToInterpret(statement);

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

				i += step; // Might be negative
			}

			_metrics.competedLoop(metric, backwards);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = values.getWhich();
			Rust_RangeExpression range = null;
			AbstractExpression initExpr = null;
			AbstractExpression termExpr = null;
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_THAN;
			if (which is Rust_RangeExpression)
			{
				range = (Rust_RangeExpression) which;
				initExpr = transformer.transformExpression(generator, range.lowExpression);
				termExpr = transformer.transformExpression(generator, range.highExpression);
			}
			if (which is Rust_RevMethod)
			{
				Rust_RevMethod reversed = (Rust_RevMethod) which;
				if (reversed.left.getWhich() is Rust_ParenthesizedExpression)
				{
					Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) reversed.left.getWhich();
					if (parens.expressions.first().getWhich() is Rust_RangeExpression)
					{
						range = (Rust_RangeExpression) parens.expressions.first().getWhich();
						initExpr = transformer.transformExpression(generator, range.highExpression);
						AbstractExpression oneExpr = generator.newNumberExpression("1", null);
						initExpr = generator.newAdditiveExpression(null, initExpr, EagleGenerator.AdditiveEnum.MINUS, oneExpr, null);
						termExpr = transformer.transformExpression(generator, range.lowExpression);
						incrExpr = generator.newNumberExpression("-1", null);
						relOp = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					}
				}
			}
			if (range == null)
			{
				throw new Exception("FOR statement requires a Range of values, not " + which);
			}

			List<AbstractStatement> newStmts = Rust_Block_Statement.collectStatements(transformer, generator, statement);
			List<AbstractStatement> actionList = new List<AbstractStatement>();
			if (newStmts != null)
			{
				foreach (AbstractStatement stmt in newStmts)
				{
					actionList.Add(stmt);
				}
			}

			AbstractVariable var = generator.newVariable(variable.var.getValue());
			return generator.newForRangeStatement(var, EagleGenerator.TypeEnum.INTEGER, initExpr, relOp, termExpr, incrExpr, actionList, this);
		}

		public static Rust_Statement generateForLoopOne(Rust_Expression initExpression, Rust_Expression condExpression, Rust_Expression incrExpression, Rust_Statement action, AbstractToken source)
		{
			List<Rust_Statement> actions = new List<Rust_Statement>();
			actions.Add(action);
			return generateForLoopMany(initExpression, condExpression, incrExpression, actions, source);
		}

		public static Rust_Statement generateForLoopMany(Rust_Expression initExpression, Rust_Expression condExpression, Rust_Expression incrExpression, List<Rust_Statement> actions, AbstractToken source)
		{
			// Condition might be "! (x < 10)" or something. Oof.
			Rust_Expression condition = condExpression;
			if (condExpression.getWhich() is Rust_NotExpression)
			{
				Rust_NotExpression notExpr = (Rust_NotExpression) condExpression.getWhich();
				if (notExpr.expr.getWhich() is Rust_ParenthesizedExpression)
				{
					Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) notExpr.expr.getWhich();
					if (parens.expressions.first().getWhich() is Rust_RelationalExpression)
					{
						// Ok, it matches, now reverse the relational operator. '<' becomes '>=' etc
						Rust_RelationalExpression rel = (Rust_RelationalExpression) parens.expressions.first().getWhich();
						string punct = rel.@operator.getValue();
						Rust_PunctuationChoice newPunct = null;
						switch (punct)
						{
						case "<":
							newPunct = new Rust_PunctuationChoice(">=");
							break;
						case "<=":
							newPunct = new Rust_PunctuationChoice(">");
							break;
						case "==":
							newPunct = new Rust_PunctuationChoice("!=");
							break;
						case "!=", "<>":
							newPunct = new Rust_PunctuationChoice("==");
							break;
						case ">=":
							newPunct = new Rust_PunctuationChoice("<");
							break;
						case ">":
							newPunct = new Rust_PunctuationChoice("<=");
							break;
						}
						// Switch to this new inverted condition
						rel.@operator = newPunct;
						condition = Rust_Generator.wrapExpression(rel);
					}
				}
			}

			// Let's just deal with easy case: for (i=0; i<10; i++) etc.
			if (!(initExpression.getWhich() is Rust_AssignmentExpression) || !(incrExpression.getWhich() is Rust_AssignmentExpression))
			{
				throw new Exception("For loops init and incr must be assignments");
			}

			AbstractToken whichCond = condition.getWhich();
			if (whichCond is Rust_NotExpression)
			{
				Rust_NotExpression notExpr = (Rust_NotExpression) whichCond;
				whichCond = notExpr.expr.getWhich();
			}

			if (whichCond is Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression parenExpr = (Rust_ParenthesizedExpression) whichCond;
				whichCond = parenExpr.expressions.first().getWhich();
			}

			if (!(whichCond is Rust_RelationalExpression))
			{
				throw new Exception("For loop test must be a relational expression");
			}
			Rust_RelationalExpression cond = (Rust_RelationalExpression) whichCond;

			Rust_AssignmentExpression init = (Rust_AssignmentExpression) initExpression.getWhich();
			if (!init.@operator.getValue().Equals("=") || !(init.var.getWhich() is Rust_VariableExpression))
			{
				throw new Exception("Assignment part too complicated for now.");
			}

			Rust_AssignmentExpression incr = (Rust_AssignmentExpression) incrExpression.getWhich();
			if (!(incr.var.getWhich() is Rust_VariableExpression) || !(incr.expr.getWhich() is Rust_Number))
			{
				throw new Exception("Increment part too complicated for now.");
			}
			string incrOper = incr.@operator.getValue();
			Rust_Number number = (Rust_Number) incr.expr.getWhich();
			int delta = int.Parse(number.getValue());
			switch (incrOper)
			{
			case "+=":
				break;
			case "-=":
				delta = -delta;
				break;
			default:
				throw new Exception("Unexpected operator: " + incrOper);
			}

			if (!(cond.left.getWhich() is Rust_VariableExpression))
			{
				throw new Exception("Condition part too complicated for now.");
			}
			Rust_PunctuationChoice condOper = cond.@operator;
			EagleGenerator.RelationalEnum relOper;
			switch (condOper.getValue())
			{
			case "=":
				relOper = EagleGenerator.RelationalEnum.EQUALS;
				break;
			case "<>", "!=":
				relOper = EagleGenerator.RelationalEnum.NOT_EQUALS;
				break;
			case "<":
				relOper = EagleGenerator.RelationalEnum.LESS_THAN;
				break;
			case "<=":
				relOper = EagleGenerator.RelationalEnum.LESS_EQUALS;
				break;
			case ">=":
				relOper = EagleGenerator.RelationalEnum.GREATER_EQUALS;
				break;
			case ">":
				relOper = EagleGenerator.RelationalEnum.GREATER_THAN;
				break;
			default:
				throw new Exception("Unexpected operator: " + condOper.getValue());
			}

			Rust_VariableExpression initVarExp = (Rust_VariableExpression) init.var.getWhich();
			Rust_VariableExpression condVarExp = (Rust_VariableExpression) cond.left.getWhich();
			Rust_VariableExpression incrVarExp = (Rust_VariableExpression) incr.var.getWhich();

			Rust_Identifier_Reference initId = initVarExp.variable.var;
			Rust_Identifier_Reference condId = condVarExp.variable.var;
			Rust_Identifier_Reference incrId = incrVarExp.variable.var;
			string id = initId.getValue();
			if (!condId.getValue().Equals(id) || !incrId.getValue().Equals(id))
			{
				throw new Exception("Must use the same variable in all parts");
			}

			// Ok, made it through the gauntlet ....
			Rust_Number numb = Rust_Number.createNumber(delta);
			Rust_Expression deltaExp = new Rust_Expression();
			deltaExp.setWhich(numb);
			return generateForRangeMany(initVarExp.variable, EagleGenerator.TypeEnum.INTEGER, init.expr, relOper, cond.right, deltaExp, actions, source);
		}

		public static Rust_Statement generateForRangeOne(Rust_Variable var, EagleGenerator.TypeEnum type, Rust_Expression fromExpression, EagleGenerator.RelationalEnum relOp, Rust_Expression toExpression, Rust_Expression delta, Rust_Statement action, AbstractToken source)
		{
			List<Rust_Statement> actions = new List<Rust_Statement>();
			actions.Add(action);
			return generateForRangeMany(var, EagleGenerator.TypeEnum.INTEGER, fromExpression, relOp, toExpression, delta, actions, source);
		}

		public static Rust_Statement generateForRangeMany(Rust_Variable var, EagleGenerator.TypeEnum type, Rust_Expression fromExpression, EagleGenerator.RelationalEnum relOper, Rust_Expression toExpression, Rust_Expression delta, List<Rust_Statement> actions, AbstractToken source)
		{
			Rust_ForStatement forStmt = new Rust_ForStatement();

			forStmt.variable = var;

			Rust_RangeExpression range = new Rust_RangeExpression();
			range.lowExpression = fromExpression;
			range.highExpression = toExpression;
			range.dots.setValue("..");

			int incr = 1;
			if (delta != null)
			{
				if (!(delta.getWhich() is Rust_Number))
				{
					throw new Exception("For loop increment must be a constant");
				}
				Rust_Number num = (Rust_Number) delta.getWhich();
				incr = int.Parse(num.getValue());
			}

			// range.rev().stepby(n) and range.stepby(n).rev() can be different
			// For now, we don't allow both modifiers

			if (incr != 1)
			{
				range.modifiers = new TokenList<Rust_RangeExpression.Rust_RangeModifier>();
			}

			if (incr == -1)
			{
				Rust_RangeExpression.Rust_RangeReverse rev = new Rust_RangeExpression.Rust_RangeReverse();
				rev.dot = new PunctuationPeriod();
				rev.leftParen = new PunctuationLeftParen();
				rev.rightParen = new PunctuationRightParen();

				Rust_RangeExpression.Rust_RangeModifier revmod = new Rust_RangeExpression.Rust_RangeModifier();
				revmod.setWhich(rev);

				range.modifiers.addToken(revmod);
			}
			else if (incr > 1)
			{
				Rust_RangeExpression.Rust_RangeStepBy step = new Rust_RangeExpression.Rust_RangeStepBy();
				step.dot = new PunctuationPeriod();
				step.leftParen = new PunctuationLeftParen();
				step.step = delta;

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================
