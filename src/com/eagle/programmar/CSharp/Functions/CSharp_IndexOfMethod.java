// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 16, 2025

package com.eagle.programmar.CSharp.Functions;

import com.eagle.generate.EagleGenerator.SubstringSCEnum;
import com.eagle.generate.Functions.EagleGenerateIndexOf;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_IndexOfMethod extends PrimaryOperator
		implements EagleRunnable, EagleGenerateIndexOf<CSharp_Expression>
{
	public @S(30) CSharp_Expression string;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(10) @NOSPACE CSharp_Keyword INDEXOF = new CSharp_Keyword("IndexOf");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE CSharp_Expression pattern;
	public @S(60) @OPT @NOSPACE CSharp_Index_SC scExpr;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;

	public static class CSharp_Index_SC extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) CSharp_Expression start;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(string);
		String patt = interpreter.getStrValue(pattern);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushInt(str.indexOf(patt, sc));
		}
		else
		{
			interpreter.pushInt(str.indexOf(patt));
		}
	}
	
	@Override
	public CSharp_Expression generateIndexOf(CSharp_Expression str, CSharp_Expression patt,
			CSharp_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		this.string = str;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.pattern = patt;
		if (sc != null)
		{
			this.scExpr = new CSharp_Index_SC();
			this.scExpr.setPresent(true);
			this.scExpr.comma = new PunctuationComma();
			this.scExpr.start = sc;
		}
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
