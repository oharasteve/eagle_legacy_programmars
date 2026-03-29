// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_Comment : TerminalCommentToken, EagleTransformableStatement
	{
		public SQL_Comment() : this("")
		{
		}

		public SQL_Comment(string comment) : base(comment)
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
			char ch = rec.charAt(_currentChar);
			if (ch == '#')
			{
				foundIt(_currentLine, nc);
				_comment = rec.substring(_currentChar, nc - _currentChar);
				return true;
			}

			if (ch == '-')
			{
				return base.possibleCommentToEndOfLine(rec, "--");
			}

			if (ch == '/' && _currentChar + 1 < nc && rec.charAt(_currentChar + 1) == '*')
			{
				return base.possibleCommentPair2(lines, rec, "/*", "*/");
			}

			if (_currentChar + 2 < nc && string.ReferenceEquals(char.ToUpper(ch), 'R') && string.ReferenceEquals(char.ToUpper(rec.charAt(_currentChar + 1)), 'E') && string.ReferenceEquals(char.ToUpper(rec.charAt(_currentChar + 2)), 'M'))
			{
				foundIt(_currentLine, nc);
				_comment = rec.substring(_currentChar, nc - _currentChar);
				return true;
			}

			return false;
		}

		public override string description()
		{
			return "/* comment */ or -- comment to end of line";
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return null;
		}
	}

}
