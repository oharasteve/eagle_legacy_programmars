// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Syntax.Go_Multiline_Syntax;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Field_Definition;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_Constructor extends PrimaryOperator
{
	public @S(10) Go_Variable className;
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT  @SYNTAX(Go_Multiline_Syntax.class) SeparatedList<Go_ConstructField,PunctuationComma> fields;
	public @S(40) PunctuationRightBrace rightBrace;
	
	public static class Go_ConstructField extends TokenSequence
	{
		public @S(10) Go_Field_Definition fieldName;
		public @S(20) PunctuationColon colon;
		public @S(30) Go_Expression value;
	}
}
