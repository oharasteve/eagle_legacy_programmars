// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Variable_Definition = com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
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

	public class Eaglish_Integer_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword INTEGER = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("INTEGER");
		public Eaglish_Keyword INTEGER = new Eaglish_Keyword("INTEGER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition var;
		public Eaglish_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Eaglish_Integer_InitialValue init;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln;
		public Eaglish_EndOfLine eoln;

		public class Eaglish_Integer_InitialValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Eaglish_Expression expression;
			public Eaglish_Expression expression;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (init != null && init.isPresent())
			{
				int x = interpreter.getIntValue(init.expression);
				EagleInteger val = new EagleInteger(x);
				interpreter.setSymbol(var, var.getValue(), val);
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newType = generator.transformType(EagleGenerator.TypeEnum.INTEGER, null, INTEGER);
			string name = var.getValue();
			AbstractExpression initial = null;
			if (init != null && init.isPresent())
			{
				initial = transformer.transformExpression(generator, init.expression);
			}
			return generator.newDataDeclaration(false, name, null, newType, initial, this);
		}
	}

}
