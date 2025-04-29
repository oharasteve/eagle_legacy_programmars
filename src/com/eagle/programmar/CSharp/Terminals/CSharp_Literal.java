// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.generate.Terminals.Eagle_Generate_Literal;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CSharp_Literal extends TerminalLiteralToken
		implements Eagle_Generate_Literal
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) == '@')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (genericLiteral(lines, "\"", false, '?', true, true)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		// An "interpolated" string can have {{ and }} in it
		if (rec.charAt(_currentChar) == '$')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (genericLiteral(lines, "\"", true, '\\', false, false)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		return genericLiteral(lines, "\"", true, '\\', false, false);
	}
	
	@Override
	public CSharp_Literal generateLiteral(String value, AbstractToken source)
	{
		String val = value;
		if (! val.startsWith("\""))
		{
			val = '"' + val + '"';
		}
		this.setValue(val);
		this.setTransformationSource(source);
		return this;
	}
}
