// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.CMacro.Symbols
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using EagleString = com.eagle.math.EagleString;
	using CMacro_Identifier = com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
	using ReferenceInterface = com.eagle.tokens.ReferenceInterface;

	public class CMacro_Identifier_Reference : CMacro_Identifier, ReferenceInterface, EagleRunnable
	{
		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(_id.ToString());
			if (value == null)
			{
				value = new EagleString(""); // Treat undefined symbols as blank
			}
			interpreter.pushEagleValue(value);
		}
	}

}
