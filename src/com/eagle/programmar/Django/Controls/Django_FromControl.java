// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Symbols.Django_Identifier_Reference;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.TokenSequence;

public class Django_FromControl extends TokenSequence
{
	public @S(10) Django_Keyword FROM = new Django_Keyword("from");
	public @S(20) Django_Identifier_Reference source;
	public @S(30) Django_Keyword IMPORT = new Django_Keyword("import");
	public @S(40) Django_Identifier_Reference def;
	public @S(50) Django_Keyword WITH = new Django_Keyword("with");
	public @S(60) Django_Keyword CONTEXT = new Django_Keyword("context");
}
