// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HashNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class JavaP_RuntimeVisibleTypeAnnotations extends TokenSequence
{
	public JavaP_Keyword ANNOTATIONS = new JavaP_Keyword("RuntimeVisibleTypeAnnotations");
	public PunctuationColon colon1;
	public JavaP_EndOfLine eoln1;

	public JavaP_Number seq;
	public PunctuationColon colon2;
	public JavaP_HashNumber id;
	public PunctuationLeftParen leftParen;
	public PunctuationRightParen rightParen;
	public PunctuationColon colon3;
	public JavaP_Keyword METHOD_RETURN = new JavaP_Keyword("METHOD_RETURN");
	public JavaP_EndOfLine eoln2;

	public @OPT JavaP_QualifiedName name;
	public JavaP_EndOfLine eoln3;
}
