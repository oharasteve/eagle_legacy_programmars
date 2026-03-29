// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Subscript = com.eagle.programmar.Java.Java_Subscript;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
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

	public class Java_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Variable variable;
		public Java_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscript = null;
			if (variable.subscript != null && variable.subscript.size() > 0)
			{
				Java_Subscript first = variable.subscript.first();
				subscript = transformer.transformExpression(generator, first.expr);
			}
			AbstractToken which = variable.firstId.getWhich();
			if (!(which is Java_Identifier_Reference))
			{
				throw new Exception("Cannot handle variable: " + which);
			}
			Java_Identifier_Reference id = (Java_Identifier_Reference) which;
			return generator.newVariableExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscript, this);
		}

		public static Java_Expression generateVariableExpression(string name, EagleGenerator.SubscriptEnum offset, Java_Expression subscrExpr, AbstractToken source)
		{
			Java_VariableExpression varExp = new Java_VariableExpression();
			varExp.variable = Java_Variable.newVariable(name);
			varExp.setTransformationSource(source);

			if (subscrExpr != null)
			{
				if (offset == EagleGenerator.SubscriptEnum.IT_IS_A_HASHMAP)
				{
					Java_Variable var = Java_Variable.newVariable(name + ".get");
					List<Java_Expression> args = new List<Java_Expression>();
					args.Add(subscrExpr);
					return Java_MethodInvocation.generateInvocation(var, args, source);
				}

				Java_Subscript subscript = new Java_Subscript();
				subscript.leftBracket = new PunctuationLeftBracket();
				subscript.rightBracket = new PunctuationRightBracket();

				if (offset == EagleGenerator.SubscriptEnum.FIRST_IS_ONE)
				{
					Java_Expression one = Java_Number.generateNumberExpression("1", source);
					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
					Java_Expression minusOne = Java_AdditiveExpression.generateAdditive(types, subscrExpr, EagleGenerator.AdditiveEnum.MINUS, one, source);
					subscript.expr = minusOne;
				}
				else
				{
					subscript.expr = subscrExpr;
				}
				subscript.expr.setPresent(true);

				varExp.variable.subscript = new TokenList<Java_Subscript>();
				varExp.variable.subscript.addToken(subscript);
			}

			return Java_Generator.wrapExpression(varExp);
		}
	}

}
