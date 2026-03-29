// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF
{
	using BNF_Alternation = com.eagle.programmar.BNF.Expressions.BNF_Alternation;
	using BNF_Group = com.eagle.programmar.BNF.Expressions.BNF_Group;
	using BNF_Optional = com.eagle.programmar.BNF.Expressions.BNF_Optional;
	using BNF_Rulename = com.eagle.programmar.BNF.Expressions.BNF_Rulename;
	using BNF_Literal = com.eagle.programmar.BNF.Terminals.BNF_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class BNF_Expression : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<BNF_ExpressionTerm> terms;
		public TokenList<BNF_ExpressionTerm> terms;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.BNF.Expressions.BNF_Alternation> choices;
		public  OPT;

		public class BNF_ExpressionTerm : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE BNF_Literal XXliteral;
			public BNF_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE BNF_Rulename XXrulename;
			public BNF_Rulename XXrulename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE BNF_Group XXgroup;
			public BNF_Group XXgroup;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE BNF_Optional XXoptional;
			public BNF_Optional XXoptional;
		}
	}

}
