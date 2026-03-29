// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2022

namespace com.eagle.programmar.Powershell.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;

	public class Powershell_VerbNoun : Powershell_Identifier
	{
		public override bool parse(EagleFileReader lines)
		{
			if (!base.parse(lines))
			{
				return false;
			}

			// Must have exactly one hyphen and cannot be at either end
			int nc = _id.length();
			bool foundHyphen = false;
			for (int i = 0; i < nc; i++)
			{
				char ch = _id.charAt(i);
				if (ch == '-')
				{
					if (i == 0 || i == nc - 1)
					{
						return false; // Cannot be at the ends
					}
					if (foundHyphen)
					{
						return false; // Can only have one
					}
					foundHyphen = true;
				}
			}
			return foundHyphen;
		}
	}

}
