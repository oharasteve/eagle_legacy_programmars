// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tokens.PrecedenceOperator;

public class C_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) C_Expression var = new C_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) C_PunctuationChoice equals = new C_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=", ">>=",
			">>>=", "&=", "^=", "|=");
	public @S(30) @OPT @SYNTAX(CMacro_Syntax.class) CMacro_StatementOrComment macro; // What the ...
	public @S(40) C_Expression rightAsg = new C_Expression(this, AllowedPrecedence.ATLEAST);
}
