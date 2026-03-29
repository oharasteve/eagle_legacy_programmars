// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

namespace com.eagle.programmar.C.Statements
{
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using IntelASM_Line = com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
	using IntelASM_Syntax = com.eagle.programmar.IntelASM.IntelASM_Syntax;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class C_Embed_Assembler : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_KeywordChoice ASM = new com.eagle.programmar.C.Terminals.C_KeywordChoice("__asm", "_asm");
		public C_KeywordChoice ASM = new C_KeywordChoice("__asm", "_asm");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @SYNTAX(com.eagle.programmar.IntelASM.IntelASM_Syntax.class) com.eagle.tokens.TokenList<com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line> assmbler;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
	}
}
