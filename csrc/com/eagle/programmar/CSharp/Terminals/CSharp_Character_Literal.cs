// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 6, 2015

namespace com.eagle.programmar.CSharp.Terminals
{
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CSharp_Character_Literal : TerminalLiteralToken
	{
		public CSharp_Character_Literal() : base("'", true, '\\', false, false)
		{
		}

		public virtual CSharp_Character_Literal generateCharLiteral(string value, AbstractToken source)
		{
			this.setValue(value);
			this.setTransformationSource(source);
			return this;
		}
	}
}
