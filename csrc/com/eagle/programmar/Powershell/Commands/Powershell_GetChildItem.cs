// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

namespace com.eagle.programmar.Powershell.Commands
{
	using Powershell_Variable = com.eagle.programmar.Powershell.Powershell_Variable;
	using Powershell_Filename = com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using Powershell_Word = com.eagle.programmar.Powershell.Terminals.Powershell_Word;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Powershell_GetChildItem : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice GETCHILDITEM = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Get-ChildItem", "GCI");
		public Powershell_KeywordChoice GETCHILDITEM = new Powershell_KeywordChoice("Get-ChildItem", "GCI");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Powershell_GCIparam> params;
		public  OPT;

		public class Powershell_GCIparam : TokenChooser
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
// ORIGINAL LINE: public @CHOICE Powershell_KeywordChoice XXopt = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("-Directory", "-File", "-Filter", "-Force", "-Hidden", "-Path", "-Recurse");
			public Powershell_KeywordChoice XXopt = new Powershell_KeywordChoice("-Directory", "-File", "-Filter", "-Force", "-Hidden", "-Path", "-Recurse");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_StandardOption XXstandard;
			public Powershell_StandardOption XXstandard;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Powershell_Word XXword;
			public Powershell_Word XXword;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_GCExclude extends com.eagle.tokens.TokenSequence
			public class Powershell_GCExclude : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice EXCLUDE = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("-Exclude", "-Include");
				public Powershell_KeywordChoice EXCLUDE = new Powershell_KeywordChoice("-Exclude", "-Include");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Powershell_GCIparam, com.eagle.tokens.punctuation.PunctuationComma> fileList;
				public SeparatedList<Powershell_GCIparam, PunctuationComma> fileList;
			}
		}
	}

}
