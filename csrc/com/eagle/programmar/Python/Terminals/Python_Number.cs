// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalNumberToken = com.eagle.tokens.terminals.TerminalNumberToken;

	public class Python_Number : TerminalNumberToken
	{
		public Python_Number() : base("Ee", "JjLl", true, false, '?')
		{
			// J is for complex ...
		}

		public static Python_Number createNumber(int value)
		{
			return generateNumber(Convert.ToString(value), null);
		}

		public static Python_Number generateNumber(string value, AbstractToken source)
		{
			Python_Number num = new Python_Number();
			num.setValue(value);
			num.setTransformationSource(source);
			return num;
		}

		public static Python_Expression generateNumberExpression(string value, AbstractToken source)
		{
			Python_Number num = generateNumber(value, source);
			return Python_Generator.wrapExpression(num);
		}
	}

}
