// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using C_Variable_Definition = com.eagle.programmar.C.Symbols.C_Variable_Definition;
	using C_Comment = com.eagle.programmar.C.Terminals.C_Comment;
	using C_FunctionPointer = com.eagle.programmar.C.Types.C_FunctionPointer;
	using C_TypeStar = com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
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

	public class C_Data : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_FunctionPointer XXfunctionPointer;
		public C_FunctionPointer XXfunctionPointer;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class C_RegularData extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnable, com.eagle.tokens.interfaces.AbstractStatement, com.eagle.transform.EagleTransformableStatementList
		public class C_RegularData : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<C_DataModifiers> modifiers1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) C_Type ctype;
			public C_Type ctype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<C_DataModifiers> modifiers2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.Symbols.C_Variable_Definition id;
			public C_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<C_Subscript> subscripts;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT C_DataInitialValue initialValue;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<C_MoreIdentifiers> moreIds;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.C.Terminals.C_Comment> comments2;
			public  OPT;

			public class C_MoreIdentifiers : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar> stars;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Symbols.C_Variable_Definition id;
				public C_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<C_Subscript> subscripts;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT C_DataInitialValue initialValue;
				public  OPT;
			}

			public override void interpret(EagleInterpreter interpreter)
			{
				if (initialValue != null && initialValue.isPresent())
				{
					EagleValue value = interpreter.getEagleValue(initialValue.expression);
					interpreter.setSymbol(id, id.ToString(), value);
				}
			}

			public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				EagleGenerator.TypeEnum argType2 = ctype.findType();

				if (subscripts != null && subscripts.size() == 1)
				{
					if (argType2 == EagleGenerator.TypeEnum.CHAR)
					{
						// If we have char xx[] then it is a string
						argType2 = EagleGenerator.TypeEnum.STRING;
					}
					else if (argType2 == EagleGenerator.TypeEnum.STRING)
					{
						// If we have char *xx[] then it is a string array
						argType2 = EagleGenerator.TypeEnum.ARRAY;
					}
				}

				AbstractType newType = generator.transformType(argType2, null, this);

				string name = id.getValue();
				AbstractExpression initial = null;
				if (initialValue != null && initialValue.isPresent())
				{
					initial = transformer.transformExpression(generator, initialValue.expression);
				}
				result.Add(generator.newDataDeclaration(false, name, null, newType, initial, this));

				foreach (C_MoreIdentifiers more in moreIds._elements)
				{
					name = more.id.getValue();
					initial = null;
					if (more.initialValue != null && more.initialValue.isPresent())
					{
						initial = transformer.transformExpression(generator, more.initialValue.expression);
					}
					result.Add(generator.newDataDeclaration(false, name, null, newType, initial, this));
				}

				return result;
			}
		}
	}

}
