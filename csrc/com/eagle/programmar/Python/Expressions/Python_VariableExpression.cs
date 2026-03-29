// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Subscript = com.eagle.programmar.Python.Python_Subscript;
	using Python_SubscrExpr = com.eagle.programmar.Python.Python_Subscript.Python_SubscrExpr;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Variable variable;
		public Python_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE Python_Subscript subscript;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (subscript != null && subscript.isPresent())
			{
				EagleValue value = interpreter.findSymbol(variable.var.getWhich().ToString());
				Python_Subscript.evaluateSubscript(interpreter, value, subscript.body);
				return;
			}

			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = variable.var.getWhich().ToString();
			if (subscript != null && subscript.isPresent())
			{
				return Python_Subscript.transformSubscript(transformer, generator, variable, subscript.body);
			}
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, this);
		}

		public static Python_Expression generateVariableExpression(string name, EagleGenerator.SubscriptEnum offset, Python_Expression subscrExpr, AbstractToken source)
		{
			Python_VariableExpression varExp = new Python_VariableExpression();
			varExp.variable = Python_Variable.newVariable(name);

			if (subscrExpr != null)
			{
				varExp.subscript = new Python_Subscript();
				varExp.subscript.setPresent(true);
				varExp.subscript.leftBracket = new PunctuationLeftBracket();
				varExp.subscript.rightBracket = new PunctuationRightBracket();
				varExp.subscript.body = new Python_Subscript.Python_SubscrExpr();

				if (offset == EagleGenerator.SubscriptEnum.FIRST_IS_ONE)
				{
					Python_Expression one = Python_Number.generateNumberExpression("1", source);
					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
					Python_Expression minusOne = Python_Additive_Expression.generateAdditive(types, subscrExpr, EagleGenerator.AdditiveEnum.MINUS, one, source);
					varExp.subscript.body.subscr = minusOne;
				}
				else
				{
					varExp.subscript.body.subscr = subscrExpr;
				}

				varExp.subscript.body.subscr.setPresent(true);
			}

			varExp.setTransformationSource(source);
			return Python_Generator.wrapExpression(varExp);
		}
	}

}
