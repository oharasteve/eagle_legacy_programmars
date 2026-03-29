// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish.Terminals
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using Eaglish_Format = com.eagle.programmar.Eaglish.Eaglish_Format;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Literal : TerminalLiteralToken, EagleTransformableExpression
	{
		public Eaglish_Literal() : base("\"", true, '\\', false, false)
		{
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string result = Eaglish_Format.format(interpreter, removeQuotes());
			interpreter.pushStr(result);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (_txt.IndexOf('^') >= 0)
			{
				return Eaglish_Format.compile(transformer, generator, removeQuotes(), this);
			}

			// Plain ol' literal
			return generator.newLiteralExpression(removeQuotes(), this);
		}
	}

}
