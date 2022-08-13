// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_OneClassMethodParameters extends TokenSequence
{
	public @S(10) JavaP_Keyword METHODPARAMETERS = new JavaP_Keyword("MethodParameters");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_EndOfLine eoln1;
	
	public @S(40) JavaP_Keyword NAME = new JavaP_Keyword("Name");
	public @S(50) JavaP_Keyword FLAGS = new JavaP_Keyword("Flags");
	public @S(60) JavaP_EndOfLine eoln2;
	
	public @S(70) @OPT TokenList<JavaP_OneClassMethodParameter> params;
	
	public static class JavaP_OneClassMethodParameter extends TokenSequence
	{
		public @S(10) JavaP_QualifiedName name;
		public @S(20) @OPT TokenList<JavaP_Value> values;
		public @S(30) JavaP_EndOfLine eoln;
	}
}