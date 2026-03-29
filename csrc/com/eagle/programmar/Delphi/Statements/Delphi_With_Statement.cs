// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

namespace com.eagle.programmar.Delphi.Statements
{
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Statement = com.eagle.programmar.Delphi.Delphi_Statement;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using Delphi_Punctuation = com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Delphi_With_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Declarations_and_Statements_(Delphi)#With_Statements") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword WITH = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("With");
		public @DOC("Declarations_and_Statements_(Delphi)#With_Statements") Delphi_Keyword WITH = new Delphi_Keyword("With");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Expression expr;
		public Delphi_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Punctuation caret = new com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation("^");
		public @OPT Delphi_Punctuation caret = new Delphi_Punctuation("^");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword DO = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Do");
		public Delphi_Keyword DO = new Delphi_Keyword("Do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Delphi.Delphi_Statement stmt;
		public Delphi_Statement stmt;
	}

}
