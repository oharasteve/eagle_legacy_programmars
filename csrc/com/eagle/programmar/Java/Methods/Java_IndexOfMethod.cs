// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

namespace com.eagle.programmar.Java.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;

	public class Java_IndexOfMethod : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Java_Variable string;
		public Java_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_Keyword INDEXOF = new com.eagle.programmar.Java.Terminals.Java_Keyword("indexOf");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Java_Expression pattern;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE Java_Index_SC scExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class Java_Index_SC : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Java_Expression start;
			public Java_Expression start;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(@string);
			string patt = interpreter.getStrValue(pattern);
			if (scExpr != null && scExpr.isPresent())
			{
				int sc = interpreter.getIntValue(scExpr);
				interpreter.pushInt(str.IndexOf(patt, sc, StringComparison.Ordinal));
			}
			else
			{
				interpreter.pushInt(str.IndexOf(patt, StringComparison.Ordinal));
			}
		}

		public static Java_Expression generateIndexOf(Java_Variable str, Java_Expression patt, Java_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
		{
			Java_IndexOfMethod indexMeth = new Java_IndexOfMethod();
			indexMeth.@string = str;
			indexMeth.dot = new PunctuationPeriod();
			indexMeth.leftParen = new PunctuationLeftParen();
			indexMeth.pattern = patt;
			if (sc != null)
			{
				indexMeth.scExpr = new Java_Index_SC();
				indexMeth.scExpr.setPresent(true);
				indexMeth.scExpr.comma = new PunctuationComma();
				indexMeth.scExpr.start = sc;
			}
			indexMeth.rightParen = new PunctuationRightParen();

			indexMeth.setTransformationSource(source);
			return Java_Generator.wrapExpression(indexMeth);
		}
	}

}
