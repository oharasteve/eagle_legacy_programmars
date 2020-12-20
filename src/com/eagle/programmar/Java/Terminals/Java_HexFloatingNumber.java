// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalHexNumberToken;
import com.eagle.tokens.TerminalToken;

/*
 * See https://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.2
 * Examples: 0x1.0p-53 0x1.0p-126f
 */

public class Java_HexFloatingNumber extends TerminalToken implements EagleRunnable
{
	protected String _numberAsText;
	
	@Override
	public String getDisplayStyleName()
	{
		return "number";
	}

	// Make it a little easier to read
	private static boolean isHex(char ch)
	{
		return TerminalHexNumberToken.HEX.indexOf(ch) >= 0;
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar + 3 >= recLen) return false;
		char ch1 = rec.charAt(_currentChar);
		char ch2 = rec.charAt(_currentChar + 1);
		char ch3 = rec.charAt(_currentChar + 2);
		char ch4 = rec.charAt(_currentChar + 3);
		
		if (ch1 == '0' &&
				(ch2 == 'x' || ch2 == 'X') &&
				(isHex(ch3) || ((ch3 == '+' || ch3 == '-' || ch3 == '.') && isHex(ch4))))
		{
			int endChar = _currentChar + 2;
			boolean foundExponent = false;
			boolean foundDecimalPoint = false;
			boolean foundDigit = false;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				char ch = rec.charAt(endChar);
				// No hex allowed after the exponent
				boolean validDigit = (foundExponent ? Character.isDigit(ch) : isHex(ch));
				if (validDigit)
				{
					foundDigit = true;
				}
				else
				{
					if (!foundDecimalPoint && !foundExponent && ch == '.')
					{
						foundDecimalPoint = true;
						continue;
					}
					
					// Uses p instead of e
					if (foundDigit && !foundExponent && (ch == 'p' || ch == 'P'))
					{
						if (endChar+1 < recLen)
						{
							ch = rec.charAt(endChar + 1);
							if (ch == '+' || ch == '-') endChar++;
						}
						foundExponent = true;
						continue;
					}

					// Allow underscores between digits (but not in the exponent)
					if (!foundExponent && ch == '_')
					{
						if (endChar == _currentChar || endChar+1 >= recLen) return false;
						if (! isHex(rec.charAt(endChar-1))) return false;
						if (! isHex(rec.charAt(endChar+1))) return false;
						continue;		// Keep the underscore in the token
					}
					
					// Check for suffix (float or double)
					if (ch == 'f' || ch == 'F' || ch == 'd' || ch == 'D') endChar++;
					
					break;
				}
			}
			
			if (! foundExponent) return false;  // The 'p' is required
			foundIt(_currentLine, endChar - 1);
			_numberAsText = rec.substring(_currentChar, endChar);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return _numberAsText;
	}
	
	@Override
	public void setValue(String val)
	{
		_numberAsText = val;
		setPresent(val != null);
	}
	
	@Override
	public String getValue()
	{
		return _numberAsText;
	}
	
	@Override
	public String showString()
	{
		return "Number";
	}

	@Override
	public String description()
	{
		return "A hex floating number";
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double value = Double.parseDouble(_numberAsText);
		interpreter.pushDouble(value);
	}
}
