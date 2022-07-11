// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_WithUseStatement extends TokenSequence
{
	public @S(10) Ada_Keyword WITH = new Ada_Keyword("with");
	public @S(20) SeparatedList<Ada_Identifier_Reference,PunctuationPeriod> withs;
	public @S(30) PunctuationSemicolon semicolon1;
	public @S(40) Ada_Keyword USE = new Ada_Keyword("use");
	public @S(50) SeparatedList<Ada_Identifier_Reference,PunctuationPeriod> uses;
	public @S(60) PunctuationSemicolon semicolon2;
}
