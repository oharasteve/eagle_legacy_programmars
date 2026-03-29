// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

namespace com.eagle.programmar.CSharp.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Variable = com.eagle.programmar.CSharp.CSharp_Variable;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;

	public class CSharp_IndexOfMethod : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.CSharp_Variable string;
		public CSharp_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Keyword INDEXOF = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("IndexOf");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE CSharp_Expression pattern;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE CSharp_Index_SC scExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class CSharp_Index_SC : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.CSharp_Expression start;
			public CSharp_Expression start;
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

		public static CSharp_Expression generateIndexOf(CSharp_Variable str, CSharp_Expression patt, CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
		{
			CSharp_IndexOfMethod indexMeth = new CSharp_IndexOfMethod();
			indexMeth.@string = str;
			indexMeth.dot = new PunctuationPeriod();
			indexMeth.leftParen = new PunctuationLeftParen();
			indexMeth.pattern = patt;
			if (sc != null)
			{
				indexMeth.scExpr = new CSharp_Index_SC();
				indexMeth.scExpr.setPresent(true);
				indexMeth.scExpr.comma = new PunctuationComma();
				indexMeth.scExpr.start = sc;
			}
			indexMeth.rightParen = new PunctuationRightParen();

			indexMeth.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(indexMeth);
		}
	}

}
