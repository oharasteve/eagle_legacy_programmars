// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_ExpressionStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @NEWLINE Java_Expression expr;
	public @S(20) @NOSPACE PunctuationSemicolon semicolon;
	public @S(30) @OPT Java_Comment comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
	
	public static Java_ExpressionStatement newExpressionStatement(AbstractExpression expr, AbstractToken source)
	{
		Java_ExpressionStatement stmt = new Java_ExpressionStatement();
		stmt.expr = (Java_Expression) expr;
		stmt.semicolon = new PunctuationSemicolon();
		stmt.setTransformationSource(source);
		return stmt;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, expr);
		return generator.newExpressionStatement(newExpr, this);
	}
}
