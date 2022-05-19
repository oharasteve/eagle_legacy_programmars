// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Comment;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_CommentControl extends TokenSequence
{
	public @S(10) Django_Keyword COMMENT = new Django_Keyword("comment");
	public @S(20) @OPT PunctuationHyphen dash2;
	public @S(30) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public @S(40) Django_Comment comment;

	public @S(50) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(60) @OPT PunctuationHyphen dash3;
	public @S(70) Django_Keyword ENDCOMMENT = new Django_Keyword("endcomment");
}
