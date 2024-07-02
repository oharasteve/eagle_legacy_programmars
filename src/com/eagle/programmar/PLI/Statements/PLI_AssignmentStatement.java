// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AssignmentStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT PLI_Label label;
	public @S(20) PLI_Identifier_Reference var;
	public @S(30) @OPT PLI_Subscript params;
	public @S(40) PunctuationEquals equals;
	public @S(50) PLI_Expression expr;
	public @S(60) @OPT PLI_Comment comment;
	public @S(70) PunctuationSemicolon semicolon;
}
