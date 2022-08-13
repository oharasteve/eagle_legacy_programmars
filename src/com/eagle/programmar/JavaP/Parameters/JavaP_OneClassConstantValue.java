// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_OneClassConstantValue extends TokenSequence
{
	public @S(10) JavaP_KeywordChoice CONSTANTVALUE = new JavaP_KeywordChoice("Constant", "ConstantValue");
	public @S(20) @OPT JavaP_Keyword VALUE = new JavaP_Keyword("value");
	public @S(30) PunctuationColon colon;
	public @S(40) JavaP_KeywordChoice type = new JavaP_KeywordChoice("int", "long", "String");
	public @S(50) JavaP_RestOfLine value;
	public @S(60) JavaP_EndOfLine eoln;
}