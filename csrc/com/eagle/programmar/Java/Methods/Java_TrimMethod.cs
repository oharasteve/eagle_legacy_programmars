// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2025

namespace com.eagle.programmar.Java.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_TrimMethod : PrecedenceOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Expression expression = new com.eagle.programmar.Java.Java_Expression(this, AllowedPrecedence.ATLEAST);
		public Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Keyword TRIM = new com.eagle.programmar.Java.Terminals.Java_Keyword("trim");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string leftStr = interpreter.getStrValue(expression);
			interpreter.pushStr(leftStr.Trim());
		}

		public static Java_Expression generateTrim(Java_Expression expr, AbstractToken source)
		{
			Java_TrimMethod trimMeth = new Java_TrimMethod();
			trimMeth.expression = expr;
			trimMeth.dot = new PunctuationPeriod();
			trimMeth.leftParen = new PunctuationLeftParen();
			trimMeth.rightParen = new PunctuationRightParen();

			trimMeth.setTransformationSource(source);
			return Java_Generator.wrapExpression(trimMeth);
		}
	}

}
