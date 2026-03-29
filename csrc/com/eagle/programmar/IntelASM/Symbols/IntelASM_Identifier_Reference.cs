// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM.Symbols
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using IntelASM_Identifier = com.eagle.programmar.IntelASM.Terminals.IntelASM_Identifier;
	using ReferenceInterface = com.eagle.tokens.ReferenceInterface;

	public class IntelASM_Identifier_Reference : IntelASM_Identifier, ReferenceInterface, EagleRunnable
	{
		public override void interpret(EagleInterpreter interpreter)
		{
			int val = interpreter.findSymbol(this.getValue()).forceIntegerValue();
			interpreter.pushInt(val);
		}
	}

}
