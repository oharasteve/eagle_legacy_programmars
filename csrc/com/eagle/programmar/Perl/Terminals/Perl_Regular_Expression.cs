// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2014

namespace com.eagle.programmar.Perl.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TerminalRegularExpression = com.eagle.tokens.terminals.TerminalRegularExpression;

	public class Perl_Regular_Expression : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_RegularSubstitution extends com.eagle.tokens.terminals.TerminalRegularExpression
		public class Perl_RegularSubstitution : TerminalRegularExpression
		{
			public override bool parse(EagleFileReader lines)
			{
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar + 2 >= recLen)
				{
					return false;
				}

				char ch = rec.charAt(_currentChar);
				if (ch == 's')
				{
					char marker = rec.charAt(_currentChar + 1);
					if (marker == '/')
					{
						int middle = myIndexOf(rec, marker, _currentChar + 2);
						if (middle > 0)
						{
							int endChar = myIndexOf(rec, marker, middle + 1);
							if (endChar > 0)
							{
								if (endChar + 1 < recLen)
								{
									if (rec.charAt(endChar + 1) == 'g')
									{
										endChar++;
									}
									foundIt(_currentLine, endChar);
									return true;
								}
							}
						}
					}
				}

				return false;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_RegularTranslation extends com.eagle.tokens.terminals.TerminalRegularExpression
		public class Perl_RegularTranslation : TerminalRegularExpression
		{
			public override bool parse(EagleFileReader lines)
			{
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar + 3 >= recLen)
				{
					return false;
				}

				char ch = rec.charAt(_currentChar);
				if (ch == 't')
				{
					ch = rec.charAt(_currentChar + 1);
					if (ch == 'r')
					{
						char marker = rec.charAt(_currentChar + 2);
						if (marker == '/')
						{
							int middle = myIndexOf(rec, marker, _currentChar + 3);
							if (middle > 0)
							{
								int endChar = myIndexOf(rec, marker, middle + 1);
								if (endChar > 0)
								{
									if (endChar + 1 < recLen)
									{
										foundIt(_currentLine, endChar);
										return true;
									}
								}
							}
						}
					}
				}

				return false;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_RegularQuery extends com.eagle.tokens.terminals.TerminalRegularExpression
		public class Perl_RegularQuery : TerminalRegularExpression
		{
			public override bool parse(EagleFileReader lines)
			{
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar + 3 >= recLen)
				{
					return false;
				}

				char ch = rec.charAt(_currentChar);
				if (ch == 'q')
				{
					ch = rec.charAt(_currentChar + 1);
					if (ch == 'r' || ch == 'q')
					{
						char marker = rec.charAt(_currentChar + 2);
						if (marker == '/')
						{
							int endChar = myIndexOf(rec, marker, _currentChar + 3);
							if (endChar > 0)
							{
								if (endChar + 1 < recLen)
								{
									foundIt(_currentLine, endChar);
									return true;
								}
							}
						}
					}
				}

				return false;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_RegularMatch extends com.eagle.tokens.terminals.TerminalRegularExpression
		public class Perl_RegularMatch : TerminalRegularExpression
		{
			public override bool parse(EagleFileReader lines)
			{
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar + 3 >= recLen)
				{
					return false;
				}

				char ch = rec.charAt(_currentChar);
				if (ch == 'm')
				{
					char marker = rec.charAt(_currentChar + 1);
					if (marker == '/')
					{
						int endChar = myIndexOf(rec, marker, _currentChar + 2);
						if (endChar > 0)
						{
							if (endChar + 1 < recLen)
							{
								foundIt(_currentLine, endChar);
								return true;
							}
						}
					}
				}

				return false;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_RegularCondition extends com.eagle.tokens.terminals.TerminalRegularExpression
		public class Perl_RegularCondition : TerminalRegularExpression
		{
			public override bool parse(EagleFileReader lines)
			{
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar + 2 >= recLen)
				{
					return false;
				}

				char marker = rec.charAt(_currentChar);
				if (marker == '/')
				{
					int endChar = myIndexOf(rec, marker, _currentChar + 1);
					if (endChar > 0)
					{
						if (endChar + 1 < recLen)
						{
							if (rec.charAt(endChar + 1) == 'i')
							{
								endChar++;
							}
							foundIt(_currentLine, endChar);
							return true;
						}
					}
				}

				return false;
			}
		}

		internal static int myIndexOf(EagleLineReader rec, char marker, int sc)
		{
			int len = rec.length();
			char prev = '?';
			for (int i = sc; i < len; i++)
			{
				char ch = rec.charAt(i);
				if (ch == marker && prev != '\\')
				{
					return i; // Found it!
				}
				prev = ch;
			}
			return -1; // Not found
		}
	}
}
