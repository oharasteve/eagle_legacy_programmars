// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024 (starts_with); ends_with in its shape, Oct 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Expressions.Rust_BorrowExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_EndsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword ENDSWITH = new Rust_Keyword("ends_with");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression arg;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String text = interpreter.getStrValue(left);
		String patt = interpreter.getStrValue(arg);
		interpreter.pushBool(text.endsWith(patt));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		AbstractExpression thePattern = transformer.transformExpression(generator, arg);
		return generator.newEndsWithFunction(theExpr, thePattern, this);
	}

	public static Rust_Expression generateEndsWith(Rust_Expression expr, Rust_Expression patt, AbstractToken source)
	{
		Rust_EndsWithMethod endsExpr = new Rust_EndsWithMethod();
		if (expr.getWhich() instanceof Rust_ParenthesizedExpression)
		{
			Rust_ParenthesizedExpression paren = (Rust_ParenthesizedExpression) expr.getWhich();
			endsExpr.left = paren.expressions.first();	// Remove extra set of parens
		}
		else
		{
			endsExpr.left = expr;
		}
		endsExpr.dot = new PunctuationPeriod();
		endsExpr.leftParen = new PunctuationLeftParen();
		endsExpr.arg = patt;

		// As for starts_with: a literal pattern without its .to_string(), a variable borrowed
		AbstractToken which = patt.getWhich();
		if (which instanceof Rust_ToStringMethod)
		{
			Rust_ToStringMethod toStr = (Rust_ToStringMethod) which;
			endsExpr.arg = toStr.left;
		}
		else if (which instanceof Rust_VariableExpression)
		{
			endsExpr.arg = Rust_BorrowExpression.generateBorrow(patt, null);
		}
		endsExpr.rightParen = new PunctuationRightParen();

		endsExpr.setTransformationSource(source);
		return Rust_Generator.wrapExpression(endsExpr);
	}
}
