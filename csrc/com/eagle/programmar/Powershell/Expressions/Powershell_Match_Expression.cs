// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_ExpressionList = com.eagle.programmar.Powershell.Powershell_ExpressionList;
	using Powershell_KeywordChoice = com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Powershell_Match_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression left = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice operator = new com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice("-cmatch", "-cnotmatch", "-contains", "-f", "-in", "-join", "-match", "-notin", "-notlike", "-notmatch", "-replace", "-split");
		public Powershell_KeywordChoice @operator = new Powershell_KeywordChoice("-cmatch", "-cnotmatch", "-contains", "-f", "-in", "-join", "-match", "-notin", "-notlike", "-notmatch", "-replace", "-split");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Powershell_ExpressionList right = new com.eagle.programmar.Powershell.Powershell_ExpressionList();
		public Powershell_ExpressionList right = new Powershell_ExpressionList();
	}

}
