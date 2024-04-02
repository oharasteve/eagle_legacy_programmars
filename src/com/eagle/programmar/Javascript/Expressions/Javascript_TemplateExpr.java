// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_TemplateLiteral;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_TemplateExpr extends PrimaryOperator
{
	public @S(10) @OPT Javascript_Function_Reference func;
	public @S(20) Javascript_TemplateLiteral template;
}
