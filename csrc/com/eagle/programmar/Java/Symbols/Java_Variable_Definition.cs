// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

namespace com.eagle.programmar.Java.Symbols
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;

	public class Java_Variable_Definition : Java_Identifier_Definition, EagleRunnable
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.VARIABLE;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			int val = interpreter.findSymbol(this.ToString()).forceIntegerValue();
			interpreter.pushInt(val);
		}
	}

}
