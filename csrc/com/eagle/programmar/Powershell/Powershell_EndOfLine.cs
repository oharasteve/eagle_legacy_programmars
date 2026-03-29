// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Powershell
{
	using Powershell_Filename = com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using Powershell_Number = com.eagle.programmar.Powershell.Terminals.Powershell_Number;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using Powershell_PunctuationChoice = com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
	using Powershell_RealEndOfLine = com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Powershell_EndOfLine : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Powershell_Redirect> redirect;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_Pipe pipe;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_RealEndOfLine eoln;
		public  OPT;

		public class Powershell_Redirect : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice direction = new com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice("<", ">", ">>", "&>", "1>", "2>", "*>");
			public Powershell_PunctuationChoice direction = new Powershell_PunctuationChoice("<", ">", ">>", "&>", "1>", "2>", "*>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Powershell_RedirectTo where;
			public Powershell_RedirectTo where;

			public class Powershell_RedirectTo : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Literal XXliteral;
				public Powershell_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Filename XXfileName;
				public Powershell_Filename XXfileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Variable XXvariable;
				public Powershell_Variable XXvariable;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_RedirectToNumber extends com.eagle.tokens.TokenSequence
				public class Powershell_RedirectToNumber : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Powershell_Punctuation ampersane = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("&");
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_Number number;
					public Powershell_Number number;
				}
			}
		}

		public class Powershell_Pipe : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_PipeAction extends com.eagle.tokens.TokenSequence
			public class Powershell_PipeAction : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice separator = new com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice("|", ";", "||", "&&");
				public Powershell_PunctuationChoice separator = new Powershell_PunctuationChoice("|", ";", "||", "&&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_RealEndOfLine eoln;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Powershell_Element statement;
				public Powershell_Element statement;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Powershell_PipeBogusSemicolon extends com.eagle.tokens.TokenSequence
			public class Powershell_PipeBogusSemicolon : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
				public @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine eoln;
				public Powershell_RealEndOfLine eoln;
			}
		}
	}

}
