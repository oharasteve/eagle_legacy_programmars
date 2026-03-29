// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.CMD.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Format = com.eagle.programmar.CMD.CMD_Format;
	using TokenRestOfLine = com.eagle.tokens.TokenRestOfLine;

	public class CMD_RestOfLine : TokenRestOfLine, EagleRunnable
	{
		public override void interpret(EagleInterpreter interpreter)
		{
			string val = this.getValue();
			string formatted = CMD_Format.format(interpreter, val);
			interpreter.pushStr(formatted);
		}
	}

}
