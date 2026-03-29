// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell.Symbols
{
	using Powershell_Identifier = com.eagle.programmar.Powershell.Terminals.Powershell_Identifier;
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public abstract class Powershell_Identifier_Definition : Powershell_Identifier, DefinitionInterface
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
