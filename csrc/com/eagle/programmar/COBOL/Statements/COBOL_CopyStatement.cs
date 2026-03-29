// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TerminalToken = com.eagle.tokens.TerminalToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_CopyStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COPY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COPY");
		public COBOL_Keyword COPY = new COBOL_Keyword("COPY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_FileNameOrLiteral fileName;
		public COBOL_FileNameOrLiteral fileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_CopyIn in;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_CopyReplacing replacing;
		public  OPT;

		public class COBOL_CopyIn : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IN");
			public COBOL_Keyword IN = new COBOL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
			public COBOL_Identifier_Reference id;
		}

		public class COBOL_CopyReplacing : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REPLACING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REPLACING");
			public COBOL_Keyword REPLACING = new COBOL_Keyword("REPLACING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_CopyReplace> replacements;
			public TokenList<COBOL_CopyReplace> replacements;

			public class COBOL_CopyReplace : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression from;
				public COBOL_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
				public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression to;
				public COBOL_Expression to;
			}
		}

		public class COBOL_FileNameOrLiteral : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_FileName XXfileNmae;
			public COBOL_FileName XXfileNmae;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXliteral;
			public COBOL_Literal XXliteral;
		}

		public class COBOL_FileName : TerminalToken
		{
			internal string id;

			public override bool parse(EagleFileReader lines)
			{
				if (findStart(lines) == FOUND.EOF)
				{
					return false;
				}
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				char ch = rec.charAt(_currentChar);
				if (char.IsLetter(ch))
				{
					int endChar = _currentChar;
					while (true)
					{
						endChar++;
						if (endChar >= recLen)
						{
							break;
						}
						ch = rec.charAt(endChar);
						if (!char.IsLetterOrDigit(ch) && ch != '-' && ch != '.')
						{
							break;
						}
						if (ch == '.' && (endChar + 1 == recLen || !char.IsLetterOrDigit(rec.charAt(endChar + 1))))
						{
							break;
						}
					}

					id = rec.substring(_currentChar, endChar - _currentChar);

					foundIt(_currentLine, endChar - 1);
					return true;
				}
				return false;
			}

			public override string ToString()
			{
				return id;
			}

			public override string Value
			{
				set
				{
					id = value;
				}
			}

			public override string showString()
			{
				return "Filename";
			}

			public override string description()
			{
				return "A filename.";
			}

			public override DisplayStyle DisplayStyle
			{
				get
				{
					return DisplayStyle.IDENTIFIER;
				}
			}
		}
	}

}
