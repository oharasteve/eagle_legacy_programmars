// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2025

package com.eagle.programmar.BNF.Expressions;

import com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class BNF_Alternation extends TokenSequence
{
	public @S(10) BNF_Punctuation VerticalBar = new BNF_Punctuation('|');
	public @S(20) TokenList<BNF_ExpressionTerm> terms;
}