// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.Go.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using Go_Type = com.eagle.programmar.Go.Go_Type;
	using Go_BracesExpression = com.eagle.programmar.Go.Expressions.Go_BracesExpression;
	using Go_Variable_Definition = com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
	using Go_EOLN = com.eagle.programmar.Go.Terminals.Go_EOLN;
	using Go_Keyword = com.eagle.programmar.Go.Terminals.Go_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Go_Data : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Variables") com.eagle.programmar.Go.Terminals.Go_Keyword VAR = new com.eagle.programmar.Go.Terminals.Go_Keyword("var");
		public @DOC("#Variables") Go_Keyword VAR = new Go_Keyword("var");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Symbols.Go_Variable_Definition id;
		public Go_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Go.Go_Type type;
		public Go_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Go.Go_Expression initValue;
		public Go_Expression initValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Go.Terminals.Go_EOLN eoln;
		public Go_EOLN eoln;

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(initValue);
			interpreter.setSymbol(id, id.getValue(), val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (initValue.getWhich() is Go_BracesExpression)
			{
				AbstractType dataType = generator.transformType(EagleGenerator.TypeEnum.ARRAY, null, this);
				AbstractExpression value = transformer.transformExpression(generator, initValue);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, id.getValue(), null, dataType, value, this);
				return dataStmt;
			}

			throw new Exception("Can't handle data value: " + initValue);
		}
	}

}
