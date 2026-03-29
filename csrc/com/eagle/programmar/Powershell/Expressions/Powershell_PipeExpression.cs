// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{
	using Powershell_Statement = com.eagle.programmar.Powershell.Powershell_Element.Powershell_Statement;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using Powershell_RealEndOfLine = com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Powershell_PipeExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression left = new com.eagle.programmar.Powershell.Powershell_Expression(this, AllowedPrecedence.ATLEAST);
		public Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation pipe = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation("|");
		public Powershell_Punctuation pipe = new Powershell_Punctuation("|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Powershell_RealEndOfLine eoln;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Powershell.Powershell_Element.Powershell_Statement statement;
		public Powershell_Statement statement;
	}
}
