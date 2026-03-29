// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 16, 2024

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_FilenameOrLiteral = com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_LnCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword LN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("ln");
		public Bash_Keyword LN = new Bash_Keyword("ln");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_LnOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_FilenameOrLiteral> files;
		public TokenList<Bash_FilenameOrLiteral> files;

		public class Bash_LnOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-f", "-fs", "-s", "-sf");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-f", "-fs", "-s", "-sf");
		}
	}

}
