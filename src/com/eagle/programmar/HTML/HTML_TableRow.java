// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, AUg 13, 2022

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_TableRow extends TokenSequence
{
	public @S(10) @INDENT HTML_StartRow startRow;
	public @S(20) @OPT TokenList<HTML_Comment> comments1;
	public @S(30) @OPT TokenList<HTML_TableData> cells;
	public @S(40) @OPT TokenList<HTML_Comment> comments2;
	public @S(50) @OPT @OUTDENT HTML_EndRow endRow;
	
	public static class HTML_StartRow extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<");
		public @S(20) @NOSPACE HTML_Keyword TR = new HTML_Keyword("tr");
		public @S(30) @OPT TokenList<HTML_Attribute> attributes; 
		public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

	public static class HTML_EndRow extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @NOSPACE HTML_Keyword TR = new HTML_Keyword("tr");
		public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}