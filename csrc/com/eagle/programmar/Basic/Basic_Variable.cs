// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleMatrix = com.eagle.math.EagleMatrix;
	using EagleValue = com.eagle.math.EagleValue;
	using Basic_Identifier_Reference = com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Basic_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference var;
		public Basic_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Basic_Subscript subscripts;
		public  OPT;

		public class Basic_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Basic_Expression, com.eagle.tokens.punctuation.PunctuationComma> subs;
			public SeparatedList<Basic_Expression, PunctuationComma> subs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(var.ToString());
			interpreter.pushEagleValue(value);

			EagleValue val = interpreter.findSymbol(var.ToString());
			if (subscripts != null && subscripts.isPresent())
			{
				int dims = subscripts.subs.getPrimaryCount();
				if (dims == 1 && val.isArray())
				{
					EagleArray array = (EagleArray) val;
					Basic_Expression sub = subscripts.subs.first();
					int indx = interpreter.getIntValue(sub);
					interpreter.pushEagleValue(array.getValue(indx - 1));
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
					throw new Exception("Can only handle arrays and matrices");
				}
			}
			else
			{
				interpreter.pushEagleValue(val);
			}
		}

		// Called from Basic_Assignment.java
		// Handles subscripts here instead of there
		public virtual void assignValue(EagleInterpreter interpreter, EagleValue value)
		{
			string varName = var.getValue();

			if (subscripts != null && subscripts.isPresent())
			{
				int dims = subscripts.subs.getPrimaryCount();
				if (dims == 1)
				{
					EagleValue arr = interpreter.findSymbol(varName);
					if (arr == null || !arr.isArray())
					{
						throw new Exception("Can only have subscripts on an array: " + varName);
					}
					EagleArray array = (EagleArray) arr;
					int sub = interpreter.getIntValue(subscripts.subs.first());
					array.setValue(sub - 1, value);
				}
				else if (dims == 2)
				{
					EagleValue mat = interpreter.findSymbol(varName);
					if (mat == null || !mat.isMatrix())
					{
						throw new Exception("Can only have subscripts on a matrix: " + varName);
					}
					EagleMatrix matrix = (EagleMatrix) mat;
					int sub1 = interpreter.getIntValue(subscripts.subs.getPrimaryElement(0));
					int sub2 = interpreter.getIntValue(subscripts.subs.getPrimaryElement(1));
					matrix.setValue(sub1 - 1, sub2 - 1, value);
				}
				else
				{
					throw new Exception("Can only have 1 or 2 dimensions: " + varName);
				}
			}
			else
			{
				// Not an array or matrix, simple.
				interpreter.setSymbol(var, varName, value);
			}
		}
	}

}
