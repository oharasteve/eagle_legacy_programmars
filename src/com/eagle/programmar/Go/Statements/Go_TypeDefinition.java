// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Symbols.Go_Type_Definition;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_TypeDefinition extends TokenSequence
{
	public @S(10) Go_Keyword TYPE = new Go_Keyword("type");
	public @S(20) Go_Type_Definition typeName;
	public @S(30) Go_Keyword STRUCT = new Go_Keyword("struct");
	public @S(40) PunctuationLeftBrace leftBrace;
	public @S(50) Go_EOLN eoln1;
	public @S(60) TokenList<Go_StructLine> structLine;
	public @S(70) PunctuationRightBrace rightBrace;
	public @S(80) Go_EOLN eoln2;
	
	public static class Go_StructLine extends TokenSequence
	{
		public @S(10) Go_Variable_Definition fieldName;
		public @S(20) Go_Type typeName;
		public @S(30) Go_EOLN eoln;
	}
}
