// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_FilenameOrLiteral = com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Bash_AwkCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword AWK = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("awk");
		public Bash_Keyword AWK = new Bash_Keyword("awk");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_AwkOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Bash_FilenameOrLiteral fileName;
		public  OPT;

		public class Bash_AwkOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_AwkOptionSmallF extends com.eagle.tokens.TokenSequence
			public class Bash_AwkOptionSmallF : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword f = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("-f");
				public Bash_Keyword f = new Bash_Keyword("-f");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_FilenameOrLiteral fileName;
				public Bash_FilenameOrLiteral fileName;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_AwkOptionCapitalF extends com.eagle.tokens.TokenSequence
			public class Bash_AwkOptionCapitalF : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword F = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("-F");
				public Bash_Keyword F = new Bash_Keyword("-F");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
			}
		}
	}

}
