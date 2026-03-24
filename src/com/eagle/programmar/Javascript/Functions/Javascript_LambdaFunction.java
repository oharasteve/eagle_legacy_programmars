// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_LambdaFunction extends PrimaryOperator
{
	public @S(10) @OPT Javascript_Keyword ASYNC = new Javascript_Keyword("async");
	public @S(20) Javascript_LambdaParams params;
	public @S(30) Javascript_Punctuation arrow = new Javascript_Punctuation("=>");
	public @S(40) Javascript_LambdaBody body;

	public static class Javascript_LambdaParams extends TokenChooser
	{
		public @CHOICE Javascript_Variable_Definition XXparam;

		public @CHOICE static class Javascript_LambdaManyParams extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT SeparatedList<Javascript_Variable_Definition, PunctuationComma> params;
			public @S(30) PunctuationRightParen rightParen;
		}
	}

	public static class Javascript_LambdaBody extends TokenChooser
	{
		public @CHOICE Javascript_FunctionBody XXblock;
		public @CHOICE Javascript_Element XXstmt;
	}
}
