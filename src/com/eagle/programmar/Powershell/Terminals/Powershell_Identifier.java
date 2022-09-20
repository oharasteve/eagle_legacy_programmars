// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalIdentifierToken;

public class Powershell_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if ( ! genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_-", false)) return false;
		
		// Cannot have the last character be a minus sign (-) in an identifier
		int len = _id.length();
		if (len > 0 && _id.charAt(len-1) == '-')
		{
			_id = _id.substring(0, len-1);	// Toss the - at the end
			_endChar--;		// Back up one
			_currentChar--;
		}
		
		return true;
	}
}
