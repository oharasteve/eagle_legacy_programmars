// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Subscript = com.eagle.programmar.Javascript.Javascript_Subscript;
	using Javascript_Variable = com.eagle.programmar.Javascript.Javascript_Variable;
	using Javascript_VariableQualifier = com.eagle.programmar.Javascript.Javascript_Variable.Javascript_VariableQualifier;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
	using Javascript_PunctuationChoice = com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
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

	public class Javascript_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Expression var = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.HIGHER);
		public Javascript_Expression var = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice operator = new com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public Javascript_PunctuationChoice @operator = new Javascript_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Javascript.Javascript_Expression expr = new com.eagle.programmar.Javascript.Javascript_Expression(this, AllowedPrecedence.ATLEAST);
		public Javascript_Expression expr = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is Javascript_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}

			Javascript_VariableExpression varExpr = (Javascript_VariableExpression) var.getWhich();
			AbstractToken token = varExpr.variable.firstId.getWhich();
			if (token is Javascript_Identifier_Reference)
			{
				Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) token;
				switch (@operator.getValue())
				{
				case "=":
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(id, id.getValue(), val);
					break;
				case "+=":
					int newVal1 = interpreter.getIntValue(expr);
					EagleValue oldVar1 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
					interpreter.setSymbol(id, id.getValue(), newValue1);
					break;
				case "-=":
					int newVal2 = interpreter.getIntValue(expr);
					EagleValue oldVar2 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
					interpreter.setSymbol(id, id.getValue(), newValue2);
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

			if (!(var.getWhich() is Javascript_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			Javascript_VariableExpression variableExpr = (Javascript_VariableExpression) var.getWhich();
			Javascript_Variable theVar = variableExpr.variable;

			AbstractExpression newSub = null;
			if (theVar.qualifiers != null && theVar.qualifiers.size() == 1)
			{
				Javascript_Variable.Javascript_VariableQualifier qual = theVar.qualifiers.first();
				if (qual.getWhich() is Javascript_Subscript)
				{
					Javascript_Subscript sub = (Javascript_Subscript) qual.getWhich();
					newSub = transformer.transformExpression(generator, sub.expr);
				}
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractToken which = theVar.firstId.getWhich();
			if (!(which is Javascript_Identifier_Reference))
			{
				throw new Exception("Have to assign to a regular variable");
			}
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;

			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, newSub, asg, value, this);
			return asgExpr;
		}
	}

}
