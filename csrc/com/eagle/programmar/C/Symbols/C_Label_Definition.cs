// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 27, 2011

namespace com.eagle.programmar.C.Symbols
{
	using C_Identifier = com.eagle.programmar.C.Terminals.C_Identifier;
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public class C_Label_Definition : C_Identifier, DefinitionInterface
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.LABEL;
			}
		}
	}
}
