// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Type = com.eagle.programmar.Algol68.Algol68_Type;
	using Algol68_Variable_Definition = com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition;
	using Algol68_PunctuationChoice = com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Type type;
		public Algol68_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Algol68.Symbols.Algol68_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> ids;
		public SeparatedList<Algol68_Variable_Definition, PunctuationComma> ids;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Algol68_DataInitialValue init;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class Algol68_DataInitialValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice equals = new com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice("=", ":=");
			public Algol68_PunctuationChoice equals = new Algol68_PunctuationChoice("=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Expression value;
			public Algol68_Expression value;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (init != null && init.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(init.value);
				Algol68_Variable_Definition var = ids.first();
				interpreter.setSymbol(var, var.getValue(), val);
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			EagleGenerator.TypeEnum typ = Algol68_Type.findType(type);

			List<AbstractStatement> result = new List<AbstractStatement>();
			int numVars = ids.getPrimaryCount();
			for (int i = 0; i < numVars; i++)
			{
				Algol68_Variable_Definition var = ids.getPrimaryElement(i);

				AbstractExpression initial = null;
				if (init != null && init.isPresent())
				{
					initial = transformer.transformExpression(generator, init.value);
				}

				if (typ == EagleGenerator.TypeEnum.OTHER)
				{
					// See if the Definition has some assignments in the metrics file
					typ = transformer.findAssignMetric(var);
				}
				AbstractType newType = generator.transformType(typ, null, null);

				string name = var.getValue();
				AbstractStatement stmt = generator.newDataDeclaration(false, name, null, newType, initial, this);
				result.Add(stmt);
			}

			return result;
		}

	}

}
