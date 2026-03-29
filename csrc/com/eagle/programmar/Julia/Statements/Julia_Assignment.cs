// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_Variable = com.eagle.programmar.Julia.Julia_Variable;
	using Julia_Identifier_Reference = com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
	using Julia_EOLN = com.eagle.programmar.Julia.Terminals.Julia_EOLN;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
	using Julia_PunctuationChoice = com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
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

	public class Julia_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Julia_Keyword GLOBAL = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("global");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Julia_Variable variable;
		public Julia_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice operator = new com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
		public Julia_PunctuationChoice @operator = new Julia_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Julia.Julia_Expression expression;
		public Julia_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln;
		public Julia_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			Julia_Identifier_Reference id = variable.vars.first();
			switch (@operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expression);
				interpreter.setSymbol(id, id.getValue(), val);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expression);
				EagleValue oldVar1 = interpreter.findSymbol(id.getValue());
				EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				interpreter.setSymbol(id, id.getValue(), newValue1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expression);
				EagleValue oldVar2 = interpreter.findSymbol(id.getValue());
				EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				interpreter.setSymbol(id, id.getValue(), newValue2);
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
			if (variable.subscript != null && variable.subscript.isPresent())
			{
				subscrExpr = transformer.transformExpression(generator, variable.subscript.expr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expression);
			Julia_Identifier_Reference id = variable.vars.first();
			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
