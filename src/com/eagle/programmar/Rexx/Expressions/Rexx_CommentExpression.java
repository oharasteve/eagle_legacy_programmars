// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Comment;
import com.eagle.tokens.PrimaryOperator;

public class Rexx_CommentExpression extends PrimaryOperator
{
	public @S(10) Rexx_Comment comment;
	public @S(20) Rexx_Expression expr;
}
