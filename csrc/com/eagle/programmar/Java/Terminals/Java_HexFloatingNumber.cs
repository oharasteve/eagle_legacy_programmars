// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

namespace com.eagle.programmar.Java.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using TerminalHexNumberToken = com.eagle.tokens.terminals.TerminalHexNumberToken;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	/*
	 * See https://docs.oracle.com/javase/specs/jls/se7/html/jls-3.html#jls-3.10.2
	 * Examples: 0x1.0p-53 0x1.0p-126f
	 */

	public class Java_HexFloatingNumber : TerminalToken, EagleRunnable, EagleTransformableExpression
	{
		protected internal string _numberAsText;

		public override DisplayStyle DisplayStyle
		{
			get
			{
				return DisplayStyle.NUMBER;
			}
		}

		// Make it a little easier to read
		private static bool isHex(char ch)
		{
			return TerminalHexNumberToken.HEX.IndexOf(ch) >= 0;
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar + 3 >= recLen)
			{
				return false;
			}
			char ch1 = rec.charAt(_currentChar);
			char ch2 = rec.charAt(_currentChar + 1);
			char ch3 = rec.charAt(_currentChar + 2);
			char ch4 = rec.charAt(_currentChar + 3);

			if (ch1 == '0' && (ch2 == 'x' || ch2 == 'X') && (isHex(ch3) || ((ch3 == '+' || ch3 == '-' || ch3 == '.') && isHex(ch4))))
			{
				int endChar = _currentChar + 2;
				bool foundExponent = false;
				bool foundDecimalPoint = false;
				bool foundDigit = false;
				while (true)
				{
					endChar++;
					if (endChar >= recLen)
					{
						break;
					}
					char ch = rec.charAt(endChar);
					// No hex allowed after the exponent
					bool validDigit = (foundExponent ? char.IsDigit(ch) : isHex(ch));
					if (validDigit)
					{
						foundDigit = true;
					}
					else
					{
						if (!foundDecimalPoint && !foundExponent && ch == '.')
						{
							foundDecimalPoint = true;
							continue;
						}

						// Uses p instead of e
						if (foundDigit && !foundExponent && (ch == 'p' || ch == 'P'))
						{
							if (endChar + 1 < recLen)
							{
								ch = rec.charAt(endChar + 1);
								if (ch == '+' || ch == '-')
								{
									endChar++;
								}
							}
							foundExponent = true;
							continue;
						}

						// Allow underscores between digits (but not in the exponent)
						if (!foundExponent && ch == '_')
						{
							if (endChar == _currentChar || endChar + 1 >= recLen)
							{
								return false;
							}
							if (!isHex(rec.charAt(endChar - 1)))
							{
								return false;
							}
							if (!isHex(rec.charAt(endChar + 1)))
							{
								return false;
							}
							continue; // Keep the underscore in the token
						}

						// Check for suffix (float or double)
						if (ch == 'f' || ch == 'F' || ch == 'd' || ch == 'D')
						{
							endChar++;
						}

						break;
					}
				}

				if (!foundExponent)
				{
					return false; // The 'p' is required
				}
				foundIt(_currentLine, endChar - 1);
				_numberAsText = rec.substring(_currentChar, endChar - _currentChar);
				return true;
			}
			return false;
		}

		public override string ToString()
		{
			return _numberAsText;
		}

		public override string Value
		{
			set
			{
				_numberAsText = value;
				setPresent(!string.ReferenceEquals(value, null));
			}
			get
			{
				return _numberAsText;
			}
		}


		public override string showString()
		{
			return "Number";
		}

		public override string description()
		{
			return "A hex floating number";
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			double value = double.Parse(_numberAsText);
			interpreter.pushDouble(value);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			double value = double.Parse(_numberAsText);
			return generator.newNumberExpression(Convert.ToString(value), this);
		}
	}

}
