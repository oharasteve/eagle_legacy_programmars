// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class CSharp_SubfieldExpression extends PrecedenceOperator
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT CSharp_Punctuation question = new CSharp_Punctuation('?');
	public @S(30) @NOSPACE PunctuationPeriod dot;
	public @S(40) @NOSPACE CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	public CSharp_Expression generateSubfield(CSharp_Expression leftExpr,
			CSharp_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.dot = new PunctuationPeriod();
		this.right = rightExpr;
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
