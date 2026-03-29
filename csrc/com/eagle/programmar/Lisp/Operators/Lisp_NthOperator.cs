// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 27, 2024

namespace com.eagle.programmar.Lisp.Operators
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Lisp_List = com.eagle.programmar.Lisp.Lisp_List;
	using Lisp_Expression = com.eagle.programmar.Lisp.Lisp_Expression;
	using Lisp_Variable = com.eagle.programmar.Lisp.Lisp_Variable;
	using Lisp_Identifier_Reference = com.eagle.programmar.Lisp.Symbols.Lisp_Identifier_Reference;
	using Lisp_Keyword = com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Lisp_NthOperator : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Lisp.Terminals.Lisp_Keyword NTH = new com.eagle.programmar.Lisp.Terminals.Lisp_Keyword("NTH");
		public Lisp_Keyword NTH = new Lisp_Keyword("NTH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Lisp.Lisp_Expression index;
		public Lisp_Expression index;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Lisp.Lisp_Expression list;
		public Lisp_Expression list;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (list.getWhich() is Lisp_List)
			{
				Lisp_List array = (Lisp_List) list.getWhich();
				int k = interpreter.getIntValue(index);
				EagleValue val = interpreter.getEagleValue(array.exprs._elements.get(k));
				interpreter.pushEagleValue(val);
				return;
			}

			if (list.getWhich() is Lisp_Variable)
			{
				Lisp_Variable var = (Lisp_Variable) list.getWhich();
				if (var.getWhich() is Lisp_Identifier_Reference)
				{
					Lisp_Identifier_Reference id = (Lisp_Identifier_Reference) var.getWhich();
					EagleValue value = interpreter.findSymbol(id.getValue());
					if (value.isArray())
					{
						EagleArray array = (EagleArray) value;
						int k = interpreter.getIntValue(index);
						EagleValue val = array.getValue(k);
						interpreter.pushEagleValue(val);
						return;
					}
				}
			}

			throw new Exception("NTH requires an index and a list, not " + list.getWhich() + " " + index.getWhich());
		}
	}

}
