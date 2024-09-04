// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class CSharp_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDdUuMm", true);
	}
	
	public static CSharp_Number generateExpression(String value, AbstractToken source)
	{
		CSharp_Number num = new CSharp_Number();
		num.setValue(value);
		num.setTransformationSource(source);
		return num;
	}
}
