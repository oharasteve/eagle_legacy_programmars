// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_FilenameOrLiteral = com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class Bash_RmCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Bash_RmBin rmBin;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Keyword RM = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("rm");
		public Bash_Keyword RM = new Bash_Keyword("rm");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Bash_RmOption> options1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Bash_FilenameOrLiteral> files;
		public TokenList<Bash_FilenameOrLiteral> files;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Bash_RmOption> options2;
		public  OPT;

		public class Bash_RmOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-f", "-fr", "-r", "-rf");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-f", "-fr", "-r", "-rf");
		}

		public class Bash_RmBin : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash1;
			public PunctuationSlash slash1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Keyword BIN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("bin");
			public Bash_Keyword BIN = new Bash_Keyword("bin");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSlash slash2;
			public PunctuationSlash slash2;
		}
	}

}
