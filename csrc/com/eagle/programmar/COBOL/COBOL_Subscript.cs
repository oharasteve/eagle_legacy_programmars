// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

namespace com.eagle.programmar.COBOL
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_PunctuationChoice = com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class COBOL_Subscript : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_SubscriptType type;
		public COBOL_SubscriptType type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class COBOL_SubscriptType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
			public COBOL_Keyword XXALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_RegularSubscript XXregularSubscript;
			public COBOL_RegularSubscript XXregularSubscript;
		}

		public class COBOL_RegularSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_SubscriptRange range;
			public  OPT;

			public class COBOL_SubscriptRange : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice colon = new com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice(":", ",");
				public COBOL_PunctuationChoice colon = new COBOL_PunctuationChoice(":", ",");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_Expression expr;
				public COBOL_Expression expr;
			}
		}

		public virtual int getSubscriptValue(EagleInterpreter interpreter)
		{
			AbstractToken which = type.getWhich();
			if (!(which is COBOL_RegularSubscript))
			{
				throw new Exception("Cannot handle " + which);
			}

			COBOL_RegularSubscript subscr = (COBOL_RegularSubscript) which;
			if (subscr.range != null && subscr.range.isPresent())
			{
				throw new Exception("Cannot handle subscript ranges yet");
			}
			return interpreter.getIntValue(subscr.expr);
		}
	}

}
