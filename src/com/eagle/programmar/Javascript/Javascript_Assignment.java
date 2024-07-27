// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Assignment extends TokenSequence
{
	public @S(10) Javascript_Variable var;
	public @S(20) Javascript_AssignmentOperator assignmentOperator;
	public @S(30) Javascript_Expression exp;
	public @S(40) PunctuationSemicolon semicolon;

	public static class Javascript_AssignmentOperator extends TokenChooser
	{
		public @CHOICE Javascript_PunctuationChoice XXoper = new Javascript_PunctuationChoice(
				"=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=", ">>>=", "&=", "^=", "|=");
	}
}
