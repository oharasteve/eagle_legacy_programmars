// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class CSharp_ClassCreationWithInitializers extends PrimaryOperator
{
	public @S(10) CSharp_Keyword NEW = new CSharp_Keyword("new");
	public @S(20) CSharp_Type jtype;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList valueList;
	public @S(50) @NOSPACE PunctuationRightBrace rightBrace;
}
