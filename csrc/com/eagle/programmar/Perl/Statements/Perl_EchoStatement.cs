// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.Perl.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Perl_Expression = com.eagle.programmar.Perl.Perl_Expression;
	using Perl_Keyword = com.eagle.programmar.Perl.Terminals.Perl_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Perl_EchoStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Keyword ECHO = new com.eagle.programmar.Perl.Terminals.Perl_Keyword("echo");
		public Perl_Keyword ECHO = new Perl_Keyword("echo");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Perl_Expression expr;
		public Perl_Expression expr;

		public override void interpret(EagleInterpreter interpreter)
		{
			string item = interpreter.getStrValue(expr);
			Console.WriteLine(item);
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			AbstractExpression line = transformer.transformExpression(generator, expr);
			AbstractStatement stmt = generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
			result.Add(stmt);
			return result;
		}
	}

}
