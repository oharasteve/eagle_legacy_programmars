// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using Algol68_Identifier_Reference = com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
	using Algol68_PunctuationChoice = com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Variable var;
		public Algol68_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice operator = new com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice("=", "+=", ":=", "+:=");
		public Algol68_PunctuationChoice @operator = new Algol68_PunctuationChoice("=", "+=", ":=", "+:=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Algol68_Expression expression;
		public Algol68_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationSemicolon semicolon;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);
			Algol68_Identifier_Reference id = var.vars.first();

			EagleValue v;
			switch (@operator.getValue())
			{
			case "=", ":=":
				v = val;
				break;
			case "+=", "+:=":
				EagleValue var1 = interpreter.findSymbol(id.ToString());
				v = new EagleInteger(var1.forceIntegerValue() + val.forceIntegerValue());
				break;
			default:
				throw new Exception("Unable to handle " + @operator.getValue());
			}

			interpreter.setSymbol(var, id.getValue(), v);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.AssignmentEnum asg;
			switch (@operator.getValue())
			{
			case "=", ":=":
				asg = EagleGenerator.AssignmentEnum.EQUALS;
				break;
			case "+=", "+:=":
				asg = EagleGenerator.AssignmentEnum.PLUS_EQUALS;
				break;
			case "-=":
				asg = EagleGenerator.AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
			}

			AbstractExpression value = transformer.transformExpression(generator, expression);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
