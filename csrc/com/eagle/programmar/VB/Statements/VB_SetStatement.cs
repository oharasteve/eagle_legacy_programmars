// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Variable = com.eagle.programmar.VB.VB_Variable;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class VB_SetStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/set-statement") com.eagle.programmar.VB.Terminals.VB_Keyword SET = new com.eagle.programmar.VB.Terminals.VB_Keyword("set");
		public @DOC("statements/set-statement") VB_Keyword SET = new VB_Keyword("set");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Variable var;
		public VB_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.VB_Expression expr;
		public VB_Expression expr;
	}

}
