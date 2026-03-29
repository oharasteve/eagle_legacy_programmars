// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Go.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_Identifier_Reference = com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
	using Go_PunctuationChoice = com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Go_PostIncrementExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Go_Variable var;
		public Go_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Terminals.Go_PunctuationChoice operator = new com.eagle.programmar.Go.Terminals.Go_PunctuationChoice("++", "--");
		public Go_PunctuationChoice @operator = new Go_PunctuationChoice("++", "--");

		public override void interpret(EagleInterpreter interpreter)
		{
			Go_Identifier_Reference id = var.vars.first();

			EagleValue val = interpreter.findSymbol(id.getValue());
			int prev = val.forceIntegerValue();

			int newVal;
			switch (@operator.getValue())
			{
			case "++":
				newVal = prev + 1;
				break;
			case "--":
				newVal = prev - 1;
				break;
			default:
				throw new Exception("Unexpected operator: " + @operator);
			}

			EagleValue curr = new EagleInteger(newVal);
			interpreter.setSymbol(var, id.getValue(), curr);
			interpreter.pushInt(prev);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.AssignmentEnum asg;
			switch (@operator.getValue())
			{
			case "++":
				asg = EagleGenerator.AssignmentEnum.PLUS_EQUALS;
				break;
			case "--":
				asg = EagleGenerator.AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected operator: " + @operator);
			}

			AbstractExpression one = generator.newNumberExpression("1", var);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, asg, one, this);
			return asgExpr;
		}
	}

}
