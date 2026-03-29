// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

namespace com.eagle.programmar.Powershell.Symbols
{
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public class Powershell_Function_Definition : Powershell_Identifier_Definition, DefinitionInterface
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.FUNCTION;
			}
		}
	}

}
