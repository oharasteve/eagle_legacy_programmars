// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Java.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_PunctuationChoice = com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
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

	public class Java_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression var = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.HIGHER);
		public Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_PunctuationChoice operator = new com.eagle.programmar.Java.Terminals.Java_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public Java_PunctuationChoice @operator = new Java_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Java_Expression expr;
		public Java_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is Java_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}

			Java_VariableExpression varExpr = (Java_VariableExpression) var.getWhich();
			AbstractToken token = varExpr.variable.firstId.getWhich();
			if (token is Java_Identifier_Reference)
			{
				Java_Identifier_Reference id = (Java_Identifier_Reference) token;
				switch (@operator.getValue())
				{
				case "=":
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(var, id.getValue(), val);
					break;
				case "+=":
					int newVal1 = interpreter.getIntValue(expr);
					EagleValue oldVar1 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
					interpreter.setSymbol(var, id.getValue(), newValue1);
					break;
				case "-=":
					int newVal2 = interpreter.getIntValue(expr);
					EagleValue oldVar2 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
					interpreter.setSymbol(var, id.getValue(), newValue2);
					break;
				default:
					throw new Exception("Unexpected assignment operator: " + @operator.getValue());
				}
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
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

			if (!(var.getWhich() is Java_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			Java_VariableExpression variableExpr = (Java_VariableExpression) var.getWhich();
			Java_Variable theVar = variableExpr.variable;

			AbstractExpression subscrExpr = null;
			if (theVar.subscript != null && theVar.subscript.size() > 0)
			{
				subscrExpr = transformer.transformExpression(generator, theVar.subscript.first().expr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractToken which = theVar.firstId.getWhich();
			if (!(which is Java_Identifier_Reference))
			{
				throw new Exception("Have to assign to a regular variable");
			}
			Java_Identifier_Reference id = (Java_Identifier_Reference) which;

			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}

		public static Java_Expression generateAssignment(Java_Variable variable, Java_Expression subscript, EagleGenerator.AssignmentEnum oper, Java_Expression expression, AbstractToken source)
		{
			Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
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

			AbstractToken which = variable.firstId.getWhich();
			if (!(which is Java_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which);
			}
			Java_Identifier_Reference id = (Java_Identifier_Reference) which;

			asgExpr.var = Java_VariableExpression.generateVariableExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscript, source);
			asgExpr.@operator.setValue(punct);
			asgExpr.expr = expression;
			asgExpr.setTransformationSource(source);
			return Java_Generator.wrapExpression(asgExpr);
		}
	}

}
