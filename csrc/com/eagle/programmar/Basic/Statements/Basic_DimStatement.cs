// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleMatrix = com.eagle.math.EagleMatrix;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_Identifier_Definition = com.eagle.programmar.Basic.Symbols.Basic_Identifier_Definition;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Basic_DimStatement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword DIM = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("DIM");
		public Basic_Keyword DIM = new Basic_Keyword("DIM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Basic_DimEntry, com.eagle.tokens.punctuation.PunctuationComma> values;
		public SeparatedList<Basic_DimEntry, PunctuationComma> values;

		public class Basic_DimEntry : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Symbols.Basic_Identifier_Definition id;
			public Basic_Identifier_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Basic.Basic_Expression, com.eagle.tokens.punctuation.PunctuationComma> dimensions;
			public SeparatedList<Basic_Expression, PunctuationComma> dimensions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			int entries = values.getPrimaryCount();
			for (int i = 0; i < entries; i++)
			{
				Basic_DimEntry entry = values.getPrimaryElement(i);
				if (entry.dimensions.getPrimaryCount() == 1)
				{
					Basic_Expression sizeExpr = entry.dimensions.first();
					int size = interpreter.getIntValue(sizeExpr);
					EagleArray array = new EagleArray();
					array.setValue(size - 1, new EagleInteger(0)); // Fills it all with 0's
					interpreter.setSymbol(entry, entry.id.getValue(), array);
				}
				else if (entry.dimensions.getPrimaryCount() == 2)
				{
					Basic_Expression sizeExpr1 = entry.dimensions.getPrimaryElement(0);
					Basic_Expression sizeExpr2 = entry.dimensions.getPrimaryElement(1);
					int size1 = interpreter.getIntValue(sizeExpr1);
					int size2 = interpreter.getIntValue(sizeExpr2);
					EagleMatrix matrix = new EagleMatrix();
					matrix.setValue(size1 - 1, size2 - 1, new EagleInteger(0)); // Fills it all with 0's
					interpreter.setSymbol(entry, entry.id.getValue(), matrix);
				}
				else
				{
					throw new Exception("DIM must have exactly one dimension");
				}
			}
		}
	}

}
