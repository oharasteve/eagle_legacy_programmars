// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Parameter_List extends TokenSequence
{
	public @S(10) @NOSPACE PunctuationLeftParen leftParen;
	public @S(20) @OPT Python_Comment comment;
	public @S(30) @NOSPACE @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_Parameters params;
	public @S(40) @OPT Python_EndOfLine eoln;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	public static class Python_InitValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Python_Expression defaultValue;
	}

	public static class Python_Parameters extends TokenChooser
	{
		public @CHOICE Python_Params params; 
		public @LAST PunctuationStar star;	// Means end of positional arguments
	}
}
