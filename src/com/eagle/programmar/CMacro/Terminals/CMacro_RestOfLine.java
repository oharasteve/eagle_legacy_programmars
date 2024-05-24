// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 29, 2015

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.TokenRestOfLine;

public class CMacro_RestOfLine extends TokenRestOfLine implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		String rec = lines.get(_currentLine).toString();
		int linesSize = lines.numberLines();
		int lastLine = _currentLine;
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;

		int sc = _currentChar;
		int ec = recLen - 1;
		StringBuffer text = new StringBuffer();
		while (ec >= 0)
		{
			// Look for comments
			if (rec.charAt(ec) != '\\')
			{
				int commentPos = rec.indexOf("/*", sc);
				if (commentPos >= 0)
				{
					ec = commentPos - 1;
				}
				else
				{
					commentPos = rec.indexOf("//", sc);
					if (commentPos >= 0) ec = commentPos - 1;
				}
			}

			// Build the new result, one line at a time
			String piece = rec.substring(sc, ec + 1);
			// Chop off the trailing \, if any
			if (piece.endsWith("\\")) piece = piece.substring(0, piece.length() - 1);
			if (text.length() > 0) text.append('\n');
			text.append(piece);

			// Not continued (any more)
			if (!rec.endsWith("\\") || lastLine + 1 >= linesSize) break;

			// Must be continued on the next line
			lastLine++;
			rec = lines.get(lastLine).toString();
			sc = 0;
			ec = rec.length() - 1;
		}

		_txt = text.toString();
		foundIt(lastLine, ec);
		return true;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Blank _txt means return ""
		if (_txt.trim().length() == 0)
		{
			interpreter.pushStr("");
			return;
		}

		// Have to parse it, in order to evaluate it
		CMacro_Program lang = new CMacro_Program();
		CMacro_Syntax syntax = new CMacro_Syntax();
		EagleFileReader lines = new EagleFileReader();
		lines.add(_txt, "none", 0);

		CMacro_Expression expr = new CMacro_Expression();
		expr.setSyntax(syntax);
		if (!interpreter._parser.parseLines(lines, lang, expr))
		{
			throw new RuntimeException("Unable to parse expression " + _txt);
		}

		// Evaluate the newly parsed expression
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
	}
}
