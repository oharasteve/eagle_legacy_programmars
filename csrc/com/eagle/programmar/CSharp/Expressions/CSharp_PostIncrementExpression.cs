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
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Variable = com.eagle.programmar.CSharp.CSharp_Variable;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using IncrementEnum = com.eagle.transform.EagleGenerator.IncrementEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_PostIncrementExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Variable var;
		public CSharp_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE CSharp_PunctuationChoice operator = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("++", "--");
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (var.firstId.getWhich() is CSharp_Identifier_Reference)
			{
				CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) var.firstId.getWhich();
				EagleValue val = interpreter.findSymbol(id.getValue());
				int prev = val.forceIntegerValue();
				int curr;
				switch (@operator.getValue())
				{
				case "++":
					curr = prev + 1;
					break;
				case "--":
					curr = prev - 1;
					break;
				default:
					throw new Exception("Unexpected operator: " + @operator);
				}
				interpreter.setSymbol(var, id.getValue(), new EagleInteger(curr));
				interpreter.pushInt(prev);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.IncrementEnum whichDirection;
			switch (@operator.getValue())
			{
			case "++":
				whichDirection = EagleGenerator.IncrementEnum.INCREMENT;
				break;
			case "--":
				whichDirection = EagleGenerator.IncrementEnum.DECREMENT;
				break;
			default:
				throw new Exception("Unexpected operator: " + @operator);
			}
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) var.firstId.getWhich();
			return generator.newPostIncrementExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
		}

		public static CSharp_Expression generateIncrement(CSharp_Variable variable, EagleGenerator.IncrementEnum oper, AbstractToken source)
		{
			CSharp_PostIncrementExpression postExpr = new CSharp_PostIncrementExpression();
			postExpr.var = variable;
			switch (oper)
			{
			case INCREMENT:
				postExpr.@operator.setValue("++");
				break;
			case DECREMENT:
				postExpr.@operator.setValue("--");
				break;
			default:
				throw new Exception("Unexpected operator: " + oper);
			}
			postExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(postExpr);
		}
	}

}
