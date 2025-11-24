// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleMatrix;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Definition;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_DimStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Keyword DIM = new Basic_Keyword("DIM");
	public @S(20) SeparatedList<Basic_DimEntry, PunctuationComma> values;

	public static class Basic_DimEntry extends TokenSequence
	{
		public @S(10) Basic_Identifier_Definition id;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<Basic_Expression, PunctuationComma> dimensions;
		public @S(40) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
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
				throw new RuntimeException("DIM must have exactly one dimension");
			}
		}
	}
}
