// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_Blanks = com.eagle.programmar.RPG.Terminals.RPG_Blanks;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_L_LineCounter_Specification : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword L = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(6, 6, "L");
		public RPG_Keyword L = new RPG_Keyword(6, 6, "L");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal fileName = new com.eagle.programmar.RPG.Terminals.RPG_Literal(7, 14);
		public RPG_Literal fileName = new RPG_Literal(7, 14);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_Number linesPerPage = new com.eagle.programmar.RPG.Terminals.RPG_Number(15, 17);
		public RPG_Number linesPerPage = new RPG_Number(15, 17);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Keyword FL = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(18, 19, "FL");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.RPG.Terminals.RPG_Number overflowLineNumber = new com.eagle.programmar.RPG.Terminals.RPG_Number(20, 22);
		public RPG_Number overflowLineNumber = new RPG_Number(20, 22);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Keyword OL = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(23, 24, "OL");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.RPG.Terminals.RPG_Blanks blank1 = new com.eagle.programmar.RPG.Terminals.RPG_Blanks(25, 74);
		public RPG_Blanks blank1 = new RPG_Blanks(25, 74);
	}

}
