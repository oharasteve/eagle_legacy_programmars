// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleHash = com.eagle.math.EagleHash;
	using EagleValue = com.eagle.math.EagleValue;
	using AWK_Identifier_Reference = com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class AWK_Variable : TokenSequence, AbstractVariable, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference id;
		public AWK_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<AWK_VarSubscript> subscripts;
		public  OPT;

		public class AWK_VarSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) AWK_Expression expr;
			public AWK_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = id.ToString();
			if (name.Equals("true", StringComparison.OrdinalIgnoreCase))
			{
				interpreter.pushBool(true);
			}
			else if (name.Equals("false", StringComparison.OrdinalIgnoreCase))
			{
				interpreter.pushBool(false);
			}
			else
			{
				EagleValue val = interpreter.findSymbol(name);
				if (subscripts != null && subscripts.isPresent() && subscripts.size() == 1)
				{
					EagleHash hash = (EagleHash) val;
					AWK_VarSubscript sub = subscripts.first();
					int key = interpreter.getIntValue(sub.expr);
					interpreter.pushEagleValue(hash.getValue(Convert.ToInt32(key)));
				}
				else
				{
					interpreter.pushEagleValue(val);
				}
			}
		}
	}

}
