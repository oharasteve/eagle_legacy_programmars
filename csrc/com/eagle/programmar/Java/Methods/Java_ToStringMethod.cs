// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2025

namespace com.eagle.programmar.Java.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_ParenthesizedExpression = com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
	using Java_VariableExpression = com.eagle.programmar.Java.Expressions.Java_VariableExpression;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Java_ToStringMethod : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression expression = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Keyword TOSTRING = new com.eagle.programmar.Java.Terminals.Java_Keyword("toString");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @NOSPACE Java_Expression value;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushStr(val.ToString());
		}

		public static Java_Expression generateString(Oper1Types types, Java_Expression expr, AbstractToken source)
		{
			Java_ToStringMethod strMeth = new Java_ToStringMethod();

			if (types != null && types._type1 == TypeEnum.INTEGER)
			{
				// Java does not like 'ok.toString()' where 'ok' is an int instead of an
				// Integer.
				Java_VariableExpression varExpr = new Java_VariableExpression();
				varExpr.variable = Java_Variable.newVariable("Integer");
				strMeth.expression = Java_Generator.wrapExpression(varExpr);
				strMeth.dot = new PunctuationPeriod();
				strMeth.leftParen = new PunctuationLeftParen();
				strMeth.value = expr;
				strMeth.value.setPresent(true);
				strMeth.rightParen = new PunctuationRightParen();
				return Java_Generator.wrapExpression(strMeth);
			}

			return Java_ParenthesizedExpression.generateParentheses(expr, source);
		}
	}

}
