// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.TCL.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Variable = com.eagle.programmar.TCL.TCL_Variable;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_BracketStringFirst : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Terminals.TCL_Keyword STRING = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("string");
		public TCL_Keyword STRING = new TCL_Keyword("string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.Terminals.TCL_Keyword FIRST = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("first");
		public TCL_Keyword FIRST = new TCL_Keyword("first");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.TCL.TCL_Expression pattern;
		public TCL_Expression pattern;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.TCL.TCL_Variable string;
		public TCL_Variable @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TCL_Expression start;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;

		public override void interpret(EagleInterpreter interpreter)
		{
			string patt = interpreter.getStrValue(pattern);
			string str = interpreter.getStrValue(@string);
			if (start != null && start.isPresent())
			{
				int sc = interpreter.getIntValue(start);
				interpreter.pushInt(str.IndexOf(patt, sc, StringComparison.Ordinal));
			}
			else
			{
				interpreter.pushInt(str.IndexOf(patt, StringComparison.Ordinal));
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractVariable strVar = generator.newVariable(@string.id.getValue());
			AbstractExpression pattExpr = transformer.transformExpression(generator, pattern);
			AbstractExpression startExpr = null;
			if (start != null && start.isPresent())
			{
				startExpr = transformer.transformExpression(generator, start);
			}
			return generator.newIndexOfFunction(strVar, pattExpr, startExpr, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
		}
	}

}
