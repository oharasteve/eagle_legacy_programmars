// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Type = com.eagle.programmar.Ada.Ada_Type;
	using Ada_Variable_Definition = com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
	using Ada_Punctuation = com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> ids;
		public SeparatedList<Ada_Variable_Definition, PunctuationComma> ids;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Type type;
		public Ada_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Ada_DataInitialValue initial;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class Ada_DataInitialValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Punctuation colonEquals = new com.eagle.programmar.Ada.Terminals.Ada_Punctuation(":=");
			public Ada_Punctuation colonEquals = new Ada_Punctuation(":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Ada_Expression value;
			public Ada_Expression value;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (initial != null && initial.isPresent())
			{
				EagleValue val = interpreter.getEagleValue(initial.value);
				Ada_Variable_Definition var = ids.first();
				interpreter.setSymbol(var, var.getValue(), val);
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			AbstractType newType = type.convertType(generator);
			AbstractExpression newInit = null;

			if (initial != null && initial.isPresent())
			{
				newInit = transformer.transformExpression(generator, initial.value);
			}

			int numIds = ids.getPrimaryCount();
			for (int i = 0; i < numIds; i++)
			{
				Ada_Variable_Definition id = ids.getPrimaryElement(i);
				string varName = id.getValue();
				AbstractStatement data = generator.newDataDeclaration(false, varName, null, newType, newInit, this);
				if (data != null)
				{
					result.Add(data);
				}
			}
			return result;
		}
	}
}
