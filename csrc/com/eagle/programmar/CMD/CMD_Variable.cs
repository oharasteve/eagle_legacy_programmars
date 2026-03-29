// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

namespace com.eagle.programmar.CMD
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using CMD_Identifier_Reference = com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
	using CMD_Punctuation = com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class CMD_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CMD_Punctuation dollar = new com.eagle.programmar.CMD.Terminals.CMD_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference id;
		public CMD_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CMD_Subscript subscript;
		public  OPT;

		public class CMD_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_Expression expr;
			public CMD_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = id.getValue();
			if (subscript != null && subscript.isPresent())
			{
				int sub = interpreter.getIntValue(subscript.expr);
				name += "[" + sub + "]";
			}
			EagleValue value = interpreter.findSymbol(name);
			interpreter.pushEagleValue(value);
		}
	}

}
