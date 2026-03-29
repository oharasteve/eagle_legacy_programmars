// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2024

namespace com.eagle.programmar.CSharp.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class CSharp_PrintFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("System");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Keyword CONSOLE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("Console");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationPeriod dot2;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE @OPT CSharp_KeywordChoice OUT = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("Error", "Out");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE @OPT PunctuationPeriod dot3;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE CSharp_KeywordChoice WRITE = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("Flush", "ReadLine", "SetOut", "Write", "WriteLine");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE @OPT CSharp_ConsoleWriteArgs args;
		public  NOSPACE;

		public class CSharp_ConsoleWriteArgs : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationLeftParen leftParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE @OPT SeparatedList<com.eagle.programmar.CSharp.CSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightParen rightParen;
			public  NOSPACE;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string val = interpreter.getStrValue(args.exprs.first());
			switch (WRITE.getValue())
			{
			case "Write":
				Console.Write(val);
				return;
			case "WriteLine":
				Console.WriteLine(val);
				return;
			}

			throw new Exception("Unexpected keyword: " + WRITE.getValue());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			bool newLine;
			switch (WRITE.getValue())
			{
			case "Write":
				newLine = false;
				break;
			case "WriteLine":
				newLine = true;
				break;
			default:
				throw new Exception("Unexpected WRITE value: " + WRITE.getValue());
			}

			AbstractExpression value = transformer.transformExpression(generator, args.exprs.first());
			return generator.newPrintFunction(value, EagleGenerator.TypeEnum.STRING, newLine, false, this);
		}

		public static CSharp_Expression generatePrintFunc(CSharp_Expression line, bool newLine, bool toErr, AbstractToken source)
		{
			CSharp_PrintFunction prtFn = new CSharp_PrintFunction();
			prtFn.SYSTEM.setPresent(true);
			prtFn.dot1 = new PunctuationPeriod();
			prtFn.dot1.setPresent(true);
			prtFn.dot2 = new PunctuationPeriod();
			prtFn.dot2.setPresent(true);
			if (toErr)
			{
				prtFn.OUT = new CSharp_KeywordChoice("Error");
			}
			else
			{
				prtFn.OUT = new CSharp_KeywordChoice("Out");
			}
			prtFn.OUT.setPresent(true);

			if (newLine)
			{
				prtFn.WRITE = new CSharp_KeywordChoice("WriteLine");
			}
			else
			{
				prtFn.WRITE = new CSharp_KeywordChoice("Write");
			}

			prtFn.dot3 = new PunctuationPeriod();
			prtFn.dot3.setPresent(true);
			prtFn.args = new CSharp_ConsoleWriteArgs();
			prtFn.args.setPresent(true);
			prtFn.args.leftParen = new PunctuationLeftParen();
			prtFn.args.rightParen = new PunctuationRightParen();

			prtFn.args.exprs = new SeparatedList<CSharp_Expression, PunctuationComma>();
			prtFn.args.exprs.addPrimaryElement(line);

			prtFn.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(prtFn);
		}
	}

}
