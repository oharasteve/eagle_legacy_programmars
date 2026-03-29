// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using AWK_Punctuation = com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using LogicalOrEnum = com.eagle.transform.EagleGenerator.LogicalOrEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_LogicalOrExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.AWK_Expression left = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.ATLEAST);
		public AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_Punctuation orOperator = new com.eagle.programmar.AWK.Terminals.AWK_Punctuation("||");
		public AWK_Punctuation orOperator = new AWK_Punctuation("||");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT AWK_EndOfLine eoln;
		public  OPT; // Hack -- really should switch to Multiline_Syntax
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.AWK.AWK_Expression right = new com.eagle.programmar.AWK.AWK_Expression(this, AllowedPrecedence.HIGHER);
		public AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.HIGHER);

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
