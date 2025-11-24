// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 9, 2024

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_AsmVolatile extends TokenSequence
{
	// __asm__ __volatile__ ("inb %w1,%0":"=a" (_v):"Nd" (__port));
	// __asm__ __volatile__ ("cld ; rep ; insb":"=D" (__addr), "=c" (__count)
	// __asm__ __volatile__ ("cld ; rep ; insb":"=D" (__addr), "=c" (__count)
	// :"d" (__port), "0" (__addr), "1" (__count));

	public @S(10) C_Keyword ASM = new C_Keyword("__asm__");
	public @S(20) C_Keyword VOLATILE = new C_Keyword("__volatile__");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) C_Literal code;
	public @S(50) TokenList<C_AsmPiece> pieces;
	public @S(60) PunctuationRightParen rightParen1;
	public @S(70) PunctuationSemicolon semicolon;

	public static class C_AsmPiece extends TokenSequence
	{
		public @S(10) C_PunctuationChoice comma = new C_PunctuationChoice(",", ":");
		public @S(20) @OPT PunctuationColon colon;
		public @S(30) C_Literal value1;
		public @S(40) PunctuationLeftParen leftParen;
		public @S(50) C_KeywordChoice VALUE = new C_KeywordChoice(
				"__addr", "__count", "__port", "_v", "__value");
		public @S(60) PunctuationRightParen rightParen;
	}
}
