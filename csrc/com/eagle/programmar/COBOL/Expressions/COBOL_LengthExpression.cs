// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Expressions
{
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class COBOL_LengthExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LENGTH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LENGTH");
		public COBOL_Keyword LENGTH = new COBOL_Keyword("LENGTH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword OF = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OF");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression expression;
		public COBOL_Expression expression;
	}

}
