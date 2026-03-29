// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Bash
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Bash_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Bash_Punctuation dollar = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("$");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference id;
		public Bash_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Bash_DoubleSubscript scnc;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Bash_Subscript subscript;
		public  OPT;

		public class Bash_DoubleSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon1;
			public PunctuationColon colon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Number sc;
			public Bash_Number sc;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon2;
			public PunctuationColon colon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_Number nc;
			public Bash_Number nc;
		}

		public class Bash_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Bash_Expression expr;
			public Bash_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(id.getValue());

			// Look for ${str:2:3} which means substring with sc=2 and nc=3
			if (value.isString() && scnc != null && scnc.isPresent())
			{
				int scVal = int.Parse(scnc.sc.getValue());
				int ncVal = int.Parse(scnc.nc.getValue());
				string strVal = value.forceStringValue();
				if (scVal >= 0 && ncVal >= 0 && scVal + ncVal <= strVal.Length)
				{
					strVal = strVal.Substring(scVal, ncVal);
				}
				interpreter.pushStr(strVal);
			}
			else if (subscript != null && subscript.isPresent())
			{
				EagleArray array = (EagleArray) value;
				int sub = interpreter.getIntValue(subscript.expr);
				EagleValue val = array.getValue(sub);
				interpreter.pushEagleValue(val);
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}
	}

}
