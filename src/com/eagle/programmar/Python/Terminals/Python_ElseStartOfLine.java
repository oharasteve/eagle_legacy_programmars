// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Python_ElseStartOfLine extends Python_StartOfLine
{
	private static final boolean DBG = false;

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		AbstractToken ifStmt = this;
		while (ifStmt.getParent() != null)
		{
			ifStmt = ifStmt.getParent();
			if (DBG) System.out.println("****** Parent is " + ifStmt.getClass().getName());
			if (ifStmt instanceof AbstractStatement) break;
		}
		
		if (_currentChar != ifStmt.getStartChar())
		{
			if (DBG) System.out.println("******* IF FAIL: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (ifStmt.getStartLine()+1) + "/" + (ifStmt.getStartChar()+1));
			return false;
		}

		if (DBG) System.out.println("******* IF MATCH: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (ifStmt.getStartLine()+1) + "/" + (ifStmt.getStartChar()+1));
		foundIt(_currentLine, _currentChar - 1);
		return true;
	}
}