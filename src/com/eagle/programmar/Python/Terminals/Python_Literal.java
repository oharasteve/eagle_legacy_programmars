// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import java.util.ArrayList;

import com.eagle.io.EaglePrinter;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Expressions.Python_Literals;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Python_Literal extends TerminalLiteralToken
{
	private static final String PREFIXES = "bfru";

	public Python_Literal()
	{
		super("\"'", true, '\\', false, false);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();

		// Pick up the prefix(es), if they are present
		char pre1 = ' ';
		char pre2 = ' ';
		if (_currentChar < nc) pre1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) pre2 = rec.charAt(_currentChar + 1);

		int prefixLen = 0;
		if (PREFIXES.indexOf(pre1) >= 0)
		{
			prefixLen++;
			if (PREFIXES.indexOf(pre2) >= 0)
			{
				prefixLen++;
			}
		}
		_currentChar += prefixLen;

		// Pick up the next three characters, if they are present
		char ch1 = ' ';
		char ch2 = ' ';
		char ch3 = ' ';
		if (_currentChar < nc) ch1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) ch2 = rec.charAt(_currentChar + 1);
		if (_currentChar + 2 < nc) ch3 = rec.charAt(_currentChar + 2);

		boolean ok;
		if (ch1 == '\'' || ch1 == '"')
		{
			lines.setCurrentChar(_currentChar);
			lines.setCurrentLine(_currentLine);
			if (ch2 == ch1 && ch3 == ch1)
			{
				String triple = (ch1 == '"' ? "\"\"\"" : "'''"); // Either ''' or """
				ok = parseLiteral3(lines, rec, triple, triple);
			}
			else
			{
				ok = super.parse(lines);
			}
		}
		else
		{
			ok = false;
		}

		if (ok)
		{
			if (prefixLen == 1)
			{
				_txt = pre1 + _txt;
			}
			else if (prefixLen == 2)
			{
				_txt = pre1 + pre2 + _txt;
			}
		}
		_currentChar -= prefixLen;
		return ok;
	}

	@Override
	public String description()
	{
		return "python literal";
	}

	public static Python_Literal generateLiteral(String value, AbstractToken source)
	{
		Python_Literal lit = new Python_Literal();
		String val = '\"' + value
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\\\\\\\\n", "\\\\n")
				.replaceAll("\"", "\\\\\"")
				// .replaceAll("'", "\\\\'")
				.replaceAll("\n", "\\n") + '\"';
		lit.setValue(val);
		lit.setTransformationSource(source);
		return lit;
	}

	public Python_Expression generateConcatLiteral(ArrayList<Python_Expression> pieces, AbstractToken source)
	{
		StringBuffer sb = new StringBuffer();
		EaglePrinter prt = new EaglePrinter();
		for (Python_Expression piece : pieces)
		{
			AbstractToken which1 = piece.getWhich();
//			if (which1 instanceof Python_Str_Function)
//			{
//				Python_Str_Function strFn = (Python_Str_Function) which1;
//				which1 = strFn.expression.getWhich();
//			}
//
			if (which1 instanceof Python_Literals)
			{
				Python_Literals lits = (Python_Literals) which1;
				for (Python_Literal lit : lits.literals._elements)
				{
					sb.append(lit.removeQuotes());
				}
			}
			else
			{
				sb.append("{");
				sb.append(prt.writeToken(which1));
				sb.append("}");
			}
		}
		
		Python_Literal lit = new Python_Literal();
		lit.setValue("f'" + sb.toString() + "'");
		Python_Literals lits = new Python_Literals();
		lits.literals = new TokenList<Python_Literal>();
		lits.literals.addToken(lit);
		lits.setTransformationSource(source);
		return Python_Generator.wrapExpression(lits);
	}
	
	public static Python_Expression generateLiteralExpression(String value, AbstractToken source)
	{
		Python_Literals literals = Python_Literals.generateLiterals(value, source);
		return Python_Generator.wrapExpression(literals);
	}
}
