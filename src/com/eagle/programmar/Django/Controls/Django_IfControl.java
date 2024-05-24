// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_IfControl extends TokenSequence
{
	public @S(10) Django_Keyword IF = new Django_Keyword("if");
	public @S(20) Django_Expression expr;
	public @S(30) @OPT PunctuationHyphen dash2;
	public @S(40) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");

	public @S(50) TokenList<Django_Element> html;

	public @S(60) @OPT Django_IfElseControl ifElse;

	public @S(70) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(80) @OPT PunctuationHyphen dash3;
	public @S(90) Django_Keyword ENDIF = new Django_Keyword("endif");

	public static class Django_IfElseControl extends TokenSequence
	{
		public @S(10) HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
		public @S(20) @OPT PunctuationHyphen dash1;
		public @S(30) Django_Keyword ELSE = new Django_Keyword("else");
		public @S(40) @OPT PunctuationHyphen dash2;
		public @S(50) HTML_Punctuation percentBrace = new HTML_Punctuation("%}");

		public @S(60) TokenList<Django_Element> html;
	}
}
