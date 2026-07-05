// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell.Terminals;

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

public class Powershell_Comment extends TerminalCommentToken
		implements EagleTransformableStatement
{
	// Need a default constructor for the parser
	public Powershell_Comment()
	{
		this("");
	}

	public Powershell_Comment(String comment)
	{
		super(comment);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		if (_currentChar < rec.length())
		{
			char ch1 = rec.charAt(_currentChar);
			if (ch1 == '#')
			{
				return super.possibleCommentToEndOfLine(rec, "#");
			}
		}

		if (_currentChar + 1 < rec.length())
		{
			char ch1 = rec.charAt(_currentChar);
			if (ch1 == '/')
			{
				char ch2 = rec.charAt(_currentChar + 1);
				if (ch2 == '/')
				{
					return super.possibleCommentToEndOfLine(rec, "//");
				}
				if (ch2 == '*')
				{
					return super.possibleCommentPair2(lines, rec, "/*", "*/");
				}
			}
			else if (ch1 == '<')
			{
				char ch2 = rec.charAt(_currentChar + 1);
				if (ch2 == '#')
				{
					return super.possibleCommentPair2(lines, rec, "<#", "#>");
				}
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
		return null; // Suppose we could save it ...
	}
}
