// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_Literal = com.eagle.programmar.Bash.Terminals.Bash_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_ReadCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword READ = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("read");
		public Bash_Keyword READ = new Bash_Keyword("read");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_ReadOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference> ids;
		public TokenList<Bash_Identifier_Reference> ids;

		public class Bash_ReadOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_ReadPrompt extends com.eagle.tokens.TokenSequence
			public class Bash_ReadPrompt : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice opt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-p");
				public Bash_KeywordChoice opt = new Bash_KeywordChoice("-p");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Literal prompt;
				public Bash_Literal prompt;
			}
		}
	}

}
