// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Variable = com.eagle.programmar.Ruby.Ruby_Variable;
	using Ruby_Identifier_Reference = com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_PunctuationChoice = com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
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

	public class Ruby_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Variable var;
		public Ruby_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice operator = new com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
		public Ruby_PunctuationChoice @operator = new Ruby_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Ruby_Expression expr;
		public Ruby_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln;
		public Ruby_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			Ruby_Identifier_Reference id = var.vars.first();
			EagleValue val = interpreter.getEagleValue(expr);

			switch (@operator.getValue())
			{
			case "=", ":=":
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
				int old1 = oldValue1.forceIntegerValue();
				interpreter.setSymbol(var, id.getValue(), new EagleInteger(old1 + val.forceIntegerValue()));
				break;
			case "-=":
				EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
				int old2 = oldValue2.forceIntegerValue();
				interpreter.setSymbol(var, id.getValue(), new EagleInteger(old2 - val.forceIntegerValue()));
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
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
			}

			AbstractExpression subscrExpr = null;
	//		if (var.subscript != null && var.subscript.isPresent())
	//		{
	//			subscrExpr = transformer.transformExpression(generator, var.subscript.expr);
	//		}
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
