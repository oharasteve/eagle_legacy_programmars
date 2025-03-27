// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Terminals.JavaP_Primitives;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;

public class JavaP_Type extends TokenSequence
{
	public @S(10) @OPT PunctuationLeftBracket leftBracket;
	public @S(20) JavaP_TypeScalar type;

	public static class JavaP_TypeScalar extends TokenChooser
	{
		public @CHOICE JavaP_Primitives XXprimitives;
		public @CHOICE JavaP_ClassName XXclassName;
	}
}
