// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2025

namespace com.eagle.programmar.Eaglish.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Variable = com.eagle.programmar.Eaglish.Eaglish_Variable;
	using Eaglish_Identifier_Reference = com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
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

	public class Eaglish_Subtract_Statement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword SUBTRACT = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("SUBTRACT");
		public Eaglish_Keyword SUBTRACT = new Eaglish_Keyword("SUBTRACT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Eaglish_Expression expr;
		public Eaglish_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword FROM = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("FROM");
		public Eaglish_Keyword FROM = new Eaglish_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Eaglish.Eaglish_Variable var;
		public Eaglish_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln;
		public Eaglish_EndOfLine eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			int x = interpreter.getIntValue(expr);
			int prev = interpreter.getIntValue(var);
			EagleInteger val = new EagleInteger(prev - x);

			AbstractToken which = var.var.getWhich();
			if (which is Eaglish_Identifier_Reference)
			{
				Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;
				interpreter.setSymbol(var, id.getValue(), val);
				return;
			}
			throw new Exception("Unable to process " + which);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractToken which = var.var.getWhich();
			if (!(which is Eaglish_Identifier_Reference))
			{
				throw new Exception("Can only subtract from variables");
			}
			Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;

			AbstractExpression subscr = null;
			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscr, EagleGenerator.AssignmentEnum.MINUS_EQUALS, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
