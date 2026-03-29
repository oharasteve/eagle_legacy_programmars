// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_Literals = com.eagle.programmar.Python.Expressions.Python_Literals;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Python_Literal : TerminalLiteralToken
	{
		private const string PREFIXES = "bfru";

		public Python_Literal() : base("\"'", true, '\\', false, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();

			// Pick up the prefix(es), if they are present
			char pre1 = ' ';
			char pre2 = ' ';
			if (_currentChar < nc)
			{
				pre1 = rec.charAt(_currentChar);
			}
			if (_currentChar + 1 < nc)
			{
				pre2 = rec.charAt(_currentChar + 1);
			}

			int prefixLen = 0;
			if (PREFIXES.IndexOf(pre1) >= 0)
			{
				prefixLen++;
				if (PREFIXES.IndexOf(pre2) >= 0)
				{
					prefixLen++;
				}
			}
			_currentChar += prefixLen;

			// Pick up the next three characters, if they are present
			char ch1 = ' ';
			char ch2 = ' ';
			char ch3 = ' ';
			if (_currentChar < nc)
			{
				ch1 = rec.charAt(_currentChar);
			}
			if (_currentChar + 1 < nc)
			{
				ch2 = rec.charAt(_currentChar + 1);
			}
			if (_currentChar + 2 < nc)
			{
				ch3 = rec.charAt(_currentChar + 2);
			}

			bool ok;
			if (ch1 == '\'' || ch1 == '"')
			{
				lines.setCurrentChar(_currentChar);
				lines.setCurrentLine(_currentLine);
				if (ch2 == ch1 && ch3 == ch1)
				{
					string triple = (ch1 == '"' ? "\"\"\"" : "'''"); // Either ''' or """
					ok = parseLiteral3(lines, rec, triple, triple);
				}
				else
				{
					ok = base.parse(lines);
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

		public override string description()
		{
			return "python literal";
		}

		public static Python_Literal generateLiteral(string value, AbstractToken source)
		{
			Python_Literal lit = new Python_Literal();
			string val = '\"' + value.replaceAll("\\\\n", "\\\\n").replaceAll("\"", "\\\\\"").replaceAll("\n", "\\n") + '\"'; lit.setValue(val); lit.setTransformationSource(source); return lit;
			}
			public static Python_Expression generateLiteralExpression(string value, AbstractToken source) {Python_Literals literals = Python_Literals.generateLiterals(value, source); return Python_Generator.wrapExpression(literals);}
			}

		}
