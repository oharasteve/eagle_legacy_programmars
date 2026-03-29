// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

namespace com.eagle.programmar.Delphi.Statements
{
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Delphi_Raise_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Exceptions_(Delphi)#Raising_and_Handling_Exceptions") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword RAISE = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Raise");
		public @DOC("Exceptions_(Delphi)#Raising_and_Handling_Exceptions") Delphi_Keyword RAISE = new Delphi_Keyword("Raise");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Expression exception;
		public Delphi_Expression exception;
	}

}
