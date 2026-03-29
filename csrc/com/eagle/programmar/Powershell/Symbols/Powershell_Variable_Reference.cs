// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 21, 2022

namespace com.eagle.programmar.Powershell.Symbols
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;

	public class Powershell_Variable_Reference : Powershell_Identifier_Reference
	{
		public override bool parse(EagleFileReader lines)
		{
			// Allow reserved words because the $ is required for variables
			if (!genericIdentifier(lines, ALPHAS + "_", ALPHAS + DIGITS + "_-", false, true))
			{
				return false;
			}
			removeTrailingHyphens();
			return true;
		}
	}

}
