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
	public @S(10) JavaP_Keyword ANNOTATIONS = new JavaP_Keyword("RuntimeVisibleTypeAnnotations");
	public @S(20) PunctuationColon colon1;
	public @S(30) JavaP_EndOfLine eoln1;

	public @S(40) JavaP_Number seq;
	public @S(50) PunctuationColon colon2;
	public @S(60) JavaP_HashNumber id;
	public @S(70) PunctuationLeftParen leftParen;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) PunctuationColon colon3;
	public @S(100) JavaP_Keyword METHOD_RETURN = new JavaP_Keyword("METHOD_RETURN");
	public @S(110) JavaP_EndOfLine eoln2;

	public @S(120) @OPT JavaP_QualifiedName name;
	public @S(130) JavaP_EndOfLine eoln3;
}
