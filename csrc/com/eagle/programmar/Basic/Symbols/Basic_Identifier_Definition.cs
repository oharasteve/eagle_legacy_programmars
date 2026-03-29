// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Symbols
{
	using Basic_Identifier = com.eagle.programmar.Basic.Terminals.Basic_Identifier;
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public class Basic_Identifier_Definition : Basic_Identifier, DefinitionInterface
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.VARIABLE;
			}
		}
	}
}
