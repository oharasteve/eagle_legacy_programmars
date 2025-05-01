// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Statements.Eagle_Generate_Return;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement,
				Eagle_Generate_Return<Java_Statement, Java_Expression>
{
	public @S(10) @NEWLINE @DOC("statements.html#14.17") Java_Keyword RETURN = new Java_Keyword("return");
	public @S(20) @OPT Java_Expression expression;
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expression != null && expression.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(val);
		}
		return Eagle_Statement_Result.RETURN;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression expr = transformer.transformExpression(generator, expression);
		return generator.newReturnStatement(expr, this);
	}

	@Override
	public Java_Statement generateReturn(Java_Expression ret, AbstractToken source)
	{
		if (ret != null)
		{
			this.expression = ret;
			this.expression.setPresent(true);
		}
		this.semicolon = new PunctuationSemicolon();
		this.setTransformationSource(source);
		return Java_Generator.wrapStatement(this);
	}
}
