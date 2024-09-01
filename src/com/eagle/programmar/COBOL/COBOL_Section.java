// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.COBOL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Section_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_Section extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT COBOL_SectionHeader sectionHeader;
	public @S(20) TokenList<COBOL_Paragraph> paragraphs;

	public static class COBOL_SectionHeader extends TokenSequence
	{
		public @S(10) COBOL_Section_Definition sectionName;
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) @OPT COBOL_Number number;
		public @S(40) PunctuationPeriod dot;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(paragraphs.first());
	}
}