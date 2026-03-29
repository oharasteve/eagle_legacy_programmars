// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2025

namespace com.eagle.programmar.Python.Methods
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Variable = com.eagle.programmar.Python.Python_Variable;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
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
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_StartsWith_Method : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Variable string;
		public Python_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Python_Keyword STARTSWITH = new com.eagle.programmar.Python.Terminals.Python_Keyword("startswith");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Python_Expression pattern;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @NOSPACE Python_Index_SC scExpr;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public class Python_Index_SC : TokenSequence
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
				interpreter.pushBool(str.StartsWith(patt, sc));
			}
			else
			{
				interpreter.pushBool(str.StartsWith(patt, StringComparison.Ordinal));
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = generator.newVariableExpression(@string.var.getWhich().ToString(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, this);
			AbstractExpression thePattern = transformer.transformExpression(generator, pattern);
			AbstractExpression theSC = null;
			if (scExpr != null && scExpr.isPresent())
			{
				theSC = transformer.transformExpression(generator, scExpr.start);
			}

			return generator.newStartsWithFunction(theExpr, thePattern, theSC, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
		}

		public static Python_Expression generateStartsWith(Python_Expression expr, Python_Expression patt, Python_Expression sc, EagleGenerator.SubstringSCEnum whichSC, AbstractToken source)
		{
			Python_StartsWith_Method startsFunc = new Python_StartsWith_Method();
			AbstractToken token = expr.getWhich();
			if (!(token is Python_VariableExpression))
			{
				throw new Exception("Python startswith must be a variable, not " + token);
			}

			Python_VariableExpression varExpr = (Python_VariableExpression) token;
			startsFunc.@string = varExpr.variable;
			startsFunc.dot = new PunctuationPeriod();
			startsFunc.leftParen = new PunctuationLeftParen();
			startsFunc.pattern = patt;
			if (sc != null)
			{
				startsFunc.scExpr = new Python_Index_SC();
				startsFunc.scExpr.setPresent(true);
				startsFunc.scExpr.comma = new PunctuationComma();
				startsFunc.scExpr.start = sc;
			}
			startsFunc.rightParen = new PunctuationRightParen();

			startsFunc.setTransformationSource(source);
			return Python_Generator.wrapExpression(startsFunc);
		}
	}

}
