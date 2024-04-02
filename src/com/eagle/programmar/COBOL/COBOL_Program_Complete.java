// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

package com.eagle.programmar.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public abstract class COBOL_Program_Complete extends COBOL_Program implements EagleRunnable
{
	public COBOL_Program_Complete(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}
	
	// Components of a complete COBOL Program
	public @S(10) @OPT TokenList<COBOL_Comment> comments1;
	public @S(20) @OPT TokenList<COBOL_Directive> directives;
	public @S(30) @OPT TokenList<COBOL_Comment> comments2;
	public @S(40) @OPT COBOL_SpecialNames specialNames;
	public @S(50) @OPT COBOL_IdentificationDivision identificationDiv;
	public @S(60) @OPT COBOL_EnvironmentDivision environmentDiv;
	public @S(70) @OPT TokenList<COBOL_Comment> comments3;
	public @S(80) @OPT COBOL_DataDivision dataDiv;
	public @S(90) COBOL_ProcedureDivision procedureDiv;
	
	public @S(100) @OPT TokenList<COBOL_Program_Free_Format> nestedPrograms;

	public @S(110) @OPT COBOL_EndProgram endProgram;
	
	public static class COBOL_EndProgram extends TokenSequence
	{
		public @S(10) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(20) COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
		public @S(30) COBOL_Identifier_Reference programId;
		public @S(40) PunctuationPeriod dot;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(procedureDiv);
	}
}
