// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Statement;
import com.eagle.programmar.TCL.Symbols.TCL_Namespace_Definition;
import com.eagle.programmar.TCL.Terminals.TCL_EndOfLine;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class TCL_NamespaceStatement extends TokenSequence
{
	public @S(10) TCL_Keyword NAMESPACE = new TCL_Keyword("namespace");
	public @S(20) TCL_Keyword EVAL = new TCL_Keyword("eval");
	public @S(30) TCL_Namespace_Definition namespace;
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) TCL_EndOfLine endOfLine;
	
	public TokenList<TCL_Statement> statements;
	
	public PunctuationRightBrace rightBrace;
}
