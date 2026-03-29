// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Identifier_Reference = com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_ForEachStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("statements/for-each-next-statement") com.eagle.programmar.VB.Terminals.VB_Keyword FOR = new com.eagle.programmar.VB.Terminals.VB_Keyword("for");
		public @DOC("statements/for-each-next-statement") VB_Keyword FOR = new VB_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword EACH = new com.eagle.programmar.VB.Terminals.VB_Keyword("each");
		public VB_Keyword EACH = new VB_Keyword("each");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Symbols.VB_Identifier_Reference var;
		public VB_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.Terminals.VB_Keyword IN = new com.eagle.programmar.VB.Terminals.VB_Keyword("in");
		public VB_Keyword IN = new VB_Keyword("in");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.VB_Expression from;
		public VB_Expression from;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> action;
		public TokenList<VB_Element> action;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.VB.Terminals.VB_Keyword NEXT = new com.eagle.programmar.VB.Terminals.VB_Keyword("next");
		public VB_Keyword NEXT = new VB_Keyword("next");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT VB_Identifier_Reference var2;
		public @OPT VB_Identifier_Reference var2;
	}

}
