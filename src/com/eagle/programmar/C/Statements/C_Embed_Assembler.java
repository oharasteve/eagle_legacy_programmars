// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
import com.eagle.programmar.IntelASM.IntelASM_Syntax;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class C_Embed_Assembler extends TokenSequence
{
	public @S(10) @INDENT C_KeywordChoice ASM = new C_KeywordChoice("__asm", "_asm");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT @SYNTAX(IntelASM_Syntax.class) TokenList<IntelASM_Line> assmbler;
	public @S(40) @OUTDENT PunctuationRightBrace rightBrace;
}