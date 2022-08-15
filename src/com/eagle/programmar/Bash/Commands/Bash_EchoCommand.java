// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_EchoCommand extends TokenSequence
{
	public @S(10) @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
	public @S(20) @OPT TokenList<Bash_EchoOption> options;
	public @S(30) @OPT Bash_EchoWhat what;
	
	public static class Bash_EchoOption extends TokenChooser
	{
		public @CHOICE Bash_Keyword N = new Bash_Keyword("-n");
	}
	
	public static class Bash_EchoWhat extends TerminalLiteralToken
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF) return false;

			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			int endChar = _currentChar;
			if (endChar >= recLen) return false;

			boolean inQuotes1 = false;
			boolean inQuotes2 = false;
			while (endChar < recLen)
			{
				char ch = rec.charAt(endChar);
				if (! inQuotes2 && ch == '\'') inQuotes1 = ! inQuotes1;
				if (! inQuotes1 && ch == '"') inQuotes2 = ! inQuotes2;
				
				if (! inQuotes1 && ! inQuotes2)
				{
					if (ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == ';')
					{
						endChar--;
						break;
					}
				}
				endChar++;
			}
			
			foundIt(_currentLine, endChar);
			_txt += rec.substring(_currentChar, endChar);
			return true;
		}
	}
}
