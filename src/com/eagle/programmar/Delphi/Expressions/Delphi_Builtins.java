// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Builtins extends PrimaryOperator
{
	public @S(10) Java_KeywordChoice builtinConstant = new Java_KeywordChoice("False", "True", "Nil");
}
