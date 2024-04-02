// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_ParenthesizedExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) PLI_Expression expr;
	public @S(30) @OPT PLI_Expression_Do expressionDo;
	public @S(40) PunctuationRightParen rightParen;
	
	public static class PLI_Expression_Do extends TokenSequence
	{
		public @S(10) PLI_Keyword DO = new PLI_Keyword("DO");
		public @S(20) PLI_Variable_Definition var;
		public @S(30) PunctuationEquals equals;
		public @S(40) PLI_Expression start;
		public @S(50) PLI_Keyword TO = new PLI_Keyword("TO");
		public @S(60) PLI_Expression stop;
	}
}
