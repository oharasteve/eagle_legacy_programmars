// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.core.EagleSyntax;
import com.eagle.generate.EagleGenerator;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.terminals.TerminalCommentToken;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class COBOL_Comment extends TerminalCommentToken
		implements EagleTransformableStatement
{
	public COBOL_Comment()
	{
		this("");
	}

	public COBOL_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleSyntax syntax = getSyntax();
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		int nc = rec.length();
		
		// Special case, *> can appear in any column
		if (ch == '*' && _currentChar + 1 < nc)
		{
			char ch2 = rec.charAt(_currentChar + 1);
			if (ch2 == '>')
			{
				_endChar = syntax.recLen(lines, _currentLine);
				foundIt(_currentLine, _endChar);
				_comment = rec.substring(_currentChar, _endChar);
				return true;
			}
		}

		// Normal comments are in column 7 (or 8)
		if (_currentChar != syntax._commentColumn)
		{
			if (_currentChar != syntax._commentColumn + 1)
			{
				return false; // The '*' must be in column 1 for free format, 7 or 8 for fixed
			}
		}
		if (ch != '*' && ch != '/') return false;

		_endChar = syntax.recLen(lines, _currentLine);
		foundIt(_currentLine, _endChar);
		_comment = rec.substring(_currentChar, _endChar);
		return true;
	}

	@Override
	public String description()
	{
		return "* comment";
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return null;		// Could keep comments here ...
	}
}
