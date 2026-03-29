// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Bash
{
	using Bash_Filename = com.eagle.programmar.Bash.Terminals.Bash_Filename;
	using Bash_Literal = com.eagle.programmar.Bash.Terminals.Bash_Literal;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
	using Bash_RealEndOfLine = com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationAmpersand = com.eagle.tokens.punctuation.PunctuationAmpersand;

	public class Bash_EndOfLine : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Bash_Redirect> redirect;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Bash_LineEnder ender;
		public Bash_LineEnder ender;

		public class Bash_Redirect : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice direction = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("<", ">", ">>", "&>", "&>>", "1>", "2>");
			public Bash_PunctuationChoice direction = new Bash_PunctuationChoice("<", ">", ">>", "&>", "&>>", "1>", "2>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Bash_RedirectTo where;
			public Bash_RedirectTo where;

			public class Bash_RedirectTo : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Literal XXliteral;
				public Bash_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Filename XXfileName;
				public Bash_Filename XXfileName;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_RedirectToNumber extends com.eagle.tokens.TokenSequence
				public class Bash_RedirectToNumber : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationAmpersand ampersand;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Terminals.Bash_Number number;
					public Bash_Number number;
				}
			}
		}

		public class Bash_LineEnder : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_RealEndOfLine XXeoln;
			public Bash_RealEndOfLine XXeoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_Piper extends com.eagle.tokens.TokenSequence
			public class Bash_Piper : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice separator = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice(",", "|", ";", "||", "&&");
				public Bash_PunctuationChoice separator = new Bash_PunctuationChoice(",", "|", ";", "||", "&&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Bash_RealEndOfLine eoln;
				public  OPT;
			}
		}
	}

}
