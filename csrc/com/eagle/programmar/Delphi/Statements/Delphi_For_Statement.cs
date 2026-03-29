// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleInteger = com.eagle.math.EagleInteger;
	using ForLoopMetric = com.eagle.metrics.ForLoopMetric;
	using ForLoopMetrics = com.eagle.metrics.ForLoopMetrics;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Statement = com.eagle.programmar.Delphi.Delphi_Statement;
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using Delphi_KeywordChoice = com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using IncrementEnum = com.eagle.transform.EagleGenerator.IncrementEnum;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_For_Statement : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Declarations_and_Statements_(Delphi)#For_Statements") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword FOR = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("For");
		public @DOC("Declarations_and_Statements_(Delphi)#For_Statements") Delphi_Keyword FOR = new Delphi_Keyword("For");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference var;
		public Delphi_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation colonEquals = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation(":=");
		public Delphi_Punctuation colonEquals = new Delphi_Punctuation(":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Delphi.Delphi_Expression from;
		public Delphi_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice TO_DOWNTO = new com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice("To", "DownTo");
		public Delphi_KeywordChoice TO_DOWNTO = new Delphi_KeywordChoice("To", "DownTo");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Delphi.Delphi_Expression to;
		public Delphi_Expression to;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword DO = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Do");
		public Delphi_Keyword DO = new Delphi_Keyword("Do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments;
		public @OPT TokenList<Delphi_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Delphi.Delphi_Statement stmt;
		public Delphi_Statement stmt;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ForLoopMetrics _metrics = null;
		private ForLoopMetrics _metrics = null;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
			}
			ForLoopMetric metric = new ForLoopMetric();

			int current = interpreter.getIntValue(from);
			int stop = interpreter.getIntValue(to);
			bool reverse = TO_DOWNTO.getValue().Equals("DownTo");

			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			while (true)
			{
				if (reverse)
				{
					if (current < stop)
					{
						break;
					}
				}
				else
				{
					if (current > stop)
					{
						break;
					}
				}

				metric.iterate();
				interpreter.setSymbol(this, var.getValue(), new EagleInteger(current));

				result = interpreter.tryToInterpret(stmt);
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

				if (reverse)
				{
					current--;
				}
				else
				{
					current++;
				}
			}

			_metrics.competedLoop(metric, reverse);
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression fromExpr = transformer.transformExpression(generator, this.from);
			AbstractExpression toExpr = transformer.transformExpression(generator, this.to);
			string varName = this.var.getValue();
			AbstractExpression varExpr = generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, null);
			AbstractStatement newAction = transformer.transformStatement1(generator, this.stmt);
			AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, fromExpr, null);

			string toDownto = this.TO_DOWNTO.getValue();
			AbstractExpression delta;
			AbstractExpression term;
			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
			switch (this.TO_DOWNTO.getValue().ToLower())
			{
			case "to":
				delta = generator.newPostIncrementExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.IncrementEnum.INCREMENT, null);
				term = generator.newRelationalExpression(types, varExpr, EagleGenerator.RelationalEnum.LESS_EQUALS, toExpr, null);
				break;
			case "downto":
				delta = generator.newPostIncrementExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.IncrementEnum.DECREMENT, null);
				term = generator.newRelationalExpression(types, varExpr, EagleGenerator.RelationalEnum.GREATER_EQUALS, toExpr, null);
				break;
			default:
				throw new Exception("Expected TO or DOWNTO, not " + toDownto);
			}

			return generator.newForLoopStatement1(asgExpr, term, delta, newAction, this);
		}
	}

}
