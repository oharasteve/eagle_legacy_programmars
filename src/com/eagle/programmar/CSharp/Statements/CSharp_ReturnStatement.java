// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Method;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_ReturnStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @S(20) @DOC("statements/jump-statements#the-return-statement") CSharp_Keyword RETURN = new CSharp_Keyword("return");
	public @S(30) @OPT CSharp_Expression expression;
	public @S(40) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);

		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof CSharp_Method)
			{
				CSharp_Method meth = (CSharp_Method) parent;
				meth._returnMetrics.returned(val.typeName());
				break;
			}
			parent = parent.getParent();
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

	public CSharp_Statement generateReturn(CSharp_Expression ret, AbstractToken source)
	{
		if (ret != null)
		{
			this.expression = ret;
			this.expression.setPresent(true);
		}
		this.semicolon = new PunctuationSemicolon();
		this.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(this);
	}
}
