// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Variable = com.eagle.programmar.Scala.Scala_Variable;
	using Scala_Identifier_Reference = com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
	using Scala_EOLN = com.eagle.programmar.Scala.Terminals.Scala_EOLN;
	using Scala_PunctuationChoice = com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_Assignment : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Scala_Variable var;
		public Scala_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice operator = new com.eagle.programmar.Scala.Terminals.Scala_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
		public Scala_PunctuationChoice @operator = new Scala_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Scala.Scala_Expression expr;
		public Scala_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Scala.Terminals.Scala_EOLN eoln;
		public Scala_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			Scala_Identifier_Reference id = var.vars.first();
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

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

			AbstractExpression subscrExpr = null;
			if (var.subscript != null && var.subscript.isPresent())
			{
				subscrExpr = transformer.transformExpression(generator, var.subscript.expr);
			}
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
