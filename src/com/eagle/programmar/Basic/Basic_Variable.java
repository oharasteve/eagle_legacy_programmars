// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleMatrix;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Basic_Identifier_Reference var;
	public @S(20) @OPT Basic_Subscript subscripts;

	public static class Basic_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Basic_Expression,PunctuationComma> subs;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.toString());
		interpreter.pushEagleValue(value);

		EagleValue val = interpreter.findSymbol(var.toString());
		if (subscripts != null && subscripts.isPresent())
		{
			int dims = subscripts.subs.getPrimaryCount();
			if (dims == 1 && val.isArray())
			{
				EagleArray array = (EagleArray) val;
				Basic_Expression sub = subscripts.subs.first();
				int indx = interpreter.getIntValue(sub);
				interpreter.pushEagleValue(array.getValue(indx-1));
			}
			else if (dims == 2 && val.isMatrix())
			{
				EagleMatrix matrix = (EagleMatrix) val;
				Basic_Expression sub1 = subscripts.subs.getPrimaryElement(0);
				Basic_Expression sub2 = subscripts.subs.getPrimaryElement(1);
				int indx1 = interpreter.getIntValue(sub1);
				int indx2 = interpreter.getIntValue(sub2);
				interpreter.pushEagleValue(matrix.getValue(indx1 - 1, indx2 - 1));
			}
			else
			{
				throw new RuntimeException("Can only arrays and matrices");
			}
		}
		else
		{
			interpreter.pushEagleValue(val);
		}
	}
	
	// Called from Basic_Assignment.java
	// Handles subscripts here instead of there
	public void assignValue(EagleInterpreter interpreter, EagleValue value)
	{
		String varName = var.getValue();
		
		if (subscripts != null && subscripts.isPresent())
		{
			int dims = subscripts.subs.getPrimaryCount();
			if (dims == 1)
			{
				EagleValue arr = interpreter.findSymbol(varName);
				if (arr == null || ! arr.isArray())
				{
					throw new RuntimeException("Can only have subscripts on an array: " + varName);
				}
				EagleArray array = (EagleArray) arr;
				int sub = interpreter.getIntValue(subscripts.subs.first());
				array.setValue(sub - 1, value);
			}
			else if (dims == 2)
			{
				EagleValue mat = interpreter.findSymbol(varName);
				if (mat == null || ! mat.isMatrix())
				{
					throw new RuntimeException("Can only have subscripts on a matrix: " + varName);
				}
				EagleMatrix matrix = (EagleMatrix) mat;
				int sub1 = interpreter.getIntValue(subscripts.subs.getPrimaryElement(0));
				int sub2 = interpreter.getIntValue(subscripts.subs.getPrimaryElement(1));
				matrix.setValue(sub1 - 1, sub2 - 1, value);
			}
			else
			{
				throw new RuntimeException("Can only have 1 or 2 dimensions: " + varName);
			}
		}
		else
		{
			// Not an array or matrix, simple.
			interpreter.setSymbol(var, varName, value);
		}
	}
}
