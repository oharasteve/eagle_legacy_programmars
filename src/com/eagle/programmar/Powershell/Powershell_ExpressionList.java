// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Powershell_ExpressionList extends TokenSequence
{
	public @S(10) @OPT Powershell_EndOfLine eoln1;
	public @S(20) @OPT Powershell_Expression expr;
	public @S(30) @OPT TokenList<Powershell_MoreExpression> more;
	public @S(40) @OPT TokenList<Powershell_Comment> comments;
	public @S(50) @OPT Powershell_EndOfLine eoln2;

	public static class Powershell_MoreExpression extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Powershell_Comment comment;
		public @S(30) @OPT Powershell_EndOfLine eoln;
		public @S(40) Powershell_Expression expr;
	}
}