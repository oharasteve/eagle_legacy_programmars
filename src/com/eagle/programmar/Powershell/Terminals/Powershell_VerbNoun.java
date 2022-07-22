// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2022

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;

public class Powershell_VerbNoun extends Powershell_Identifier
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (! super.parse(lines)) return false;
		
		// Must have exactly one hyphen and cannot be at either end
		int nc = _id.length();
		boolean foundHyphen = false;
		for (int i = 0; i < nc; i++)
		{
			char ch = _id.charAt(i);
			if (ch == '-')
			{
				if (i == 0 || i == nc-1) return false;	// Cannot be at the ends
				if (foundHyphen) return false;			// Can only have one
				foundHyphen = true;
			}
		}
		return foundHyphen;
	}
}
