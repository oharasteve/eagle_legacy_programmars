// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Variable = com.eagle.programmar.Ada.Ada_Variable;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_PunctuationChoice = com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
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

	public class Ada_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Ada_Variable variable;
		public Ada_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice equals = new com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
		public Ada_PunctuationChoice equals = new Ada_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Expression expr;
		public Ada_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public override void interpret(EagleInterpreter interpreter)
		{
			Ada_Identifier_Reference id = variable.vars.first();
			EagleValue val = interpreter.getEagleValue(expr);

			EagleValue v;
			switch (equals.getValue())
			{
			case "=", ":=":
				v = val;
				break;
			case "+=":
				EagleValue var1 = interpreter.findSymbol(id.ToString());
				v = new EagleInteger(var1.forceIntegerValue() + val.forceIntegerValue());
				break;
			case "-=":
				EagleValue var2 = interpreter.findSymbol(id.ToString());
				v = new EagleInteger(var2.forceIntegerValue() - val.forceIntegerValue());
				break;
			case "*=":
				EagleValue var3 = interpreter.findSymbol(id.ToString());
				v = new EagleInteger(var3.forceIntegerValue() * val.forceIntegerValue());
				break;
			case "/=":
				EagleValue var4 = interpreter.findSymbol(id.ToString());
				v = new EagleInteger(var4.forceIntegerValue() / val.forceIntegerValue());
				break;
			default:
				throw new Exception("Unable to handle " + equals.getValue());
			}

			interpreter.setSymbol(variable, id.getValue(), v);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
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
				throw new Exception("Unexpected assignment operator: " + equals.getValue());
			}

			AbstractExpression subscrExpr = null;
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(variable.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
