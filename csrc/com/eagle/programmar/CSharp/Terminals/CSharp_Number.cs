// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp.Terminals
{
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class CSharp_Number : TerminalNumberToken
	{
		public CSharp_Number() : base("Ee", "LlFfDdUuMm", true, false, '?')
		{
		}

		public static CSharp_Number generateNumber(string value, AbstractToken source)
		{
			CSharp_Number num = new CSharp_Number();
			num.setValue(value);
			num.setTransformationSource(source);
			return num;
		}

		public static CSharp_Expression generateNumberExpression(string value, AbstractToken source)
		{
			return CSharp_Generator.wrapExpression(generateNumber(value, source));
		}
	}

}
