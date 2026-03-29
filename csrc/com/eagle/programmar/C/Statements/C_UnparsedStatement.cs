// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2013

namespace com.eagle.programmar.C.Statements
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using UnparsedElement = com.eagle.tokens.UnparsedElement;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class C_UnparsedStatement : UnparsedElement
	{
// JAVA TO C# CONVERTER WARNING: Java wildcard generics have no direct equivalent in C#:
// ORIGINAL LINE: @Override public com.eagle.tokens.TokenList<? extends com.eagle.tokens.AbstractToken> unparsedPieces()
		public override TokenList<AbstractToken> unparsedPieces()
		{
			TokenList<C_SkipToSemicolon> t = new TokenList<C_SkipToSemicolon>();
			t.addToken(unparsedStatement);
			return t;
		}

		public C_SkipToSemicolon unparsedStatement;

		public virtual string UnparsedElement
		{
			get
			{
				return unparsedStatement.ToString();
			}
		}

		public class C_SkipToSemicolon : TerminalCommentToken
		{
			public C_SkipToSemicolon() : this("")
			{
			}

			public C_SkipToSemicolon(string comment) : base(comment)
			{
			}

			public override bool parse(EagleFileReader lines)
			{
				if (findStart(lines) == FOUND.EOF)
				{
					return false;
				}

				bool inQuotes1 = false;
				bool inQuotes2 = false;
				_comment = "";
				while (_currentLine < lines.numberLines())
				{
					EagleLineReader rec = lines.get(_currentLine);
					if (rec == null)
					{
						return false;
					}
					_endChar = rec.length();
					if (_currentChar >= _endChar)
					{
						break; // What happened? Already too far?
					}
					_comment += rec.substring(_currentChar, _endChar - _currentChar).Trim();

					while (_currentChar < _endChar)
					{
						char ch = rec.charAt(_currentChar);
						if (ch == '\'' && !inQuotes2)
						{
							inQuotes1 = !inQuotes1;
						}
						if (ch == '"' && !inQuotes1)
						{
							inQuotes2 = !inQuotes2;
						}
						else if (ch == ';' && !inQuotes1 && !inQuotes2)
						{
							if (_comment.length() == 0)
							{
								return false;
							}
							foundIt(_currentLine, _endChar - 2); // Can't consume the semicolon!
							return true;
						}

						_currentChar++;
					}

					_comment += " ";
				}
				return false;
			}

			public override string description()
			{
				return "Unparsed C statement.";
			}
		}
	}

}
