// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template.Symbols
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Template_Identifier = com.eagle.programmar.Template.Terminals.Template_Identifier;
	using ReferenceInterface = com.eagle.tokens.ReferenceInterface;

	public class Template_Identifier_Reference : Template_Identifier, ReferenceInterface, EagleRunnable
	{
		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(_id.ToString());
			interpreter.pushEagleValue(value);
		}
	}
}
