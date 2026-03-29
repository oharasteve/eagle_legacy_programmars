// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

namespace com.eagle.programmar.C.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Variable = com.eagle.programmar.C.C_Variable;
	using C_VariableExpression = com.eagle.programmar.C.Expressions.C_VariableExpression;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class C_StrCatFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword STRCAT = new com.eagle.programmar.C.Terminals.C_Keyword("strcat");
		public C_Keyword STRCAT = new C_Keyword("strcat");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Expressions.C_VariableExpression varExp;
		public C_VariableExpression varExp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.C_Expression expr;
		public C_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			C_Variable var = varExp.variable;
			C_Identifier_Reference id = (C_Identifier_Reference) var.firstId.getWhich();
			string varName = id.getValue();
			string str = interpreter.getStrValue(expr);
			string oldVal = interpreter.findSymbol(varName).forceStringValue();
			EagleString val = new EagleString(oldVal + str);
			interpreter.setSymbol(var, varName, val);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = varExp.variable.firstId.getWhich();
			if (!(which is C_Identifier_Reference))
			{
				throw new Exception("Must be a regular variable");
			}
			C_Identifier_Reference id = (C_Identifier_Reference) which;
			AbstractExpression newValue = transformer.transformExpression(generator, expr);
			return generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, newValue, STRCAT);
		}
	}

}
