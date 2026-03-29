// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

namespace com.eagle.programmar.Java.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_PrintFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE Java_Keyword SYSTEM = new com.eagle.programmar.Java.Terminals.Java_Keyword("System");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot1;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_KeywordChoice OUT = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("err", "out");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationPeriod dot2;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Java_KeywordChoice PRINT = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("print", "println");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE @OPT Java_Expression expr;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override void interpret(EagleInterpreter interpreter)
		{
			string val = interpreter.getStrValue(expr);
			PrintStream prt = System.out;
			if (OUT.getValue().Equals("err"))
			{
				prt = System.err;
			}
			switch (PRINT.getValue())
			{
			case "print":
				prt.print(val);
				return;
			case "println":
				prt.println(val);
				return;
			}

			throw new Exception("Unexpected keyword: " + PRINT.getValue());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			bool newLine;
			switch (PRINT.getValue())
			{
			case "print":
				newLine = false;
				break;
			case "println":
				newLine = true;
				break;
			default:
				throw new Exception("Unexpected PRINT value: " + PRINT.getValue());
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			return generator.newPrintFunction(value, EagleGenerator.TypeEnum.STRING, newLine, false, this);
		}

		public static Java_Expression generatePrintFunc(Java_Expression line, EagleGenerator.TypeEnum type, bool newLine, bool toErr, AbstractToken source)
		{
			Java_PrintFunction prtFn = new Java_PrintFunction();
			prtFn.dot1 = new PunctuationPeriod();
			prtFn.dot1.setPresent(true);
			if (toErr)
			{
				prtFn.OUT = new Java_KeywordChoice("err");
			}
			else
			{
				prtFn.OUT = new Java_KeywordChoice("out");
			}
			prtFn.dot2 = new PunctuationPeriod();
			prtFn.dot2.setPresent(true);

			if (newLine)
			{
				prtFn.PRINT = new Java_KeywordChoice("println");
			}
			else
			{
				prtFn.PRINT = new Java_KeywordChoice("print");
			}

			prtFn.leftParen = new PunctuationLeftParen();
			prtFn.rightParen = new PunctuationRightParen();

			prtFn.expr = line;
			if (line != null)
			{
				prtFn.expr.setPresent(true);
			}

			prtFn.setTransformationSource(source);
			return Java_Generator.wrapExpression(prtFn);
		}
	}

}
