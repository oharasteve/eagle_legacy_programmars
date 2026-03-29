// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Julia_Identifier_Reference = com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
	using Julia_Punctuation = com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Julia_Variable : TokenSequence, AbstractVariable, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Julia_Punctuation dollar = new com.eagle.programmar.Julia.Terminals.Julia_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> vars;
		public SeparatedList<Julia_Identifier_Reference, PunctuationPeriod> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Julia_Subscript subscript;
		public  OPT;

		public class Julia_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Julia_Expression expr;
			public Julia_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Julia_Identifier_Reference which = vars.first();
			EagleValue value = interpreter.findSymbol(which.ToString());

			if (subscript != null && subscript.isPresent() && value is EagleArray)
			{
				int subscr = interpreter.getIntValue(subscript.expr);
				EagleArray val = (EagleArray) value;
				interpreter.pushEagleValue(val.getValue(subscr - 1));
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}
	}

}
