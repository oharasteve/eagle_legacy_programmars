// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template.Symbols
{
	using Template_Identifier = com.eagle.programmar.Template.Terminals.Template_Identifier;
	using DefinitionInterface = com.eagle.tokens.DefinitionInterface;

	public class Template_Identifier_Definition : Template_Identifier, DefinitionInterface
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
