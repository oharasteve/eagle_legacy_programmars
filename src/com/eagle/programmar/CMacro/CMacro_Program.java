// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.core.EagleLanguage;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_MultiLineText;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Program extends EagleLanguage
{
	public static final String CMACRO = "CMacro";
	
	public CMacro_Program()
	{
		super(CMACRO, new CMacro_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://gcc.gnu.org/onlinedocs/cpp/";
	}
	
//	@Override
//	public void findClassOverrides(EagleOverrideManager overrideManager)
//	{
//		// Instead of creating a bunch of real C statements inside a #if, just use this simple class
//		overrideManager.override(CMacro_IfElement.class, CMacro_Element.class);
//		overrideManager.override(CMacro_IfDefElement.class, CMacro_Element.class);
//	}
	
	public @S(10) TokenList<CMacro_Element> elements;
	
	public static class CMacro_Element extends TokenChooser
	{
		public @CHOICE CMacro_CommentLine comment;
		public @CHOICE CMacro_StatementOrComment stmt;
		public @CHOICE CMacro_MultiLineText textLine;
		public @LAST CMacro_EndOfLine endOfLine;
	}
	
	public static class CMacro_CommentLine extends TokenSequence
	{
		public @S(10) CMacro_Comment comment;
		public @S(20) CMacro_EndOfLine endOfLine;
	}
	
	public static class CMacro_TextLine extends TerminalLiteralToken
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			// Don't allow C lines to start with a #
			if (findStart(lines) == FOUND.EOF) return false;
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (recLen < _currentChar) return false;
			if (_currentChar < recLen && rec.charAt(_currentChar) == '#')
			{
				// Check to make sure we are at the start of a line. This check may be superfluous.
				// Normally, _currentChar = 0 for a macro line that starts with #
				// In that case, the loop doesn't even execute once so it fails as a text line.
				boolean atStart = true;
				for (int i = 0; i < _currentChar; i++)
				{
					char ch = rec.charAt(i);
					if (ch != ' ' && ch != '\t')
					{
						atStart = false;
						break;
					}
				}
				if (atStart) return false;
			}
			
			foundIt(_currentLine, recLen);
			_txt = rec.substring(_currentChar, recLen);
			return true;
		}
	}
}
