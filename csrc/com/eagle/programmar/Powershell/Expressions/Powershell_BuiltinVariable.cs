// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using Powershell_PunctuationChoice = com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Powershell_BuiltinVariable : PrimaryOperator
	{
		// Doesn't work to put this up at the top. Ends up with just a
		// Powershell_Punctuation('\0')
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice dollarQuestion = new com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice("$?", "$_");
		public Powershell_PunctuationChoice dollarQuestion = new Powershell_PunctuationChoice("$?", "$_");
	}

}
