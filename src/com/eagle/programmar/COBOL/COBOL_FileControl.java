// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_FileControl extends TokenSequence
{
	public @S(10) COBOL_Keyword FILECONTROL = new COBOL_Keyword("FILE-CONTROL");
	public @S(20) PunctuationPeriod dot;
	public @S(30) @OPT TokenList<COBOL_Copy_or_FileSelect> fileSelects;

	public static class COBOL_Copy_or_FileSelect extends TokenChooser
	{
		public @CHOICE COBOL_Copy_Directive XXcopyDirective;
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_Directive XXdirective;
		public @CHOICE COBOL_FileSelect XXfileSelect;
	}
}
