// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CSharp_ArgumentList extends TokenSequence
{
	public @S(10) @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
	public @S(20) CSharp_Expression arg;
	public @S(30) @OPT TokenList<CSharp_MoreArguments> moreArgs;
	
	public static class CSharp_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out");
		public @S(30) CSharp_Expression arg;
		public @S(40) @OPT TokenList<CSharp_Comment> comments;
	}
}
