// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.Statements.C_StatementBlock;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CPlus_ConstructorValue extends TokenChooser
{
	public @CHOICE C_StatementBlock block;

	public @CHOICE static class CPlus_ConstructorNoBraces extends TokenSequence
	{
		public @S(10) @OPT CPlus_ConstructorInitialValue value;
		public @S(20) PunctuationSemicolon semicolon;

		public static class CPlus_ConstructorInitialValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) C_KeywordChoice DELETE = new C_KeywordChoice("delete", "default");
		}
	}
}