// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Identifier_Reference = com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Algol68_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> vars;
		public SeparatedList<Algol68_Identifier_Reference, PunctuationPeriod> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Algol68_Subscript subscript;
		public  OPT;

		public class Algol68_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Algol68_Expression expr;
			public Algol68_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Algol68_ColonSubscript colonSub;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public class Algol68_ColonSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Algol68_Expression expr2;
			public Algol68_Expression expr2;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Algol68_Identifier_Reference which = vars.first();
			EagleValue value = interpreter.findSymbol(which.ToString());
			if (subscript != null && subscript.isPresent() && value.isArray())
			{
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(subscript.expr);
				EagleValue val = array.getValue(sub - 1);
				interpreter.pushEagleValue(val);
			}
			else if (subscript != null && subscript.isPresent() && value.isString())
			{
				string str = value.forceStringValue();
				int sub = interpreter.getIntValue(subscript.expr);
				if (subscript.colonSub != null && subscript.colonSub.isPresent())
				{
					int ec = interpreter.getIntValue(subscript.colonSub.expr2);
					interpreter.pushStr(str.Substring(sub - 1, ec - (sub - 1)));
				}
				else
				{
					interpreter.pushStr(str.Substring(sub - 1, sub - (sub - 1)));
				}
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}
	}

}
