// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_EOLN = com.eagle.programmar.Julia.Terminals.Julia_EOLN;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Julia_PrintlnStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("base/io-network/#Base.println") com.eagle.programmar.Julia.Terminals.Julia_Keyword PRINTLN = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("println");
		public @DOC("base/io-network/#Base.println") Julia_Keyword PRINTLN = new Julia_Keyword("println");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Julia_Expression expr;
		public Julia_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln;
		public Julia_EOLN eoln;

		public void interpret(EagleInterpreter interpreter)
		{
			string val = interpreter.getStrValue(expr);
			Console.WriteLine(val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = transformer.transformExpression(generator, expr);
			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
		}
	}

}
