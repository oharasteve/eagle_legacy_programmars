// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class Rust_FindMethod extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Rust_Variable string;
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword FIND = new Rust_Keyword("find");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression pattern;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(string);
		String patt = interpreter.getStrValue(pattern);
		interpreter.pushInt(str.indexOf(patt));
	}

	public static Rust_Expression generateFind(Rust_Variable str, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Rust_FindMethod indexMeth = new Rust_FindMethod();
		
		indexMeth.string = str;
		
		indexMeth.dot = new PunctuationPeriod();
		indexMeth.leftParen = new PunctuationLeftParen();
		indexMeth.pattern = patt;
		indexMeth.rightParen = new PunctuationRightParen();

		indexMeth.setTransformationSource(source);
		return Rust_Generator.wrapExpression(indexMeth);
	}
}
