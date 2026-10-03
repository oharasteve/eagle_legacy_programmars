// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Javascript_ReturnStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("js_functions.asp") Javascript_Keyword RETURN = new Javascript_Keyword("return");
	public @S(20) @OPT SeparatedList<Javascript_Expression, PunctuationComma> expressions; // return a(), b: a comma expression (Oct 2026)
	public @S(30) @OPT PunctuationSemicolon semicolon;

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression expr = transformer.transformExpression(generator, lastExpression());
		return generator.newReturnStatement(expr, this);
	}

	/** The value returned: the last expression of `return a, b`, or null for a bare `return`. */
	private Javascript_Expression lastExpression()
	{
		if (expressions == null || !expressions.isPresent()) return null;
		return expressions.getPrimaryElement(expressions.getPrimaryCount() - 1);
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue val = JsValues.undefined();
		if (expressions != null && expressions.isPresent())
		{
			int count = expressions.getPrimaryCount();
			for (int i = 0; i < count; i++) val = rt.eval(expressions.getPrimaryElement(i));
		}
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Javascript_Function)
			{
				Javascript_Function func = (Javascript_Function) parent;
				if (func._returnMetrics != null) func._returnMetrics.returned(val.getType());
				break;
			}
			parent = parent.getParent();
		}
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
