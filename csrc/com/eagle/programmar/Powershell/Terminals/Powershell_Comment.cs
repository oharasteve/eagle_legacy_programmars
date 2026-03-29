// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell.Terminals
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

	public class Powershell_Comment : TerminalCommentToken, EagleTransformableStatement
	{
		// Need a default constructor for the parser
		public Powershell_Comment() : this("")
		{
		}

		public Powershell_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			if (_currentChar < rec.length())
			{
				char ch1 = rec.charAt(_currentChar);
				if (ch1 == '#')
				{
					return base.possibleCommentToEndOfLine(rec, "#");
				}
			}

			if (_currentChar + 1 < rec.length())
			{
				char ch1 = rec.charAt(_currentChar);
				if (ch1 == '/')
				{
					char ch2 = rec.charAt(_currentChar + 1);
					if (ch2 == '/')
					{
						return base.possibleCommentToEndOfLine(rec, "//");
					}
					if (ch2 == '*')
					{
						return base.possibleCommentPair2(lines, rec, "/*", "*/");
					}
				}
				else if (ch1 == '<')
				{
					char ch2 = rec.charAt(_currentChar + 1);
					if (ch2 == '#')
					{
						return base.possibleCommentPair2(lines, rec, "<#", "#>");
					}
				}
			}
			return false;
		}

		public override string description()
		{
			return "/* comment */ or # or // comment to end of line";
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return null; // Suppose we could save it ...
		}
	}

}
