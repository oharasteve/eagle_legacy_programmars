// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_BuiltInFunction extends PrimaryOperator
{
	public @S(10) C_KeywordChoice builtinFunction = new C_KeywordChoice("exit");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<C_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;
}
