// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_FunctionCall extends PrimaryOperator
{
	public @S(10) @OPT CPlus_NamespaceList namespace;
	public @S(20) C_Variable functionName;
	public @S(30) @OPT C_Generic generic;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT C_ArgumentList argList;
	public @S(60) PunctuationRightParen rightParen;
}
