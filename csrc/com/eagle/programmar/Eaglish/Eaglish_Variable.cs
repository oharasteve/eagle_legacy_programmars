// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Eaglish_Identifier_Reference = com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Eaglish_Variable : TokenSequence, EagleRunnable, AbstractVariable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Eaglish_VariableIdentifier var;
		public Eaglish_VariableIdentifier var;

		public class Eaglish_VariableIdentifier : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Eaglish_Identifier_Reference XXid;
			public Eaglish_Identifier_Reference XXid;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Eaglish_Identifier_Reference which = (Eaglish_Identifier_Reference) var.getWhich();
			EagleValue value = interpreter.findSymbol(which.ToString());
			interpreter.pushEagleValue(value);
		}
	}

}
