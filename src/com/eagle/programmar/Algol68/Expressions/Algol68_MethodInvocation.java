// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Statements.Algol68_FunctionCall.Algol68_FunctionArguments;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_MethodInvocation extends PrimaryOperator
{
	public @S(10) Algol68_Variable methodName;
	public @S(20) @OPT Algol68_Punctuation question = new Algol68_Punctuation("?");
	public @S(30) Algol68_FunctionArguments args;
}
