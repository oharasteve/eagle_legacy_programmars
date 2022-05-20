// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Symbols.Django_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Django_Variable extends TokenSequence
{
	public @S(10) @OPT Django_Namespace namespace;
	public @S(20) Django_Identifier_Reference variable;
	public @S(30) @OPT TokenList<Django_DotVariable> more;
	
	public static class Django_Namespace extends TokenSequence
	{
		public @S(10) Django_Identifier_Reference name;
		public @S(20) PunctuationColon colon;
	}
	
	public static class Django_DotVariable extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) Django_Identifier_Reference variable;
	}
}
