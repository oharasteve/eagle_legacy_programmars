// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

namespace com.eagle.programmar.Python.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;

	public class Python_Find_Method : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Variable string;
		public Python_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Keyword FIND = new com.eagle.programmar.Python.Terminals.Python_Keyword("find");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Python_Expression pattern;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE Python_Find_SC scExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class Python_Find_SC : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression start;
			public Python_Expression start;
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

		public static Python_Expression generateIndexOf(Python_Variable str, Python_Expression patt, Python_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
		{
			Python_Find_Method indexFn = new Python_Find_Method();
			indexFn.@string = str;
			indexFn.dot = new PunctuationPeriod();
			indexFn.leftParen = new PunctuationLeftParen();
			indexFn.pattern = patt;
			if (sc != null)
			{
				indexFn.scExpr = new Python_Find_SC();
				indexFn.scExpr.setPresent(true);
				indexFn.scExpr.comma = new PunctuationComma();
				indexFn.scExpr.start = sc;
			}
			indexFn.rightParen = new PunctuationRightParen();

			indexFn.setTransformationSource(source);
			return Python_Generator.wrapExpression(indexFn);
		}
	}

}
