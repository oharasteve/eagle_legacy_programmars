// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_Instruction extends TokenSequence
{
	public @S(10) IntelASM_RegularInstruction instruction;
	public @S(20) @OPT IntelASM_Comment commend;
	public @S(30) IntelASM_EndOfLine eoln;
	
	public static class IntelASM_RegularInstruction extends TokenChooser
	{
		public @CHOICE static class IntelASM_CALL extends TokenSequence
		{
			public @S(10) IntelASM_Keyword CALL = new IntelASM_Keyword("CALL");
			public @S(20) IntelASM_Label_Reference label;
		}

		public @CHOICE static class IntelASM_JMP extends TokenSequence
		{
			public @S(10) IntelASM_KeywordChoice JMP =
					new IntelASM_KeywordChoice("JC", "JE", "JG", "JGE", "JL", "JLE", "JMP", "JNE", "JNZ", "JZ");
			public @S(20) IntelASM_Label_Reference label;
		}

		public @CHOICE static class IntelASM_NoArgs extends TokenSequence
		{
			public @S(10) IntelASM_KeywordChoice CMD =
					new IntelASM_KeywordChoice("CLD", "LODSB", "MOVSB", "NOP", "RET", "STD", "STOSB");
		}

		public @CHOICE static class IntelASM_OneArg extends TokenSequence
		{
			public @S(10) IntelASM_KeywordChoice CMD =
					new IntelASM_KeywordChoice("DEC", "DIV", "INC", "MUL", "NEG", "POP", "PUSH", "REP", "REPZ", "SETZ");
			public @S(20) IntelASM_Expression arg;
		}
		
		public @CHOICE static class IntelASM_TwoArgs extends TokenSequence
		{
			public @S(10) IntelASM_KeywordChoice CMD =
					new IntelASM_KeywordChoice("ADD", "AND", "CMP", "LEA", "MOV", "MOVSX", "MOVZX",
							"OR", "SHL", "SHR", "SUB", "TEST", "XOR");
			public @S(20) IntelASM_Expression arg1;
			public @S(30) PunctuationComma comma;
			public @S(40) IntelASM_Expression arg2;
		}
		
		public @CHOICE static class IntelASM_REPNE extends TokenSequence
		{
			public @S(10) IntelASM_Keyword REPNE = new IntelASM_Keyword("REPNE");
			public @S(20) IntelASM_Keyword SCASB = new IntelASM_Keyword("SCASB");
		}
	}
}
