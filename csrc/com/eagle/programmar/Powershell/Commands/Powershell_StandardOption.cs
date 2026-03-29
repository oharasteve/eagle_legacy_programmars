// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Powershell.Commands
{
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Powershell_StandardOption : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_ErrorAction extends com.eagle.tokens.TokenSequence
		public class Powershell_ErrorAction : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword ERROR = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("-ErrorAction");
			public Powershell_Keyword ERROR = new Powershell_Keyword("-ErrorAction");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice ACTION = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Ignore", "SilentlyContinue");
			public Powershell_KeywordChoice ACTION = new Powershell_KeywordChoice("Ignore", "SilentlyContinue");
		}
	}

}
