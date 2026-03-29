// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.CSharp.Expressions
{
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSharp_ConsoleRead : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @OPT CSharp_Keyword SYSTEM = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("System");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE @OPT PunctuationPeriod dot1;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Keyword CONSOLE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("Console");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationPeriod dot2;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE CSharp_KeywordChoice READ = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("OpenStandardInput", "OpenStandardOutput", "ReadKey", "ReadLine");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE @OPT SeparatedList<com.eagle.programmar.CSharp.CSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;
	}

}
