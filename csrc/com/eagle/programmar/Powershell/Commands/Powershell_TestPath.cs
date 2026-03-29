// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

namespace com.eagle.programmar.Powershell.Commands
{
	using Powershell_Filename = com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Powershell_TestPath : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword TESTPATH = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Test-Path");
		public Powershell_Keyword TESTPATH = new Powershell_Keyword("Test-Path");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Powershell_TPParam param;
		public Powershell_TPParam param;

		public class Powershell_TPParam : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Literal XXliteral;
			public Powershell_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Filename XXfileName;
			public Powershell_Filename XXfileName;
		}
	}

}
