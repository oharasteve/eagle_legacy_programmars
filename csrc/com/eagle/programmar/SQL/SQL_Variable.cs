// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

namespace com.eagle.programmar.SQL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class SQL_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SQL_Punctuation AT = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation("@");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> ids;
		public SeparatedList<SQL_Identifier_Reference, PunctuationPeriod> ids;

		public override void interpret(EagleInterpreter interpreter)
		{
			SQL_Identifier_Reference id = ids.first();

			EagleValue value = interpreter.findSymbol(id.ToString());
			if (value == null)
			{
				throw new Exception("Unable to find a value for " + id.ToString());
			}
			interpreter.pushEagleValue(value);
		}
	}

}
