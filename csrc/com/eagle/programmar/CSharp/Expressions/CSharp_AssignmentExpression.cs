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

	public class CSharp_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Expression var = new com.eagle.programmar.CSharp.CSharp_Expression(this, AllowedPrecedence.HIGHER);
		public CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice operator = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public CSharp_PunctuationChoice @operator = new CSharp_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.CSharp_Expression expr;
		public CSharp_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is CSharp_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}

			CSharp_VariableExpression varExpr = (CSharp_VariableExpression) var.getWhich();
			AbstractToken token = varExpr.variable.firstId.getWhich();
			if (token is CSharp_Identifier_Reference)
			{
				CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) token;
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

			if (!(var.getWhich() is CSharp_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			CSharp_VariableExpression variableExpr = (CSharp_VariableExpression) var.getWhich();
			CSharp_Variable theVar = variableExpr.variable;

			AbstractExpression subscrExpr = null;
			if (theVar.subscript != null && theVar.subscript.size() > 0)
			{
				subscrExpr = transformer.transformExpression(generator, theVar.subscript.first().expr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractToken which = theVar.firstId.getWhich();
			if (!(which is CSharp_Identifier_Reference))
			{
				throw new Exception("Have to assign to a regular variable");
			}
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) which;

			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}

		public static CSharp_Expression generateAssignment(CSharp_Variable variable, CSharp_Expression subscript, EagleGenerator.AssignmentEnum oper, CSharp_Expression expression, AbstractToken source)
		{
			CSharp_AssignmentExpression asgExpr = new CSharp_AssignmentExpression();

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
			if (!(which is CSharp_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which);
			}
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) which;

			asgExpr.var = CSharp_VariableExpression.generateVarExpr(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscript, source);
			asgExpr.@operator.setValue(punct);
			asgExpr.expr = expression;
			asgExpr.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(asgExpr);
		}
	}

}
