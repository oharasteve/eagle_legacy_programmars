// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Variable = com.eagle.programmar.Powershell.Powershell_Variable;
	using Powershell_PunctuationChoice = com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
	using EagleScope = com.eagle.scope.EagleScope;
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

	public class Powershell_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression var = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.HIGHER);
		public Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice operator = new com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=");
		public Powershell_PunctuationChoice @operator = new Powershell_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Powershell_Expression expr = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			if (var.getWhich() is Powershell_VariableExpression)
			{
				Powershell_VariableExpression pVar = (Powershell_VariableExpression) var.getWhich();
				EagleValue newValue;
				switch (@operator.getValue())
				{
				case "=":
					newValue = interpreter.getEagleValue(expr);
					break;
				case "+=":
					int newVal1 = interpreter.getIntValue(expr);
					EagleValue oldVar1 = interpreter.findSymbol(pVar.variable.id.ToString());
					newValue = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
					break;
				case "-=":
					int newVal2 = interpreter.getIntValue(expr);
					EagleValue oldVar2 = interpreter.findSymbol(pVar.variable.id.ToString());
					newValue = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
					break;
				default:
					throw new Exception("Unexpected assignment operator: " + @operator.getValue());
				}

				if (pVar.variable.scope != null && pVar.variable.scope.isPresent())
				{
					// Was calling SetGlobalSymbol()
					EagleScope saveScope = interpreter._symbolTable.getScope();
					interpreter._symbolTable.setScope(interpreter._lang.getScope()); // Smash it :)
					interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
					interpreter._symbolTable.setScope(saveScope);
				}
				else
				{
					interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
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

			if (!(var.getWhich() is Powershell_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			Powershell_VariableExpression variableExpr = (Powershell_VariableExpression) var.getWhich();
			Powershell_Variable theVar = variableExpr.variable;

			AbstractExpression subscrExpr = null;
			if (theVar.subscript != null && theVar.subscript.isPresent())
			{
				subscrExpr = transformer.transformExpression(generator, theVar.subscript.subscr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			string newName = Powershell_Variable.repairName(theVar.id.getValue());
			AbstractExpression asgExpr = generator.newAssignmentExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}
	}

}
