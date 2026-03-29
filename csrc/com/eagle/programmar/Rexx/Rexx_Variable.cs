// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleHash = com.eagle.math.EagleHash;
	using EagleValue = com.eagle.math.EagleValue;
	using Rexx_Identifier_Reference = com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Rexx_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference var;
		public Rexx_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Rexx_Subscript subscript;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.findSymbol(var.ToString());
			if (subscript != null && subscript.isPresent())
			{
				EagleHash hash = (EagleHash) val;
				int? key = Convert.ToInt32(interpreter.getIntValue(subscript.subscr));
				interpreter.pushEagleValue(hash.getValue(key));
			}
			else
			{
				interpreter.pushEagleValue(val);
			}
		}
	}

}
