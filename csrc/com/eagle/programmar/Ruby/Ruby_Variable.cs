// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Ruby_Identifier_Reference = com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
	using Ruby_Punctuation = com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Ruby_Variable : TokenSequence, AbstractVariable, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Ruby_Punctuation dollar = new com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> vars;
		public SeparatedList<Ruby_Identifier_Reference, PunctuationPeriod> vars;
	//	public @S(30) @OPT Ruby_Subscript subscript;

		public class Ruby_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Ruby_Expression expr;
			public Ruby_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Ruby_Identifier_Reference which = vars.first();
			EagleValue value = interpreter.findSymbol(which.getValue());

	//		if (subscript != null && subscript.isPresent())
	//		{
	//			if (value instanceof EagleArray)
	//			{
	//				int subscr = interpreter.getIntValue(subscript.expr);
	//				EagleArray val = (EagleArray) value;
	//				interpreter.pushEagleValue(val.getValue(subscr));
	//				return;
	//			}
	//
	//			if (value instanceof EagleString && subscript.expr.getWhich() instanceof Ruby_RangeExpression)
	//			{
	//				Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.expr.getWhich();
	//				String str = value.forceStringValue();
	//				int len = str.length();
	//				int sc = interpreter.getIntValue(range.left);
	//				int ec = interpreter.getIntValue(range.right) + 1;
	//				if (ec > len) ec = len;
	//				interpreter.pushStr(str.substring(sc, ec));
	//				return;
	//			}
	//		}

			interpreter.pushEagleValue(value);
		}
	}

}
