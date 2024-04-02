// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.tokens.PrimaryOperator;

public class VB_CommentExpression extends PrimaryOperator
{
	public @S(10) VB_Comment comment;
	public @S(20) VB_Expression expr;
}
