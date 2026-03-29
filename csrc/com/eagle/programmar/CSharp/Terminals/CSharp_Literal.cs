// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CSharp_Literal : TerminalLiteralToken
	{
		public CSharp_Literal() : base("\"", true, '\\', false, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			if (rec.charAt(_currentChar) == '@')
			{
				lines.setCurrentChar(_currentChar + 1);
				base._hasEscape = false; // Smash these temporarily
				base._allowDoubled = true; // @"..." is an odd beast
				base._allowMultiline = true;
				if (base.parse(lines))
				{
					return true;
				}
				base._hasEscape = true; // Restore these
				base._allowDoubled = false;
				base._allowMultiline = false;
				lines.setCurrentChar(_currentChar);
				return false;
			}

			// An "interpolated" string can have {{ and }} in it
			if (rec.charAt(_currentChar) == '$')
			{
				lines.setCurrentChar(_currentChar + 1);
				if (base.parse(lines))
				{
					return true;
				}
				lines.setCurrentChar(_currentChar);
				return false;
			}

			return base.parse(lines);
		}

		public static CSharp_Literal generateLiteral(string value, AbstractToken source)
		{
			CSharp_Literal lit = new CSharp_Literal();
			string val = '"' + value.replaceAll("\\\\n", "\\\\n").replaceAll("\"", "\\\\\"") + '"'; lit.setValue(val); lit.setTransformationSource(source); return lit;
			}
			public static CSharp_Expression generateLiteralExpression(string value, AbstractToken source) {CSharp_Literal lit = CSharp_Literal.generateLiteral(value, source); return CSharp_Generator.wrapExpression(lit);}
			}

		}
