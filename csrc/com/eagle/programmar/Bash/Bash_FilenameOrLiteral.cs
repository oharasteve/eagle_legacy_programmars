// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

namespace com.eagle.programmar.Bash
{
	using Bash_Filename = com.eagle.programmar.Bash.Terminals.Bash_Filename;
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Bash_FilenameOrLiteral : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Filename XXfilename;
		public Bash_Filename XXfilename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_PunctuationChoice XXstar = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("*");
		public Bash_PunctuationChoice XXstar = new Bash_PunctuationChoice("*");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_Expression XXexpression;
		public Bash_Expression XXexpression;
	}

}
