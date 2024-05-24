// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Ruby.Terminals.Ruby_Comment;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Ruby_Program extends EagleLanguage
{
	public static final String RUBY = "Ruby";

	public Ruby_Program()
	{
		super(RUBY, new Ruby_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.ruby-lang.org/en/2.4.0/syntax/";
	}

	public @S(10) TokenList<Ruby_Element> elements;

	public static class Ruby_Element extends TokenChooser
	{
		public @CHOICE Ruby_CommentEoln comment;
		public @CHOICE Ruby_Statement stmt;
	}

	public static class Ruby_CommentEoln extends TokenSequence
	{
		public @S(10) Ruby_Comment comment;
		public @S(20) Ruby_EOLN eoln;
	}
}