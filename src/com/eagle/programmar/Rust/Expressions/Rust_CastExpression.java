// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 22, 2026

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_CastExpression extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Rust_KeywordChoice type = new Rust_KeywordChoice("i32", "f64");
	public @S(20) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
	public @S(30) Rust_Keyword FROM = new Rust_Keyword("from");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression expression;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (type.getValue())
		{
		case "i32":
			double x = interpreter.getDoubleValue(expression);
			interpreter.pushInt((int) x);
			break;
		case "f64":
			int y = interpreter.getIntValue(expression);
			interpreter.pushDouble(y);	// Don't need a cast from int to double
			break;
		default:
			throw new RuntimeException("Unexpected cast function: " + type.getValue());
		}
	}
	
	public static Rust_Expression newCastExpression(String type, Rust_Expression expr,
			AbstractToken source)
	{
		Rust_CastExpression castExpr = new Rust_CastExpression();
		castExpr.type.setValue(type);
		castExpr.leftParen = new PunctuationLeftParen();
		castExpr.expression = expr;
		castExpr.rightParen = new PunctuationRightParen();
		
		return Rust_Generator.wrapExpression(castExpr);
	}
}
