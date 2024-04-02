// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class TCL_ConditionalOrExpression extends PrecedenceOperator
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_OrOperator orOper;
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class TCL_OrOperator extends TokenChooser
	{
		public @CHOICE TCL_Keyword OR = new TCL_Keyword("or");
		public @CHOICE TCL_Punctuation orOper = new TCL_Punctuation("||");
	}
}