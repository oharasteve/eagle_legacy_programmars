// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) COBOL_SubscriptType which;
	public @S(30) PunctuationRightParen rightParen;
	
	public static class COBOL_SubscriptType extends TokenChooser
	{
		public @CHOICE COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		
		public @CHOICE static class COBOL_RegularSubscript extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) @OPT COBOL_SubscriptRange range;
			
			public static class COBOL_SubscriptRange extends TokenSequence
			{
				public @S(10) COBOL_PunctuationChoice colon = new COBOL_PunctuationChoice(":", ",");
				public @S(20) COBOL_Expression expr;
			}
		}
	}
}
