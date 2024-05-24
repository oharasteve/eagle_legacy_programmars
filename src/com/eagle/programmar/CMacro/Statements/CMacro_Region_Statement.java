// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 18, 2022

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.Terminals.CMacro_CommentRestOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Region_Statement extends TokenSequence
{
	public @S(10) CMacro_Punctuation pound1 = new CMacro_Punctuation('#');
	public @S(20) CMacro_Keyword REGION = new CMacro_Keyword("region");
	public @S(30) @OPT CMacro_CommentRestOfLine startLabel;
	public @S(40) CMacro_EndOfLine eoln1;

	public @S(50) @OPT TokenList<CMacro_Element> elements;
	public @S(60) @OPT CMacro_EndOfLine eoln2;

	public @S(70) CMacro_Punctuation pound2 = new CMacro_Punctuation('#');
	public @S(80) CMacro_Keyword ENDREGION = new CMacro_Keyword("endregion");
	public @S(90) @OPT CMacro_CommentRestOfLine endLabel;
}