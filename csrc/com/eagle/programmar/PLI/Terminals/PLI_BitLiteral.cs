// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2014

namespace com.eagle.programmar.PLI.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_BitLiteral : TerminalLiteralToken, EagleRunnable, EagleTransformableExpression
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch = rec.charAt(_currentChar);
			if (ch != '\'')
			{
				return false;
			}

			int endChar = _currentChar + 1;
			while (true)
			{
				if (endChar >= recLen)
				{
					return false;
				}
				ch = rec.charAt(endChar);
				if (ch != '0' && ch != '1')
				{
					break;
				}
				endChar++;
			}

			if (endChar + 1 >= recLen)
			{
				return false;
			}
			if (rec.charAt(endChar) != '\'')
			{
				return false;
			}
			if (!string.ReferenceEquals(char.ToUpper(rec.charAt(endChar + 1)), 'B'))
			{
				return false;
			}

			_txt = rec.substring(_currentChar, (endChar + 2) - _currentChar);
			foundIt(_currentLine, endChar + 1);
			return true;
		}

		public override string showString()
		{
			return "Bit Literal";
		}

		public override string description()
		{
			return "A bit literal, like '0'B";
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_txt.equalsIgnoreCase("'1'B"))
			{
				interpreter.pushBool(true);
			}
			else if (_txt.equalsIgnoreCase("'0'B"))
			{
				interpreter.pushBool(false);
			}
			else
			{
				throw new Exception("Unexpected BIT value: " + _txt);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (_txt.equalsIgnoreCase("'1'B"))
			{
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
			}
			if (_txt.equalsIgnoreCase("'0'B"))
			{
				return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
			}
			throw new Exception("Unexpected BIT value: " + _txt);
		}
	}
}
