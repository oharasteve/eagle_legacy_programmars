// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, June 17, 2011

package com.eagle.programmar.PLI.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalLiteralToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class PLI_Literal extends TerminalLiteralToken
		implements EagleTransformableExpression
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		char ch = rec.charAt(_currentChar);
		if (ch == '\'')
		{
			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (ch == '\'')
				{
					if (endChar + 1 >= recLen) break;
					if (rec.charAt(endChar + 1) != '\'')
					{
						break;
					}
					endChar++; // Doubled up single quotes
				}
			}

			// Look for '0'B and '0a'X
			if (endChar + 1 < recLen)
			{
				char nextChar = Character.toUpperCase(rec.charAt(endChar + 1));
				if (nextChar == 'B' || nextChar == 'X') return false;
			}

			foundIt(_currentLine, endChar);
			_txt = rec.substring(_currentChar + 1, endChar);
			return true;
		}
		return false;
	}

	@Override
	public String description()
	{
		return "PL/I literal";
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newLiteralExpression(_txt.replaceAll("'", ""), this);
	}
}
