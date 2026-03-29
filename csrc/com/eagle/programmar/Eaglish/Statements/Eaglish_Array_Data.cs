// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Variable_Definition = com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
	using Eaglish_EndOfLine = com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
	using Eaglish_Keyword = com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_Array_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword ARRAY = new com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword("ARRAY");
		public Eaglish_Keyword ARRAY = new Eaglish_Keyword("ARRAY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition var;
		public Eaglish_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Eaglish_Array_InitialValues init;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine eoln;
		public Eaglish_EndOfLine eoln;

		public class Eaglish_Array_InitialValues : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Eaglish.Eaglish_Expression, com.eagle.tokens.punctuation.PunctuationComma> values;
			public SeparatedList<Eaglish_Expression, PunctuationComma> values;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			List<EagleValue> vals = null;
			if (init.isPresent())
			{
				vals = new List<EagleValue>();
				for (int i = 0; i < init.values.getPrimaryCount(); i++)
				{
					Eaglish_Expression expr = init.values.getPrimaryElement(i);
					string val = interpreter.getStrValue(expr);
					vals.Add(new EagleString(val));
				}
			}

			EagleArray array = new EagleArray();
			array.setValues(vals);
			interpreter.setSymbol(var, var.ToString(), array);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newType = generator.transformType(EagleGenerator.TypeEnum.ARRAY, null, ARRAY);
			string name = var.getValue();
			AbstractExpression initial = null;
			if (init != null && init.isPresent())
			{
				List<AbstractExpression> vals = new List<AbstractExpression>();
				int numVals = init.values.getPrimaryCount();
				for (int i = 0; i < numVals; i++)
				{
					Eaglish_Expression expr = init.values.getPrimaryElement(i);
					AbstractExpression next = transformer.transformExpression(generator, expr);
					vals.Add(next);
				}
				initial = generator.newArrayExpression(vals, ARRAY);
			}
			return generator.newDataDeclaration(false, name, null, newType, initial, this);
		}
	}

}
