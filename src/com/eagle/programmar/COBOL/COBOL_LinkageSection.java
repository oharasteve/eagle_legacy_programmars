// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2026

package com.eagle.programmar.COBOL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_LinkageSection extends TokenSequence implements EagleRunnable
{
	public @S(10) COBOL_Keyword LINKAGE = new COBOL_Keyword("LINKAGE");
	public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
		{
			interpreter.tryToInterpret(decl);
		}
	}
}