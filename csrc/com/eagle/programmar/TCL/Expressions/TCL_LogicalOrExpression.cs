// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.TCL.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TCL_Punctuation = com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_LogicalOrExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.TCL.TCL_Expression left = new com.eagle.programmar.TCL.TCL_Expression(this, AllowedPrecedence.ATLEAST);
		public TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) TCL_OrOperator orOper;
		public TCL_OrOperator orOper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.TCL_Expression right = new com.eagle.programmar.TCL.TCL_Expression(this, AllowedPrecedence.HIGHER);
		public TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

		public class TCL_OrOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_Keyword XXOR = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("or");
			public TCL_Keyword XXOR = new TCL_Keyword("or");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE TCL_Punctuation XXorOper = new com.eagle.programmar.TCL.Terminals.TCL_Punctuation("||");
			public TCL_Punctuation XXorOper = new TCL_Punctuation("||");
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			bool leftValue = interpreter.getBoolValue(left);
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				bool rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, right);
			return generator.newLogicalOrExpression(leftExpr, EagleGenerator.LogicalOrEnum.OR, rightExpr, this);
		}
	}
}
