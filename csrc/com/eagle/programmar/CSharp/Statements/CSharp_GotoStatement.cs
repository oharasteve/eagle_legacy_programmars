// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 13, 2022

namespace com.eagle.programmar.CSharp.Statements
{
	using CSharp_Label_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Label_Reference;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class CSharp_GotoStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword GOTO = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("goto");
		public CSharp_Keyword GOTO = new CSharp_Keyword("goto");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_GoWhere where;
		public CSharp_GoWhere where;

		public class CSharp_GoWhere : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Keyword XXDEFAULT = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("default");
			public CSharp_Keyword XXDEFAULT = new CSharp_Keyword("default"); // Weird -- inside a switch / case
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Label_Reference XXlabel;
			public CSharp_Label_Reference XXlabel;
		}
	}

}
