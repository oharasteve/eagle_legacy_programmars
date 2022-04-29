// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 10, 2014

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.programmar.CMacro.Terminals.CMacro_RestOfLine;
import com.eagle.tokens.TokenSequence;

public class CMacro_Error_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) CMacro_KeywordChoice ERROR = new CMacro_KeywordChoice("error", "warning");
	public @S(30) @OPT CMacro_RestOfLine message;		// Just keep it as a String

	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}
