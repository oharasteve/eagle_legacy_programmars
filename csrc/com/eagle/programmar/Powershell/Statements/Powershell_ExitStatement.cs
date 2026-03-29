// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Powershell.Statements
{
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Powershell_ExitStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("chapter-08?view=powershell-5.1#851-the-exit-statement") com.eagle.programmar.Powershell.Terminals.Powershell_Keyword EXIT = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Exit");
		public @DOC("chapter-08?view=powershell-5.1#851-the-exit-statement") Powershell_Keyword EXIT = new Powershell_Keyword("Exit");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_Expression code;
		public @OPT Powershell_Expression code;
	}

}
