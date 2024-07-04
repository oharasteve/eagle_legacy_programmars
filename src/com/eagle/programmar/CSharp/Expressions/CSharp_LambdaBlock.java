// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_LambdaBlock extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT SeparatedList<CSharp_Variable, PunctuationComma> vars;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) CSharp_Punctuation lambda = new CSharp_Punctuation("=>");
	public @S(50) CSharp_StatementBlock block;
}
