// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java.Terminals
{
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class Java_Number : TerminalNumberToken
	{
		public Java_Number() : base("Ee", "LlFfDd", true, true, '_')
		{
		}

		public static Java_Number generateNumber(string value, AbstractToken source)
		{
			Java_Number num = new Java_Number();
			num.setValue(value);
			num.setTransformationSource(source);
			return num;
		}

		public static Java_Expression generateNumberExpression(string value, AbstractToken source)
		{
			return Java_Generator.wrapExpression(generateNumber(value, source));
		}
	}

}
