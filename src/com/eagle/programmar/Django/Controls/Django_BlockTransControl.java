// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_BlockTransControl extends TokenSequence
{
	public @S(10) Django_Keyword BLOCK = new Django_Keyword("blocktrans");
	public @S(20) @OPT Django_BlockControlWith with;
	public @S(30) @OPT PunctuationHyphen dash2;
	public @S(40) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public @S(50) @OPT TokenList<Django_Element> html;

	public @S(60) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(70) @OPT PunctuationHyphen dash3;
	public @S(80) Django_Keyword ENDBLOCK = new Django_Keyword("endblocktrans");
	
	public static class Django_BlockControlWith extends TokenSequence
	{
		public @S(10) Django_Keyword WITH = new Django_Keyword("with");
		public @S(20) Django_Literal literal;
		public @S(30) Django_Keyword AS = new Django_Keyword("as");
		public @S(40) Django_Variable var;
	}
}
