// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

namespace com.eagle.programmar.IBMASM.Symbols
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;

	public class IBMASM_Label_Definition : IBMASM_Identifier_Definition
	{
		public override DefinitionType Type
		{
			get
			{
				return DefinitionType.LABEL;
			}
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			if (_currentChar != 0)
			{
				return false; // Labels must be in column 1
			}
			return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "@", true, true);
		}
	}
}
