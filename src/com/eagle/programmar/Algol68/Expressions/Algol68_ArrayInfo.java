// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_ArrayInfo extends PrimaryOperator
{
	public @S(10) Algol68_KeywordChoice UPB = new Algol68_KeywordChoice("UPB", "LWB");
	public @S(20) Algol68_Variable arrayName;
}
