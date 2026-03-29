// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using TCL_Identifier_Reference = com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference;
	using TCL_Punctuation = com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class TCL_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TCL_Punctuation dollar = new com.eagle.programmar.TCL.Terminals.TCL_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference id;
		public TCL_Identifier_Reference id;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(id.ToString());
			interpreter.pushEagleValue(value);
		}
	}

}
