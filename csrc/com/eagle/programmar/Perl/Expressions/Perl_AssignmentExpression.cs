// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_UserVariable = com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
	using Perl_Identifier_Reference = com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
	using Perl_PunctuationChoice = com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
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

	public class Perl_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Perl_Expression var = new com.eagle.programmar.Perl.Perl_Expression(this, AllowedPrecedence.HIGHER);
		public Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice operator = new com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
		public Perl_PunctuationChoice @operator = new Perl_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=", ".=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Perl.Perl_Expression expr;
		public Perl_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (!(var.getWhich() is Perl_VariableExpression))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}
			Perl_VariableExpression varExpr = (Perl_VariableExpression) var.getWhich();
			if (!(varExpr.variable.getWhich() is Perl_Variable.Perl_UserVariable))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}

			Perl_Variable.Perl_UserVariable userVar = (Perl_Variable.Perl_UserVariable) varExpr.variable.getWhich();
			switch (@operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(var, userVar.id.getValue(), val);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expr);
				EagleValue oldVar1 = interpreter.findSymbol(userVar.id.ToString());
				EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				interpreter.setSymbol(var, userVar.id.getValue(), newValue1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expr);
				EagleValue oldVar2 = interpreter.findSymbol(userVar.id.ToString());
				EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				interpreter.setSymbol(var, userVar.id.getValue(), newValue2);
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
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

			if (!(var.getWhich() is Perl_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			Perl_VariableExpression variableExpr = (Perl_VariableExpression) var.getWhich();
			Perl_Variable theVar = variableExpr.variable;
			if (!(theVar.getWhich() is Perl_Variable.Perl_UserVariable))
			{
				throw new Exception("Unexpected assignment variable: " + var.getWhich());
			}
			Perl_Variable.Perl_UserVariable userVar = (Perl_Variable.Perl_UserVariable) theVar.getWhich();

			AbstractExpression subscrExpr = null;
			if (userVar.subscript != null && userVar.subscript.size() > 0)
			{
				subscrExpr = transformer.transformExpression(generator, userVar.subscript.first().expr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			Perl_Identifier_Reference id = userVar.id;
			string newName = Perl_Variable.repairName(id.getValue());

			AbstractExpression asgExpr = generator.newAssignmentExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}
	}

}
