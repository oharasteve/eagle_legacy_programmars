// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Algol68_RelationalExpression extends PrecedenceOperator
{
	public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Algol68_RelOp relOp;
	public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

	public static class Algol68_RelOp extends TokenChooser
	{
		public @CHOICE Algol68_PunctuationChoice symbol = new Algol68_PunctuationChoice("<", ">", "<=", ">=", "=", "~=");
		public @CHOICE Algol68_KeywordChoice word = new Algol68_KeywordChoice("NE");
	}
}
