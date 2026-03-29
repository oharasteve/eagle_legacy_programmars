// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Element = com.eagle.programmar.VB.VB_Element;
	using VB_EndOfLine = com.eagle.programmar.VB.Terminals.VB_EndOfLine;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_BeginStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword BEGIN = new com.eagle.programmar.VB.Terminals.VB_Keyword("begin");
		public VB_Keyword BEGIN = new VB_Keyword("begin");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_EndOfLine eoln;
		public VB_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.VB.VB_Element> stmts;
		public TokenList<VB_Element> stmts;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.Terminals.VB_Keyword END = new com.eagle.programmar.VB.Terminals.VB_Keyword("end");
		public VB_Keyword END = new VB_Keyword("end");

	}

}
