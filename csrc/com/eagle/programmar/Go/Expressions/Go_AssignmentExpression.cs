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
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_Identifier_Reference = com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
	using Go_PunctuationChoice = com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Go_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Go_Expression varExpr = new com.eagle.programmar.Go.Go_Expression(this, AllowedPrecedence.HIGHER);
		public Go_Expression varExpr = new Go_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Terminals.Go_PunctuationChoice equals = new com.eagle.programmar.Go.Terminals.Go_PunctuationChoice("=", ":=", "*=", "/=", "%=", "+=", "-=");
		public Go_PunctuationChoice equals = new Go_PunctuationChoice("=", ":=", "*=", "/=", "%=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Go_Expression expr = new com.eagle.programmar.Go.Go_Expression(this, AllowedPrecedence.ATLEAST);
		public Go_Expression expr = new Go_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(varExpr.getWhich() is Go_VariableExpression))
			{
				throw new Exception("Can only handle simple assignments, not  " + varExpr.getWhich());
			}

			Go_Variable variable = ((Go_VariableExpression) varExpr.getWhich()).variable;
			EagleValue val = interpreter.getEagleValue(expr);
			Go_Identifier_Reference id = variable.vars.first();
			switch (equals.getValue())
			{
			case "=", ":=":
				interpreter.setSymbol(id, id.ToString(), val);
				return;
			case "+=":
				EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
				int newVal1 = oldValue1.forceIntegerValue() + val.forceIntegerValue();
				interpreter.setSymbol(id, id.ToString(), new EagleInteger(newVal1));
				return;
			case "-=":
				EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
				int newVal2 = oldValue2.forceIntegerValue() - val.forceIntegerValue();
				interpreter.setSymbol(id, id.ToString(), new EagleInteger(newVal2));
				return;
			}
			throw new Exception("Unable to handle assignment operator: " + equals.getValue());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(varExpr.getWhich() is Go_VariableExpression))
			{
				throw new Exception("Can only handle simple assignments, not  " + varExpr.getWhich());
			}

			Go_Variable variable = ((Go_VariableExpression) varExpr.getWhich()).variable;
			EagleGenerator.AssignmentEnum asg;
			switch (equals.getValue())
			{
			case "=", ":=":
				asg = EagleGenerator.AssignmentEnum.EQUALS;
				break;
			case "+=":
				asg = EagleGenerator.AssignmentEnum.PLUS_EQUALS;
				break;
			case "-=":
				asg = EagleGenerator.AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + equals);
			}

			AbstractExpression subscrExpr = null;
			if (variable.subscript != null && variable.subscript.isPresent())
			{
				subscrExpr = transformer.transformExpression(generator, variable.subscript.expr);
			}
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(variable.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}
	}

}
