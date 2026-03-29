// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.RPG.Specifications
{
	using RPG_KeywordChoice = com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_O_Output_Program_Record_Id_Piece1 : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Literal recordName;
		public RPG_Literal recordName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice type;
		public RPG_KeywordChoice type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT RPG_KeywordChoice overflowRelease;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT RPG_Number spaceBefore;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT RPG_Number spaceAfter;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT RPG_Literal skipBefore;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT RPG_Literal skipAfter;
		public  OPT;
	}
}
