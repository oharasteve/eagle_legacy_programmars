// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Statement;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_ForStatement extends TokenSequence
{
	public @S(10) @DOC("TclCmd/for.html") TCL_Keyword FOR = new TCL_Keyword("for");
	public @S(20) PunctuationLeftBrace leftBrace1;
	public @S(30) TCL_Statement init;
	public @S(40) PunctuationRightBrace rightBrace1;
	public @S(50) PunctuationLeftBrace leftBrace2;
	public @S(60) TCL_Expression code;
	public @S(70) PunctuationRightBrace rightBrace2;
	public @S(80) PunctuationLeftBrace leftBrace3;
	public @S(90) TCL_Statement incr;
	public @S(100) PunctuationRightBrace rightBrace3;
	public @S(110) TCL_Statement stmt;
}
