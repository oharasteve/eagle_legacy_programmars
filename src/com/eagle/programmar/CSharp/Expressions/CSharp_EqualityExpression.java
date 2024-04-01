// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class CSharp_EqualityExpression extends PrecedenceOperator
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_EqualityOperator equalityOperator;
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	public static class CSharp_EqualityOperator extends TokenChooser
	{
		public @CHOICE CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("==", "!=", "??");
		public @CHOICE CSharp_KeywordChoice asIs = new CSharp_KeywordChoice("as", "is");
	}
}
