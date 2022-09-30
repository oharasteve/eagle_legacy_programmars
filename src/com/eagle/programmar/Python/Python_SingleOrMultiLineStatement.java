// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2022

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Python_SingleOrMultiLineStatement extends TokenChooser
{
	public @CHOICE Python_Punctuation dots = new Python_Punctuation("...");
	
	public @CHOICE static class Python_SingleLineStatement extends TokenSequence
	{
		public @S(10) SeparatedList<Python_Simple_Statement,PunctuationSemicolon> statements;
		public @S(20) @OPT Python_Comment comment;
		public @S(30) @OPT Python_EndOfLine eoln;
	}

	public @CHOICE static class Python_MultilineStatement extends TokenSequence
	{
		public @S(10) @OPT Python_Comment comment;
		public @S(20) Python_EndOfLine eoln;
		public @S(30) TokenList<Python_Statement> statements;
	}
}