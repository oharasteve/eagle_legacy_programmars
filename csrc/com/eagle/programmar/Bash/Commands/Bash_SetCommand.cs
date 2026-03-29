// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_Comment = com.eagle.programmar.Bash.Terminals.Bash_Comment;
	using Bash_Filename = com.eagle.programmar.Bash.Terminals.Bash_Filename;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Bash_SetCommand : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Keyword SET = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("set");
		public Bash_Keyword SET = new Bash_Keyword("set");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_SetOption> options;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Bash_Comment comment;
		public  OPT;

		public class Bash_SetOption : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-e", "-eu", "-ex", "-eE", "-eux", "-u", "-x");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-e", "-eu", "-ex", "-eE", "-eux", "-u", "-x");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_SetPlus extends com.eagle.tokens.TokenSequence
			public class Bash_SetPlus : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation PLUS = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("+");
				public Bash_Punctuation PLUS = new Bash_Punctuation("+");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice EX = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("e", "ex", "x");
				public Bash_KeywordChoice EX = new Bash_KeywordChoice("e", "ex", "x");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_SetOptionO extends com.eagle.tokens.TokenSequence
			public class Bash_SetOptionO : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice O = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-eo", "-euox", "-euxo", "-o");
				public Bash_KeywordChoice O = new Bash_KeywordChoice("-eo", "-euox", "-euxo", "-o");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference id;
				public Bash_Identifier_Reference id;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_SetAssignment extends com.eagle.tokens.TokenSequence
			public class Bash_SetAssignment : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Bash_Variable var;
				public Bash_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Bash_SetValue what;
				public Bash_SetValue what;

				public class Bash_SetValue : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Filename XXfilename;
					public Bash_Filename XXfilename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Expression XXexpr;
					public Bash_Expression XXexpr;
				}
			}
		}
	}

}
