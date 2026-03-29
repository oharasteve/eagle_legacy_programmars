// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Format = com.eagle.programmar.Ruby.Ruby_Format;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_Keyword = com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
	using Ruby_Literal = com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Ruby_PutsStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword PUTS = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("puts");
		public Ruby_Keyword PUTS = new Ruby_Keyword("puts");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Ruby_Expression expr;
		public Ruby_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln;
		public Ruby_EOLN eoln;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (expr.getWhich() is Ruby_Literal)
			{
				Ruby_Literal format = (Ruby_Literal) expr.getWhich();
				string formatted = Ruby_Format.format(interpreter, format.getValue());
				Console.WriteLine(formatted);
			}
			else
			{
				string line = interpreter.getStrValue(expr);
				Console.WriteLine(line);
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (expr.getWhich() is Ruby_Literal)
			{
				Ruby_Literal format = (Ruby_Literal) expr.getWhich();
				AbstractExpression newLine = Ruby_Format.compile(generator, format.getValue(), this);
				return generator.newPrintStatement(newLine, EagleGenerator.TypeEnum.STRING, true, false, this);
			}

			AbstractExpression line = transformer.transformExpression(generator, expr);
			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
		}
	}

}
