// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 8, 2026

package com.eagle.programmar.Haskell.Expressions;

import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Syntax.Haskell_Multiline_Syntax;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Haskell_BracketsExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @SYNTAX(Haskell_Multiline_Syntax.class) SeparatedList<Haskell_Expression,PunctuationComma> values;
	public @S(30) PunctuationRightBracket rightBracket;
}
