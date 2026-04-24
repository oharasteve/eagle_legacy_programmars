// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CSharp_Literal extends TerminalLiteralToken
{
	public CSharp_Literal()
	{
		super("\"", true, '\\', false, false);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		if (rec.charAt(_currentChar) == '@')
		{
			lines.setCurrentChar(_currentChar + 1);
			super._hasEscape = false;	// Smash these temporarily
			super._allowDoubled = true; // @"..." is an odd beast
			super._allowMultiline = true;
			if (super.parse(lines)) return true;
			super._hasEscape = true;    // Restore these
			super._allowDoubled = false;
			super._allowMultiline = false;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		// An "interpolated" string can have {{ and }} in it
		if (rec.charAt(_currentChar) == '$')
		{
			lines.setCurrentChar(_currentChar + 1);
			if (super.parse(lines)) return true;
			lines.setCurrentChar(_currentChar);
			return false;
		}

		return super.parse(lines);
	}

	public static CSharp_Literal generateLiteral(String value, AbstractToken source)
	{
		CSharp_Literal lit = new CSharp_Literal();
		String val = '"' + value
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\\\\\\\\n", "\\\\n")
				.replaceAll("\"", "\\\\\"")
				+ '"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public static CSharp_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		CSharp_Literal lit = CSharp_Literal.generateLiteral(value, source);
		return CSharp_Generator.wrapExpression(lit);
	}
}
