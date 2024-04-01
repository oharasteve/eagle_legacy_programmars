// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class AWK_GetLine extends PrimaryOperator
{
	public @S(10) @DOC("#index-getline-command") AWK_Keyword GETLINE = new AWK_Keyword("GETLINE");
	public @S(20) AWK_Variable var;
	public @S(30) AWK_Punctuation lessThan = new AWK_Punctuation('<');
	public @S(40) AWK_Variable file;
}
