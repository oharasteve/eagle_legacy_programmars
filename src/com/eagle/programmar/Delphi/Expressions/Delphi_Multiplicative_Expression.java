// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Delphi_Multiplicative_Expression extends PrecedenceOperator 
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Multiplicative_Operator multOp;
	public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Delphi_Multiplicative_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("*", "/");
		public @CHOICE Delphi_KeywordChoice word = new Delphi_KeywordChoice("Div", "Mod", "And", "Shl", "Shr", "As");
	}
}
