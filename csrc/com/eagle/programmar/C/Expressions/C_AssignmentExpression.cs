// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.C.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Variable = com.eagle.programmar.C.C_Variable;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using CMacro_StatementOrComment = com.eagle.programmar.CMacro.CMacro_StatementOrComment;
	using CMacro_Syntax = com.eagle.programmar.CMacro.CMacro_Syntax;
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

	public class C_AssignmentExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Expression var = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.HIGHER);
		public C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_PunctuationChoice operator = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
		public C_PunctuationChoice @operator = new C_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @SYNTAX(com.eagle.programmar.CMacro.CMacro_Syntax.class) com.eagle.programmar.CMacro.CMacro_StatementOrComment macro;
		public  OPT; // What the ...
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.C_Expression expr = new com.eagle.programmar.C.C_Expression(this, AllowedPrecedence.ATLEAST);
		public C_Expression expr = new C_Expression(this, AllowedPrecedence.ATLEAST);

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			if (!(var.getWhich() is C_VariableExpression))
			{
				throw new Exception("Can only handle simple assignments, not  " + var.getWhich());
			}

			C_Variable variable = ((C_VariableExpression) var.getWhich()).variable;
			AbstractToken which = variable.firstId.getWhich();
			if (!(which is C_Identifier_Reference))
			{
				throw new Exception("Unable to handle " + which + " now");
			}
			C_Identifier_Reference id = (C_Identifier_Reference) which;

			switch (@operator.getValue())
			{
			case "=":
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
				int newValue1 = oldValue1.forceIntegerValue() + val.forceIntegerValue();
				interpreter.setSymbol(var, id.getValue(), new EagleInteger(newValue1));
				break;
			case "-=":
				EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
				int newValue2 = oldValue2.forceIntegerValue() - val.forceIntegerValue();
				interpreter.setSymbol(var, id.getValue(), new EagleInteger(newValue2));
				break;
			default:
				throw new Exception("Can only handle = and += right now");
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

			if (!(var.getWhich() is C_VariableExpression))
			{
				throw new Exception("Can only assign variables");
			}
			C_VariableExpression variableExpr = (C_VariableExpression) var.getWhich();
			C_Variable theVar = variableExpr.variable;

			AbstractExpression subscrExpr = null;
			if (theVar.subscript != null && theVar.subscript.size() > 0)
			{
				subscrExpr = transformer.transformExpression(generator, theVar.subscript.first().expr);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractToken which = theVar.firstId.getWhich();
			if (!(which is C_Identifier_Reference))
			{
				throw new Exception("Have to assign to a regular variable");
			}
			C_Identifier_Reference id = (C_Identifier_Reference) which;

			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return asgExpr;
		}
	}

}
