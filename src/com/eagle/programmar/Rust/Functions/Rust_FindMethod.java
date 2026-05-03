// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;

public class Rust_FindMethod extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Rust_Variable string;
	public @S(20) @NOSPACE @OPT Rust_FindSubscript subscript;
	public @S(30) @NOSPACE PunctuationPeriod dot;
	public @S(40) @NOSPACE Rust_Keyword FIND = new Rust_Keyword("find");
	public @S(50) @NOSPACE PunctuationLeftParen leftParen;
	public @S(60) @NOSPACE Rust_Expression pattern;
	public @S(70) @NOSPACE PunctuationRightParen rightParen;

	public static class Rust_FindSubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE Rust_Expression sc;
		public @S(30) @NOSPACE Rust_Punctuation dots = new Rust_Punctuation("..");
		public @S(40) @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			throw new RuntimeException("Need to implement");
		}
		
		String str = interpreter.getStrValue(string);
		String patt = interpreter.getStrValue(pattern);
		interpreter.pushInt(str.indexOf(patt));
	}

	// This is a good reason to hate Rust ...
    // let index: i32 = text.find('w')  // 1. ".find()" returns Option<usize> and we want i32
    //        .map(|idx| idx as i32)    // 2. Convert Option<usize> to Option<i32>
    //        .unwrap_or(-1);           // 3. Handle None (e.g., return -1 if not found)
	
	public static Rust_Expression generateFind(Rust_Variable str, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Rust_FindMethod indexMeth = new Rust_FindMethod();
		
		indexMeth.string = str;
		
		if (sc != null)
		{
			indexMeth.subscript = new Rust_FindSubscript();
			indexMeth.subscript.leftBracket = new PunctuationLeftBracket();
			indexMeth.subscript.sc = sc;
			indexMeth.subscript.rightBracket = new PunctuationRightBracket();
			indexMeth.subscript.setPresent(true);
		}
		
		indexMeth.dot = new PunctuationPeriod();
		indexMeth.leftParen = new PunctuationLeftParen();
		indexMeth.pattern = patt;
		indexMeth.rightParen = new PunctuationRightParen();

		indexMeth.setTransformationSource(source);
		Rust_Expression find = Rust_Generator.wrapExpression(indexMeth);
		Rust_Expression map = Rust_MapMethod.generateMap(find, source);
		Rust_Expression minusOne = Rust_Number.createExpression(-1);
		if (sc != null)
		{
			minusOne = Rust_AdditiveExpression.generateAdditive(null, minusOne,
					AdditiveEnum.MINUS, sc, null);
		}
		Rust_Expression result = Rust_UnwrapMethod.generateUnwrap(map, minusOne, source);

		if (sc != null)
		{
			// .find(xx, 3) returns 0, 1, ... and we need 3, 4, ...
			return Rust_AdditiveExpression.generateAdditive(null, result,
					AdditiveEnum.PLUS, sc, source);
		}
		return result;
	}
}
