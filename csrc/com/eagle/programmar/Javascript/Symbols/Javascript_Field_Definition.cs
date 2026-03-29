// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2022

namespace com.eagle.programmar.Javascript.Symbols
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;

	public class Javascript_Field_Definition : Javascript_Identifier_Definition
	{
		public override bool parse(EagleFileReader lines)
		{
			// Allow reserved words for a field name
			if (!genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_-", false, true))
			{
				return false;
			}
			return true;
		}

		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.FIELD;
			}
		}
	}

}
