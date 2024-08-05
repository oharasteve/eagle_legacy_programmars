// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_Paragraph extends TokenSequence implements EagleRunnable, AbstractFunction
{
	public @S(10) @OPT TokenList<COBOL_ParagraphHeader> paragraphHeaders;
	public @S(20) TokenList<COBOL_SentenceOrComment> sentences;

	public static class COBOL_SentenceOrComment extends TokenChooser
	{
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_Sentence XXsentence;
		public @LAST COBOL_ScreenDeclaration XXscreen;

		public @LAST static class COBOL_DataInParagraph extends TokenSequence
		{
			public @S(10) TokenList<COBOL_CopyOrDataDeclaration> data;
		}
	}

	public static class COBOL_ParagraphHeader extends TokenSequence
	{
		public @S(10) COBOL_Paragraph_Definition paragraphName;
		public @S(20) PunctuationPeriod dot;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_SentenceOrComment sentence : sentences._elements)
		{
			interpreter.tryToInterpret(sentence);
		}
	}
}