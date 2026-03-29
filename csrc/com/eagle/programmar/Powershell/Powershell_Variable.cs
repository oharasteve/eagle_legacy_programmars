// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

namespace com.eagle.programmar.Powershell
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Variable_Reference = com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Powershell_Variable : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation DOLLAR = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("$");
		public Powershell_Punctuation DOLLAR = new Powershell_Punctuation("$");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_VariableScope scope;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference id;
		public Powershell_Variable_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Powershell_Subscript subscript;
		public  OPT;

		public class Powershell_Subscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Powershell_Expression subscr;
			public Powershell_Expression subscr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public class Powershell_VariableScope : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice SCRIPT = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("env", "global", "script", "variable");
			public Powershell_KeywordChoice SCRIPT = new Powershell_KeywordChoice("env", "global", "script", "variable");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(id.ToString());
			if (subscript != null && subscript.isPresent() && value.isArray())
			{
				int sub = interpreter.getIntValue(subscript.subscr);
				EagleArray array = (EagleArray) value;
				interpreter.pushEagleValue(array.getValue(sub));
			}
			else
			{
				interpreter.pushEagleValue(value);
			}
		}

		public static string repairName(string powershellVariable)
		{
			return powershellVariable.replaceAll("\\$", "");
		}
	}

}
