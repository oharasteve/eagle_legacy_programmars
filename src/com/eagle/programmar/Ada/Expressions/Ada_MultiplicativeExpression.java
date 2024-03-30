// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Ada_MultiplicativeExpression extends PrecedenceOperator
{
	public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ada_MultOper operator;
	public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Ada_MultOper extends TokenChooser
	{
		public @CHOICE Ada_PunctuationChoice operator = new Ada_PunctuationChoice("*", "/");
		public @CHOICE Ada_Keyword MOD = new Ada_Keyword("mod");
	}
}