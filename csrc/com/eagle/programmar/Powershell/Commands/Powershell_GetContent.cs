// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Powershell.Commands
{
	using Powershell_Filename = com.eagle.programmar.Powershell.Terminals.Powershell_Filename;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Powershell_GetContent : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword GETCONTENT = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Get-Content");
		public Powershell_Keyword GETCONTENT = new Powershell_Keyword("Get-Content");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Powershell_GCParam param;
		public Powershell_GCParam param;

		public class Powershell_GCParam : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Literal XXliteral;
			public Powershell_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Powershell_Filename XXfileName;
			public Powershell_Filename XXfileName;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Power_GCEncoding extends com.eagle.tokens.TokenSequence
			public class Power_GCEncoding : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword ENCODING = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("-Encoding");
				public Powershell_Keyword ENCODING = new Powershell_Keyword("-Encoding");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice encoding = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("Byte", "UTF8");
				public Powershell_KeywordChoice encoding = new Powershell_KeywordChoice("Byte", "UTF8");
			}
		}
	}

}
