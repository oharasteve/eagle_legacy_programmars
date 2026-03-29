// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using Rust_PunctuationChoice = com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
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

	public class Rust_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression var = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.HIGHER);
		public Rust_Expression var = new Rust_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice operator = new com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice("=", "+=", "-=");
		public Rust_PunctuationChoice @operator = new Rust_PunctuationChoice("=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Rust_Expression expr;
		public Rust_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is Rust_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}
			Rust_VariableExpression varExpr = (Rust_VariableExpression) var.getWhich();

			string id = varExpr.variable.var.getValue();
			switch (@operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(var, id, val);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expr);
				EagleValue oldVar1 = interpreter.findSymbol(id);
				EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				interpreter.setSymbol(var, id, newValue1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expr);
				EagleValue oldVar2 = interpreter.findSymbol(id);
				EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				interpreter.setSymbol(var, id, newValue2);
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(var.getWhich() is Rust_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}
			Rust_VariableExpression varExpr = (Rust_VariableExpression) var.getWhich();

			EagleGenerator.AssignmentEnum asg;
			switch (@operator.getValue())
			{
			case "=":
				asg = EagleGenerator.AssignmentEnum.EQUALS;
				break;
			case "+=":
				asg = EagleGenerator.AssignmentEnum.PLUS_EQUALS;
				break;
			case "-=":
				asg = EagleGenerator.AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(varExpr.variable.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
			return asgExpr;
		}

		public static Rust_Expression generateAssignment(Rust_Variable variable, Rust_Expression subscript, EagleGenerator.AssignmentEnum oper, Rust_Expression expression, AbstractToken source)
		{
			Rust_AssignmentExpression asgExpr = new Rust_AssignmentExpression();
			string punct;
			switch (oper)
			{
			case EQUALS:
				punct = "=";
				break;
			case PLUS_EQUALS:
				punct = "+=";
				break;
			case MINUS_EQUALS:
				punct = "-=";
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + oper);
			}

			Rust_Identifier_Reference id = variable.var;
			Rust_VariableExpression varExpr = new Rust_VariableExpression();
			varExpr.variable = Rust_Variable.generateVariable(id.getValue());
			asgExpr.var = Rust_Generator.wrapExpression(varExpr);
			asgExpr.@operator.setValue(punct);
			asgExpr.expr = expression;
			asgExpr.setTransformationSource(source);
			return Rust_Generator.wrapExpression(asgExpr);
		}
	}

}
