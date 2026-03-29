// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

namespace com.eagle.programmar.Delphi
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using TerminalEndOfLine = com.eagle.tokens.terminals.TerminalEndOfLine;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Delphi_Configuration : AbstractLanguage
	{
		public const string DELPHIConfig = "Delphi_Configuration";

		public class Delphi_Config_Syntax : EagleSyntax
		{
			public const bool IS_CASE_SENSITIVE = false;

			public override string syntaxId()
			{
				return "Delphi Config";
			}

			public Delphi_Config_Syntax()
			{
				_isCaseSensitive = IS_CASE_SENSITIVE;
				_continuationChar = "\\";
				_extraCharacters = "";
				_autoAdvance = false;
			}
		}

		public class Delphi_Config_EndOfLine : TerminalEndOfLine
		{
		}

		public Delphi_Configuration() : base(DELPHIConfig, new Delphi_Config_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return null;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Delphi_Config_EndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<Delphi_Config_Block> blocks;
		public TokenList<Delphi_Config_Block> blocks;

		public class Delphi_Config_Block : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Delphi_Config_Punctuation leftBracket = new Delphi_Config_Punctuation('[');
			public Delphi_Config_Punctuation leftBracket = new Delphi_Config_Punctuation('[');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Delphi_Config_Value blockName = new Delphi_Config_Value("]");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Delphi_Config_Punctuation rightBracket = new Delphi_Config_Punctuation(']');
			public Delphi_Config_Punctuation rightBracket = new Delphi_Config_Punctuation(']');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Delphi_Config_EndOfLine eoln;
			public Delphi_Config_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<Delphi_Config_Line> lines;
			public TokenList<Delphi_Config_Line> lines;

			public class Delphi_Config_Line : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Delphi_Config_Value id = new Delphi_Config_Value("=]");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Delphi_Config_Punctuation equals = new Delphi_Config_Punctuation('=');
				public Delphi_Config_Punctuation equals = new Delphi_Config_Punctuation('=');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Config_Value value = new Delphi_Config_Value("]");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Delphi_Config_EndOfLine eoln;
				public Delphi_Config_EndOfLine eoln;
			}
		}

		public class Delphi_Config_Value : TerminalLiteralToken
		{
			internal string _stoppers;

			public Delphi_Config_Value() : this("")
			{
			}

			public Delphi_Config_Value(string stoppers)
			{
				_stoppers = stoppers;
			}

			public override bool parse(EagleFileReader lines)
			{
				if (findStart(lines) == FOUND.EOF)
				{
					return false;
				}
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar >= recLen)
				{
					return false;
				}
				char ch = rec.charAt(_currentChar);
				if (_stoppers.IndexOf(ch) >= 0)
				{
					return false;
				}

				_endChar = _currentChar;
				while (true)
				{
					_endChar++;
					if (_endChar >= recLen)
					{
						break;
					}
					ch = rec.charAt(_endChar);
					if (_stoppers.IndexOf(ch) >= 0)
					{
						break;
					}
				}
				_txt = rec.substring(_currentChar, (_endChar - 1) - _currentChar);

				foundIt(_currentLine, _endChar - 1);
				return true;
			}

			public override string description()
			{
				return "configuration value";
			}
		}

		public class Delphi_Config_Punctuation : TerminalPunctuationToken
		{
			// Need default constructor for reading from the XML file
			public Delphi_Config_Punctuation() : this('\0')
			{
			}

			public Delphi_Config_Punctuation(char punct) : base(punct)
			{
			}
		}
	}

}
