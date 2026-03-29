// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

namespace com.eagle.programmar.Java.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Java_Character_Literal : TerminalLiteralToken
	{
		public Java_Character_Literal() : base("'", true, '\\', false, false)
		{
		}

		public static Java_Character_Literal generateCharLiteral(string value, AbstractToken source)
		{
			Java_Character_Literal lit = new Java_Character_Literal();
			lit.setValue(value);
			lit.setTransformationSource(source);
			return lit;
		}
	}
}
