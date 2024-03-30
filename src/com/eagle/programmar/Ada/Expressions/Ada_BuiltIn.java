// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Ada_BuiltIn extends PrimaryOperator
{
	public @S(10) Ada_KeywordChoice builtinConstant = new Ada_KeywordChoice("false", "true");
}