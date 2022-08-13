// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.C.Types;

import com.eagle.programmar.C.Symbols.C_Type_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Types.C_TypeStruct.C_FieldOrComment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_TypeUnion extends TokenSequence
{
	public @S(10) C_Keyword UNION = new C_Keyword("union");
	public @S(20) @OPT C_Type_Definition def;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT TokenList<C_FieldOrComment> fields;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) @OPT PunctuationSemicolon semicolon;
}