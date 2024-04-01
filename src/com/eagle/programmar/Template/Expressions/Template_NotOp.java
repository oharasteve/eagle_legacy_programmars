// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Template.Expressions;

import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Terminals.Template_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Template_NotOp extends PrimaryOperator
{
	public @S(10) Template_Punctuation NOT = new Template_Punctuation('!');
	public @S(20) Template_Expression expr;
}
