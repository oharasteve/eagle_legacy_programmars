// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_RangeExpression extends PrimaryOperator
{
	public @S(10) Python_Keyword RANGE = new Python_Keyword("range");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Python_Expression start;
	public @S(40) @NOSPACE PunctuationComma comma;
	public @S(50) Python_Expression stop;
	public @S(60) @NOSPACE @OPT Python_RangeIncr increment;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;

	public static class Python_RangeIncr extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Python_Expression incr;
	}
}
