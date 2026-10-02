// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara
// Rewritten on the shane branch, Oct 1, 2026: a template literal is scanned with its ${...}
// substitutions, which may hold braces, strings and further template literals, so a nested
// backtick no longer ends the outer one. The text between the backticks stays one token.

package com.eagle.programmar.Javascript.Terminals;

import java.util.ArrayDeque;
import java.util.Deque;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Javascript_TemplateLiteral extends TerminalLiteralToken
{
	public Javascript_TemplateLiteral()
	{
		super("`", true, '\\', false, true);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		int line = _currentLine;
		EagleLineReader rec = lines.get(line);
		int ch = _currentChar;
		if (ch >= rec.length() || rec.charAt(ch) != '`') return false;

		// -1 on the stack is "inside a template"; a number is "inside ${...}" with that many
		// open braces beyond the one that started it.
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.push(-1);
		StringBuilder txt = new StringBuilder("`");
		ch++;
		int numberLines = lines.numberLines();
		while (true)
		{
			if (ch >= rec.length())
			{
				// A template may span lines; a substitution may too.
				line++;
				if (line >= numberLines) return false;
				rec = lines.get(line);
				ch = 0;
				txt.append('\n');
				continue;
			}
			char c = rec.charAt(ch);
			int mode = stack.peek();
			if (mode < 0)
			{
				// Template text
				if (c == '\\' && ch + 1 < rec.length())
				{
					txt.append(c).append(rec.charAt(ch + 1));
					ch += 2;
					continue;
				}
				if (c == '`')
				{
					txt.append(c);
					stack.pop();
					if (stack.isEmpty())
					{
						_txt = txt.toString();
						_endLine = line;
						_endChar = ch;
						return true;
					}
					ch++;
					continue;
				}
				if (c == '$' && ch + 1 < rec.length() && rec.charAt(ch + 1) == '{')
				{
					txt.append("${");
					stack.push(0);
					ch += 2;
					continue;
				}
				txt.append(c);
				ch++;
				continue;
			}

			// Inside a substitution: track braces, skip strings, nest templates.
			if (c == '{')
			{
				stack.pop();
				stack.push(mode + 1);
			}
			else if (c == '}')
			{
				stack.pop();
				if (mode > 0) stack.push(mode - 1);
			}
			else if (c == '`')
			{
				stack.push(-1);
			}
			else if (c == '"' || c == '\'')
			{
				// A plain string inside the substitution, on this line.
				txt.append(c);
				ch++;
				while (ch < rec.length())
				{
					char s = rec.charAt(ch);
					txt.append(s);
					if (s == '\\' && ch + 1 < rec.length())
					{
						txt.append(rec.charAt(ch + 1));
						ch += 2;
						continue;
					}
					ch++;
					if (s == c) break;
				}
				continue;
			}
			txt.append(c);
			ch++;
		}
	}

	@Override
	public String description()
	{
		return "`template literal with ${substitutions}`";
	}
}
