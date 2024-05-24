// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_Parameters extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT SeparatedList<VB_Parameter, PunctuationComma> params;
	public @S(30) PunctuationRightParen rightParen;

	public static class VB_Parameter extends TokenSequence
	{
		public @S(10) @OPT VB_KeywordChoice valRef = new VB_KeywordChoice("byval", "byref");
		public @S(20) VB_Variable_Definition var;
		public @S(30) @OPT VB_ParameterAs as;

		public static class VB_ParameterAs extends TokenSequence
		{
			public @S(10) VB_Keyword AS = new VB_Keyword("as");
			public @S(20) VB_Type type;
		}
	}
}
