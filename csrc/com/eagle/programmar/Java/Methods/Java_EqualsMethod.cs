// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

namespace com.eagle.programmar.Java.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_EqualsMethod : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression left = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Keyword EQUALS = new com.eagle.programmar.Java.Terminals.Java_Keyword("equals");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Java_Expression expr;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(left);
			string other = interpreter.getStrValue(expr);
			interpreter.pushBool(leftStr.Equals(other));
		}

		public static Java_EqualsMethod newEqualsMethod(Java_Expression leftExpr, Java_Expression rightExpr)
		{
			Java_EqualsMethod equals = new Java_EqualsMethod();
			equals.left = leftExpr;
			equals.dot = new PunctuationPeriod();
			equals.leftParen = new PunctuationLeftParen();
			equals.expr = rightExpr;
			equals.rightParen = new PunctuationRightParen();
			return equals;
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression leftExpr = transformer.transformExpression(generator, left);
			AbstractExpression rightExpr = transformer.transformExpression(generator, expr);
			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.STRING, EagleGenerator.TypeEnum.STRING);
			return generator.newRelationalExpression(types, leftExpr, EagleGenerator.RelationalEnum.EQUALS, rightExpr, this);
		}
	}

}
