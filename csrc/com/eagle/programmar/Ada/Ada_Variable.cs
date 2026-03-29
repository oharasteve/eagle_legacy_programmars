// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_Punctuation = com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Ada_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Ada_Punctuation dollar = new com.eagle.programmar.Ada.Terminals.Ada_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> vars;
		public SeparatedList<Ada_Identifier_Reference, PunctuationPeriod> vars;

		public override void interpret(EagleInterpreter interpreter)
		{
			Ada_Identifier_Reference which = vars.first();
			EagleValue value = interpreter.findSymbol(which.ToString());
			interpreter.pushEagleValue(value);
		}
	}

}
