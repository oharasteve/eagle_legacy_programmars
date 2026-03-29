// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

namespace com.eagle.programmar.AWK.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleHash = com.eagle.math.EagleHash;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Variable = com.eagle.programmar.AWK.AWK_Variable;
	using AWK_VarSubscript = com.eagle.programmar.AWK.AWK_Variable.AWK_VarSubscript;
	using AWK_PunctuationChoice = com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
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

	public class AWK_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Variable variable;
		public AWK_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice equals = new com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
		public AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.AWK.AWK_Expression expr;
		public AWK_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue var = interpreter.findSymbol(variable.id.ToString());
			EagleValue val = interpreter.getEagleValue(expr);

			if (variable.subscripts != null && variable.subscripts.size() == 1)
			{
				EagleHash hash = (EagleHash) var;
				if (hash == null)
				{
					hash = new EagleHash();
					interpreter.setSymbol(variable, variable.id.getValue(), hash);
				}
				AWK_Variable.AWK_VarSubscript sub = variable.subscripts.first();
				int key = interpreter.getIntValue(sub.expr);
				hash.putValue(Convert.ToInt32(key), val);
			}
			else
			{
				EagleValue v;
				switch (equals.getValue())
				{
				case "=":
					v = val;
					break;
				case "+=":
					v = new EagleInteger(var.forceIntegerValue() + val.forceIntegerValue());
					break;
				case "-=":
					v = new EagleInteger(var.forceIntegerValue() - val.forceIntegerValue());
					break;
				case "*=":
					v = new EagleInteger(var.forceIntegerValue() * val.forceIntegerValue());
					break;
				case "/=":
					v = new EagleInteger(var.forceIntegerValue() / val.forceIntegerValue());
					break;
				default:
					throw new Exception("Unable to handle " + equals.getValue());
				}

				interpreter.setSymbol(variable, variable.id.getValue(), v);
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.AssignmentEnum asg;
			switch (equals.getValue())
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
				throw new Exception("Unexpected assignment operator: " + equals.getValue());
			}

			string varName = variable.id.getValue();
			if (varName.Equals("true") || varName.Equals("false"))
			{
				// Sorry, cannot redefine true or false
				return null;
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			if (variable.subscripts != null && variable.subscripts.size() == 1)
			{
				AWK_Variable.AWK_VarSubscript varSub = variable.subscripts.first();
				AbstractExpression subscrExpr = transformer.transformExpression(generator, varSub.expr);
				AbstractExpression hashExpr = generator.newHashAssignment(varName, subscrExpr, value, this);
				return generator.newExpressionStatement(hashExpr, this);
			}

			AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}
}
