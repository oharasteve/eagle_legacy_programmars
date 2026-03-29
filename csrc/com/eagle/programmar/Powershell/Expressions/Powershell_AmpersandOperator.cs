// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using Powershell_Command = com.eagle.programmar.Powershell.Statements.Powershell_Command;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Powershell_AmpersandOperator : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation AMPERSAND = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("&");
		public Powershell_Punctuation AMPERSAND = new Powershell_Punctuation("&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Statements.Powershell_Command command;
		public Powershell_Command command;
	}

}
