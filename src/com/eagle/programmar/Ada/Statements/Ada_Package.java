// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Symbols.Ada_Package_Definition;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Package extends TokenSequence
{
	public @S(10) Ada_Keyword PACKAGE = new Ada_Keyword("package");
	public @S(20) Ada_Package_Definition pkg;
	public @S(30) Ada_Keyword IS = new Ada_Keyword("is");
	public @S(40) Ada_Keyword NEW = new Ada_Keyword("new");
	public @S(50) SeparatedList<Ada_Identifier_Reference, PunctuationPeriod> ids;
	public @S(60) PunctuationLeftParen leftParen;
	public @S(70) SeparatedList<Ada_Identifier_Reference, PunctuationComma> params;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) PunctuationSemicolon semicolon;
}
