// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ForEachStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE CSharp_Keyword FOREACH = new CSharp_Keyword("foreach");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) CSharp_Type varType;
	public @S(40) CSharp_Variable forVar;
	public @S(50) CSharp_Keyword IN = new CSharp_Keyword("in");
	public @S(60) CSharp_Expression collection;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;
	public @S(80) @OPT CSharp_Comment comment;
	public @S(90) CSharp_Statement action;

//	private EagleScope _scope = new EagleScope(this, CSharp_Syntax.isCaseSensitive);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}
}
