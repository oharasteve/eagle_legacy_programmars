// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Subscript = com.eagle.programmar.CSharp.CSharp_Subscript;
	using CSharp_Variable = com.eagle.programmar.CSharp.CSharp_Variable;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
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

	public class CSharp_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Variable variable;
		public CSharp_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscript = null;
			if (variable.subscript != null && variable.subscript.size() > 0)
			{
				CSharp_Subscript first = variable.subscript.first();
				subscript = transformer.transformExpression(generator, first.expr);
			}
			AbstractToken which = variable.firstId.getWhich();
			if (!(which is CSharp_Identifier_Reference))
			{
				throw new Exception("Cannot handle variable: " + which);
			}
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) which;
			return generator.newVariableExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscript, this);
		}

		public static CSharp_Expression generateVarExpr(string name, EagleGenerator.SubscriptEnum offset, CSharp_Expression subscrExpr, AbstractToken source)
		{
			CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
			varExpr.variable = CSharp_Variable.newVariable(name);

			if (subscrExpr != null)
			{
				CSharp_Subscript subscript = new CSharp_Subscript();
				subscript.leftBracket = new PunctuationLeftBracket();

				if (offset == EagleGenerator.SubscriptEnum.FIRST_IS_ONE)
				{
					CSharp_Expression one = CSharp_Generator.wrapExpression(CSharp_Number.generateNumber("1", source));
					Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
					CSharp_Expression minusOne = CSharp_AdditiveExpression.generateAdditive(types, subscrExpr, EagleGenerator.AdditiveEnum.MINUS, one, source);
					subscript.expr = minusOne;
				}
				else
				{
					subscript.expr = subscrExpr;
				}
				subscript.expr.setPresent(true);
				subscript.rightBracket = new PunctuationRightBracket();

				varExpr.variable.subscript = new TokenList<CSharp_Subscript>();
				varExpr.variable.subscript.addToken(subscript);
			}

			varExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(varExpr);
		}
	}

}
