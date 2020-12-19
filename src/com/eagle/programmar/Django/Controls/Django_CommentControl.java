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
	public @S(10) HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_Keyword COMMENT = new Django_Keyword("comment");
	public @S(40) @OPT PunctuationHyphen dash2;
	public @S(50) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public @S(60) Django_Comment comment;

	public @S(70) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(80) @OPT PunctuationHyphen dash3;
	public @S(90) Django_Keyword ENDCOMMENT = new Django_Keyword("endcomment");
	public @S(100) @OPT PunctuationHyphen dash4;
	public @S(110) HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
}
