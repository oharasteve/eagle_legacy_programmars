// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Statements.Ada_FunctionCall.Ada_FunctionArguments;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Ada_MethodInvocation  extends PrimaryOperator
{
	public @S(10) Ada_Variable methodName;
	public @S(20) @OPT Ada_Punctuation question = new Ada_Punctuation("?");
	public @S(30) Ada_FunctionArguments args;
}
