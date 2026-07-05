// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

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

public class Perl_Comment extends TerminalCommentToken
		implements EagleTransformableStatement
{
	public Perl_Comment()
	{
		this("");
	}

	public Perl_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		char ch = rec.charAt(_currentChar);

		if (ch == '#')
		{
			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc);
			return true;
		}

		if (ch == '/')
		{
			if (_currentChar + 1 >= nc) return false;
			ch = rec.charAt(_currentChar + 1);
			switch (ch)
			{
			case '/':
				return super.possibleCommentToEndOfLine(rec, "//");
			case '*':
				return super.possibleCommentPair2(lines, rec, "/*", "*/");
			}
		}

		return false;
	}

	@Override
	public String description()
	{
		return "/* comment */ or # or // comment to end of line";
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return null;
	}
}
