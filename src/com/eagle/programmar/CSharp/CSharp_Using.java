// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Using extends TokenSequence implements EagleRunnable
{
	public @S(10) CSharp_Keyword USING = new CSharp_Keyword("using");
	public @S(20) @OPT CSharp_Keyword STATIC = new CSharp_Keyword("static");
	public @S(30) CSharp_Identifier id;
	public @S(40) @OPT TokenList<CSharp_MoreUsing> moreIds;
	public @S(50) @OPT CSharp_UsingEquals alternateName;
	public @S(60) @NOSPACE PunctuationSemicolon semicolon;

	public static class CSharp_MoreUsing extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE CSharp_Identifier id;
	}

	public static class CSharp_UsingEquals extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) @OPT CSharp_UsingGlobal global;
		public @S(30) SeparatedList<CSharp_Identifier, PunctuationPeriod> id;

		public static class CSharp_UsingGlobal extends TokenSequence
		{
			public @S(10) CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
			public @S(20) CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}
