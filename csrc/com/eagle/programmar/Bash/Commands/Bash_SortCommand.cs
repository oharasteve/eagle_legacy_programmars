// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_Comment = com.eagle.programmar.Bash.Terminals.Bash_Comment;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_SortCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword SORT = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("sort");
		public Bash_Keyword SORT = new Bash_Keyword("sort");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_SortOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Bash_Comment comment;
		public  OPT;

		public class Bash_SortOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-r", "-n");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-r", "-n");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_SortOptionK extends com.eagle.tokens.TokenSequence
			public class Bash_SortOptionK : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword K = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("-k");
				public Bash_Keyword K = new Bash_Keyword("-k");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Number n;
				public Bash_Number n;
			}
		}
	}

}
