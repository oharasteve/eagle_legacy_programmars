// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

namespace com.eagle.programmar.Python.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_ExpressionList = com.eagle.programmar.Python.Python_ExpressionList;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_VariableList = com.eagle.programmar.Python.Python_VariableList;
	using Python_Just_Var = com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
	using Python_VariableAndSubscript = com.eagle.programmar.Python.Python_VariableList.Python_VariableAndSubscript;
	using Python_VariableOrList = com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
	using Python_Additive_Expression = com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
	using Python_Assignment_Expression = com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
	using Python_Function_Call = com.eagle.programmar.Python.Expressions.Python_Function_Call;
	using Python_Logical_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
	using Python_Negative_Expression = com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_RangeExpression = com.eagle.programmar.Python.Expressions.Python_RangeExpression;
	using Python_Relational_Expression = com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_MultilineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_ElseStartOfLine = com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_ForStatement : TokenSequence, AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_Keyword ASYNC = new com.eagle.programmar.Python.Terminals.Python_Keyword("async");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("compound_stmts.html#the-for-statement") @NOSPACE Python_Keyword FOR = new com.eagle.programmar.Python.Terminals.Python_Keyword("for");
		public @DOC("compound_stmts.html#the-for-statement") Python_Keyword FOR = new Python_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Python_ForWhat what;
		public Python_ForWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Terminals.Python_Keyword IN = new com.eagle.programmar.Python.Terminals.Python_Keyword("in");
		public Python_Keyword IN = new Python_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Python.Python_ExpressionList expressionList;
		public Python_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationColon colon;
		public @NOSPACE PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Python_Comment comment;
		public @OPT Python_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @PYDENT Python_StatementBlock forBlock;
		public @PYDENT Python_StatementBlock forBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT Python_ForElse forElseStatement;
		public @OPT Python_ForElse forElseStatement;

		public static class Python_ForWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_VariableList XXvarList;
			public Python_VariableList XXvarList;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_ForList extends com.eagle.tokens.TokenSequence
			public static class Python_ForList extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_VariableList varList;
				public Python_VariableList varList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
			}
		}

		public static class Python_ForElse extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_EndOfLine eoln;
			public @OPT Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine soln = new com.eagle.programmar.Python.Terminals.Python_ElseStartOfLine();
			public Python_ElseStartOfLine soln = new Python_ElseStartOfLine();
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Terminals.Python_Keyword ELSE = new com.eagle.programmar.Python.Terminals.Python_Keyword("else");
			public Python_Keyword ELSE = new Python_Keyword("else");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Python_StatementBlock doWhat;
			public Python_StatementBlock doWhat;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Python_RangeExpression rangeExpr = null;
			if (expressionList.expressions.getPrimaryCount() == 1)
			{
				Python_Expression expr = expressionList.expressions.first();
				if (expr.getWhich() is Python_RangeExpression)
				{
					rangeExpr = (Python_RangeExpression) expr.getWhich();
				}
			}

			if (rangeExpr == null)
			{
				throw new Exception("FOR statement requires a Range of values");
			}

			int start = interpreter.getIntValue(rangeExpr.start);
			int stop = interpreter.getIntValue(rangeExpr.stop);
			int incr = 1;
			if (rangeExpr.increment != null && rangeExpr.increment.isPresent())
			{
				incr = interpreter.getIntValue(rangeExpr.increment.incr);
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
				if (incr > 0 && i >= stop)
				{
					break;
				}
				if (incr < 0 && i <= stop)
				{
					break;
				}

				Python_Variable var = null;
				string varName = "unknown";
				if (what.getWhich() is Python_VariableList)
				{
					Python_VariableList varList = (Python_VariableList) what.getWhich();
					Python_VariableList.Python_VariableOrList varOrList = varList.vars.first();
					if (varOrList.getWhich() is Python_VariableList.Python_Just_Var)
					{
						Python_VariableList.Python_Just_Var justVar = (Python_VariableList.Python_Just_Var) varOrList.getWhich();
						var = justVar.variable.first().variable;
						if (var.var.getWhich() is Python_Identifier_Reference)
						{
							Python_Identifier_Reference id = (Python_Identifier_Reference) var.var.getWhich();
							varName = id.getValue();
						}
					}
				}

				metric.iterate();
				interpreter.setSymbol(var, varName, new EagleInteger(i));

				result = interpreter.tryToInterpret(forBlock);

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

				i += incr;
			}

			_metrics.competedLoop(metric, incr < 0);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Python_RangeExpression rangeExpr = null;
			if (expressionList.expressions.getPrimaryCount() == 1)
			{
				Python_Expression expr = expressionList.expressions.first();
				if (expr.getWhich() is Python_RangeExpression)
				{
					rangeExpr = (Python_RangeExpression) expr.getWhich();
				}
			}

			if (rangeExpr == null)
			{
				throw new Exception("Python FOR statement requires a Range of values");
			}

			AbstractExpression initExpr = transformer.transformExpression(generator, rangeExpr.start);
			AbstractExpression termExpr = transformer.transformExpression(generator, rangeExpr.stop);
			AbstractExpression incrExpr = null;
			EagleGenerator.RelationalEnum relOp = EagleGenerator.RelationalEnum.LESS_THAN;
			if (rangeExpr.increment != null && rangeExpr.increment.isPresent())
			{
				incrExpr = transformer.transformExpression(generator, rangeExpr.increment.incr);
				AbstractToken whichIncr = rangeExpr.increment.incr.getWhich();
				if (whichIncr is Python_Negative_Expression)
				{
					relOp = EagleGenerator.RelationalEnum.GREATER_THAN;
				}
				else if (whichIncr is Python_Number)
				{
					Python_Number num = (Python_Number) whichIncr;
					if (num.getValue().StartsWith("-"))
					{
						relOp = EagleGenerator.RelationalEnum.GREATER_THAN;
					}
				}
			}

			List<AbstractStatement> newStmts = forBlock.transformStatement(transformer, generator);
			List<AbstractStatement> actionList = new List<AbstractStatement>();
			if (newStmts != null)
			{
				foreach (AbstractStatement stmt in newStmts)
				{
					actionList.Add(stmt);
				}
			}

			AbstractVariable newVar = null;
			if (what.getWhich() is Python_VariableList)
			{
				Python_VariableList varList = (Python_VariableList) what.getWhich();
				Python_VariableList.Python_VariableOrList varOrList = varList.vars.first();
				if (varOrList.getWhich() is Python_VariableList.Python_Just_Var)
				{
					Python_VariableList.Python_Just_Var justVar = (Python_VariableList.Python_Just_Var) varOrList.getWhich();
					Python_Variable pyVar = justVar.variable.first().variable;
					AbstractToken whichVar = pyVar.var.getWhich();
					if (whichVar is Python_Identifier_Reference)
					{
						Python_Identifier_Reference id = (Python_Identifier_Reference) whichVar;
						newVar = generator.newVariable(id.getValue());
					}
				}
			}

			return generator.newForRangeStatement(newVar, EagleGenerator.TypeEnum.VOID, initExpr, relOp, termExpr, incrExpr, actionList, this);
		}

		public static Python_ComplexStatement generateForLoopOne(Python_Expression initExpression, Python_Expression condExpression, Python_Expression incrExpression, Python_ComplexStatement action, AbstractToken source)
		{
			List<Python_ComplexStatement> actions = new List<Python_ComplexStatement>();
			actions.Add(action);
			return generateForLoopMany(initExpression, condExpression, incrExpression, actions, source);
		}

		public static Python_ComplexStatement generateForLoopMany(Python_Expression initExpression, Python_Expression condExpression, Python_Expression incrExpression, List<Python_ComplexStatement> actions, AbstractToken source)
		{
			// Condition might be "! (x < 10)" or something. Oof.
			Python_Expression condition = condExpression;
			if (condExpression.getWhich() is Python_Logical_Not_Expression)
			{
				Python_Logical_Not_Expression notExpr = (Python_Logical_Not_Expression) condExpression.getWhich();
				if (notExpr.expr.getWhich() is Python_Parenthesized_Expression)
				{
					Python_Parenthesized_Expression parens = (Python_Parenthesized_Expression) notExpr.expr.getWhich();
					if (parens.list.expr.getWhich() is Python_Relational_Expression)
					{
						// Ok, it matches, now reverse the relational operator. '<' becomes '>=' etc
						Python_Relational_Expression rel = (Python_Relational_Expression) parens.list.expr.getWhich();
						if (rel.@operator.getWhich() is Python_PunctuationChoice)
						{
							Python_PunctuationChoice punct = (Python_PunctuationChoice) rel.@operator.getWhich();
							Python_PunctuationChoice newPunct = null;
							switch (punct.getValue())
							{
							case "<":
								newPunct = new Python_PunctuationChoice(">=");
								break;
							case "<=":
								newPunct = new Python_PunctuationChoice(">");
								break;
							case "==":
								newPunct = new Python_PunctuationChoice("!=");
								break;
							case "!=", "<>":
								newPunct = new Python_PunctuationChoice("==");
								break;
							case ">=":
								newPunct = new Python_PunctuationChoice("<");
								break;
							case ">":
								newPunct = new Python_PunctuationChoice("<=");
								break;
							}
							// Switch to this new inverted condition
							rel.@operator.setWhich(newPunct);
							condition = Python_Generator.wrapExpression(rel);
						}
					}
				}
			}

			// Let's just deal with easy case: for (i=0; i<10; i++) etc.
			if (!(initExpression.getWhich() is Python_Assignment_Expression) || !(condition.getWhich() is Python_Relational_Expression) || !(incrExpression.getWhich() is Python_Assignment_Expression))
			{
				throw new Exception("Need to implement");
			}

			Python_Assignment_Expression init = (Python_Assignment_Expression) initExpression.getWhich();
			if (!init.@operator.getValue().Equals("=") || !(init.left.getWhich() is Python_VariableExpression))
			{
				throw new Exception("Assignment part too complicated for now.");
			}

			Python_Assignment_Expression incr = (Python_Assignment_Expression) incrExpression.getWhich();
			if (!(incr.left.getWhich() is Python_VariableExpression) || !(incr.right.getWhich() is Python_Number))
			{
				throw new Exception("Increment part too complicated for now.");
			}
			string incrOper = incr.@operator.getValue();
			Python_Number number = (Python_Number) incr.right.getWhich();
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

			Python_Relational_Expression cond = (Python_Relational_Expression) condition.getWhich();
			if (!(cond.@operator.getWhich() is Python_PunctuationChoice) || !(cond.left.getWhich() is Python_VariableExpression))
			{
				throw new Exception("Condition part too complicated for now.");
			}
			Python_PunctuationChoice condOper = (Python_PunctuationChoice) cond.@operator.getWhich();
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

			Python_VariableExpression initVarExp = (Python_VariableExpression) init.left.getWhich();
			Python_VariableExpression condVarExp = (Python_VariableExpression) cond.left.getWhich();

// ====================================================================================================
// End of the allowed output for the Free Edition of Java to C# Converter.

// To buy a Premium Edition license, visit our website:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================
