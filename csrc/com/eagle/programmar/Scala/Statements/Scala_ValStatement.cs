// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Type = com.eagle.programmar.Scala.Scala_Type;
	using Scala_Variable_Definition = com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition;
	using Scala_EOLN = com.eagle.programmar.Scala.Terminals.Scala_EOLN;
	using Scala_Keyword = com.eagle.programmar.Scala.Terminals.Scala_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Scala_ValStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("taste-vars-data-types.html#two-types-of-variables") com.eagle.programmar.Scala.Terminals.Scala_Keyword VAL = new com.eagle.programmar.Scala.Terminals.Scala_Keyword("val");
		public @DOC("taste-vars-data-types.html#two-types-of-variables") Scala_Keyword VAL = new Scala_Keyword("val");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Scala.Symbols.Scala_Variable_Definition id;
		public Scala_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Scala.Scala_Type type;
		public Scala_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Scala.Scala_Expression initValue;
		public Scala_Expression initValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Scala.Terminals.Scala_EOLN eoln;
		public Scala_EOLN eoln;

		public void interpret(EagleInterpreter interpreter)
		{
			if (initValue.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(initValue);
				interpreter.setSymbol(id, id.getValue(), val);
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// See if the Definition has some assignments in the metrics file
			EagleGenerator.TypeEnum metricType = transformer.findAssignMetric(id);
			AbstractType newType = generator.transformType(metricType, null, null);

			AbstractExpression initial = transformer.transformExpression(generator, initValue);

			string name = id.getValue();
			AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
			return stmt;
		}
	}

}
