// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Javascript_FunctionParameters extends TokenSequence
{
	public @S(10) Javascript_FunctionParameter param;
	public @S(20) @OPT TokenList<Javascript_MoreParameters> moreParams;
	
	public static class Javascript_FunctionParameter extends TokenSequence
	{
		public @S(10) Javascript_ParameterName paramName;
		public @S(20) @OPT Javascript_ParameterInitValue value;
		
		public static class Javascript_ParameterName extends TokenChooser
		{
			public @CHOICE Javascript_Variable_Definition id;
			public @CHOICE Javascript_Punctuation dollar = new Javascript_Punctuation('$');
		}
		
		public static class Javascript_ParameterInitValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Javascript_Expression initValue;
		}
	}
		
	public static class Javascript_MoreParameters extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Javascript_Comment comment;
		public @S(30) Javascript_FunctionParameter param;
	}
}
