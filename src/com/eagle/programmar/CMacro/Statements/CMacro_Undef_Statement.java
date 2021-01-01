// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CMacro_Undef_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Undefining-and-Redefining-Macros.html") CMacro_Keyword UNDEF = new CMacro_Keyword("undef");
	public @S(30) CMacro_Identifier_Reference var;
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		String id = var.getValue();
		//System.out.println("#undef " + id + " ...");
		preprocessor._symbolTable.removeSymbol(id);
		return true;
	}
}
