// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_ArgumentList;
import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_ClassCreationWithInitializers extends PrimaryOperator
{
	public @S(10) Javascript_Keyword NEW = new Javascript_Keyword("new");
	public @S(20) Javascript_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) Javascript_ArgumentList valueList;
	public @S(50) PunctuationRightBrace rightBrace;
}
