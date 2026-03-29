// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

namespace com.eagle.programmar.JavaP.Symbols
{
	using JavaP_HashNumber = com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public class JavaP_Symbol_Definition : JavaP_HashNumber, DefinitionInterface
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.SYMBOL;
			}
		}
	}
}
