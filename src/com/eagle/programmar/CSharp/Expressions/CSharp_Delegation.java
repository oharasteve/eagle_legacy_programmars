// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameters;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_Delegation extends PrimaryOperator
{
	public @S(10) CSharp_Keyword DELEGATE = new CSharp_Keyword("delegate");
	public @S(20) CSharp_MethodParameters parameters;
	public @S(30) @NEWLINE CSharp_MethodBody body;
}
