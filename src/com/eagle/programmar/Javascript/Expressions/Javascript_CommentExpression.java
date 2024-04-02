// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_CommentExpression extends PrimaryOperator
{
	public @S(10) Javascript_Comment comment;
	public @S(20) Javascript_Expression expr;
}
