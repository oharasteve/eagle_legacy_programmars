// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_List;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Python_Brackets extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT Python_EndOfLine eoln1;
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) @OPT Python_EndOfLine eoln2;
	public @S(50) @OPT @SYNTAX(Python_Multiline_Syntax.class) Python_List list;
	public @S(60) PunctuationRightBracket rightBracket;
}
