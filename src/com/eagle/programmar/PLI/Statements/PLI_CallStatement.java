// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class PLI_CallStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable,
				EagleTransformableStatement
{
	public @S(10) @DOC("7.6") PLI_Keyword CALL = new PLI_Keyword("CALL");
	public @S(20) PLI_Expression arguments;
	public @S(30) @OPT PLI_Comment comment;
	public @S(40) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(arguments);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression newExpr = transformer.transformExpression(generator, arguments);
		return generator.newExpressionStatement(newExpr, CALL);
	}
}
