// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL.Statements
{
	using TCL_Element = com.eagle.programmar.TCL.TCL_Element;
	using TCL_Namespace_Definition = com.eagle.programmar.TCL.Symbols.TCL_Namespace_Definition;
	using TCL_EndOfLine = com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class TCL_NamespaceStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("TclCmd/namespace.html") com.eagle.programmar.TCL.Terminals.TCL_Keyword NAMESPACE = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("namespace");
		public @DOC("TclCmd/namespace.html") TCL_Keyword NAMESPACE = new TCL_Keyword("namespace");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Terminals.TCL_Keyword EVAL = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("eval");
		public TCL_Keyword EVAL = new TCL_Keyword("eval");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.TCL.Symbols.TCL_Namespace_Definition namespace;
		public TCL_Namespace_Definition @namespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.TCL.Terminals.TCL_EndOfLine endOfLine;
		public TCL_EndOfLine endOfLine;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.TCL.TCL_Element> statements;
		public TokenList<TCL_Element> statements;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
	}

}
