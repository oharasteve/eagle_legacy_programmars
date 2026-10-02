// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Javascript_Pattern;
import com.eagle.programmar.Javascript.TypeScript.TS_TypeAnnotation;
import com.eagle.programmar.Javascript.TypeScript.TS_Generics;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
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
		public @CHOICE Javascript_LambdaParam XXparam;

		public @CHOICE static class Javascript_LambdaManyParams extends TokenSequence
		{
			public @S(5) @OPT TS_Generics generics;
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT SeparatedList<Javascript_LambdaParam, PunctuationComma> params;
			public @S(30) PunctuationRightParen rightParen;
			public @S(40) @OPT TS_TypeAnnotation returns;
		}
	}
	
	public static class Javascript_LambdaParam extends TokenSequence
	{
		public @S(10) @OPT Javascript_Punctuation rest = new Javascript_Punctuation("...");
		public @S(20) Javascript_LambdaParamName param;
		public @S(23) @OPT Javascript_Punctuation optional = new Javascript_Punctuation("?");
		public @S(26) @OPT TS_TypeAnnotation tsType;
		public @S(30) @OPT Javascript_LambdaInitValue init;

		public static class Javascript_LambdaParamName extends TokenChooser
		{
			public @CHOICE Javascript_Variable_Definition XXid;
			public @CHOICE Javascript_Pattern.Javascript_ObjectPattern XXobjectPattern;
			public @CHOICE Javascript_Pattern.Javascript_ArrayPattern XXarrayPattern;
		}

		public static class Javascript_LambdaInitValue extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Javascript_Expression initValue;
		}
	}

	public static class Javascript_LambdaBody extends TokenChooser
	{
		public @CHOICE Javascript_FunctionBody XXblock;
		// An expression, not a statement (changed Oct 2026, shane branch): a statement body
		// swallowed the comma-separated properties that followed a lambda in an object literal.
		public @CHOICE Javascript_Expression XXexpr;
	}
}
