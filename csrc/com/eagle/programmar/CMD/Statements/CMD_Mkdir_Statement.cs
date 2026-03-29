// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 1, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class CMD_Mkdir_Statement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("mkdir.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword MKDIR = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("mkdir");
		public @DOC("mkdir.mspx") CMD_Keyword MKDIR = new CMD_Keyword("mkdir");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.CMD_Expression dir;
		public CMD_Expression dir;
	}

}
