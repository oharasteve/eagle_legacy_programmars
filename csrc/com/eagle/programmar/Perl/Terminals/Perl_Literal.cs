// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_Literal : TerminalLiteralToken, EagleTransformableExpression
	{
		public Perl_Literal() : base("\"'`", true, '\\', false, true)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			// Check for <<<STOPPER lines STOPPER
			EagleLineReader rec = lines.get(_currentLine);
			if (multilineStopper(lines, rec, "<<<"))
			{
				return true;
			}
			if (multilineStopper(lines, rec, "<<"))
			{
				return true;
			}

			return base.parse(lines);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string text = this.removeQuotes().replaceAll("\\\\n", "").replaceAll("\\\\\\$", "\\$");
			return generator.newLiteralExpression(text, this);
		}
	}

}
