// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Bash.Conditions
{
	using Bash_FilenameOrLiteral = com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Bash_ExistsCondition : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice E = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-d", "-e", "-f", "-n", "-x", "-z");
		public Bash_KeywordChoice E = new Bash_KeywordChoice("-d", "-e", "-f", "-n", "-x", "-z");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Bash.Bash_FilenameOrLiteral filename;
		public Bash_FilenameOrLiteral filename;
	}

}
