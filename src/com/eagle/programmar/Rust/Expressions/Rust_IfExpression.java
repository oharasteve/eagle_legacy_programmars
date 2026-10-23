// Copyright Eagle Legacy Modernization, 2010-date
// if as an expression, for the conditional operator of other languages (Oct 2026)

package com.eagle.programmar.Rust.Expressions;

import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.generate.TypeEnum;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Rust_IfExpression extends PrimaryOperator
{
	public @S(10) Rust_Keyword IF = new Rust_Keyword("if");
	public @S(20) Rust_Expression condition;
	public @S(30) PunctuationLeftBrace leftBrace1;
	public @S(40) Rust_Expression whenTrue;
	public @S(50) PunctuationRightBrace rightBrace1;
	public @S(60) Rust_Keyword ELSE = new Rust_Keyword("else");
	public @S(70) PunctuationLeftBrace leftBrace2;
	public @S(80) Rust_Expression whenFalse;
	public @S(90) PunctuationRightBrace rightBrace2;

	public static Rust_Expression generateIf(Rust_Expression condition, Rust_Expression whenTrue,
			Rust_Expression whenFalse, AbstractToken source)
	{
		Rust_IfExpression ifExpr = new Rust_IfExpression();
		ifExpr.condition = condition;
		ifExpr.leftBrace1 = new PunctuationLeftBrace();
		ifExpr.whenTrue = owned(whenTrue);
		ifExpr.rightBrace1 = new PunctuationRightBrace();
		ifExpr.leftBrace2 = new PunctuationLeftBrace();
		ifExpr.whenFalse = owned(whenFalse);
		ifExpr.rightBrace2 = new PunctuationRightBrace();
		ifExpr.setTransformationSource(source);
		return Rust_Generator.wrapExpression(ifExpr);
	}

	/** Both arms must have one type; a string literal arm becomes a String, as the generator's strings are. */
	private static Rust_Expression owned(Rust_Expression arm)
	{
		if (arm.getWhich() instanceof Rust_Literal) return Rust_ToStringMethod.generateString(TypeEnum.STRING, arm, null);
		return arm;
	}
}
