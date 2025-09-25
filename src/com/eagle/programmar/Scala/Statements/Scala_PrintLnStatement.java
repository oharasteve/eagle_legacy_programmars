// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_PrintLnStatement extends PrimaryOperator
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) Scala_Keyword PRINTLN = new Scala_Keyword("println");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) Scala_EOLN eoln;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String line = interpreter.getStrValue(expr);
		System.out.println(line);
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression line = transformer.transformExpression(generator, expr);
		return generator.newPrintStatement(line, true, false, this);
	}
}
