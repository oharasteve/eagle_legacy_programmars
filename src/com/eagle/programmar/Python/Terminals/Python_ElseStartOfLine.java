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

		if (DBG) System.out.println("******* ElseStartOfLine: Checking " + (_currentLine+1) + "/" + (_currentChar+1));
		AbstractToken parent = this;
		while (parent != null)
		{
			if (DBG) System.out.println("******* Parent is " + parent.getClass().getName());
			if (parent instanceof AbstractStatement) break;
			parent = parent.getParent();
		}
		
		/////// The KEY Line ///////
		if (_currentChar != parent.getStartChar())
		{
			if (DBG) System.out.println("******* IF FAIL: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (parent.getStartLine()+1) + "/" + (parent.getStartChar()+1));
			return false;
		}

		if (DBG) System.out.println("******* IF MATCH: Comparing " + (_currentLine+1) + "/" + (_currentChar+1) + " to " + (parent.getStartLine()+1) + "/" + (parent.getStartChar()+1));
		foundIt(_currentLine, _currentChar - 1);
		return true;
	}
	
//	@Override
//	public String toString()
//	{
//		return super.toString();
//	}
}