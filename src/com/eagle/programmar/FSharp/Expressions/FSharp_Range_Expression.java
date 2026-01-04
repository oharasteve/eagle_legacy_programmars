// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2026

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.Terminals.FSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class FSharp_Range_Expression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) FSharp_Expression low;
	public @S(30) FSharp_Punctuation dotDot = new FSharp_Punctuation("..");
	public @S(40) FSharp_Expression high;
	public @S(50) PunctuationRightBracket rightBracket;
}
