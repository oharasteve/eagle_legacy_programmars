// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_CommentExpression extends PrimaryOperator
{
	public @S(10) CSharp_Comment comment;
	public @S(20) CSharp_Expression expr;
}
