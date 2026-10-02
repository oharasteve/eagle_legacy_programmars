// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_ArgumentList;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Javascript_ExpressionList extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<Javascript_Comment> comment;
	public @S(30) @OPT Javascript_ArgumentList valueList;
	public @S(40) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// `{ a, b }` lands here before the object literal rule: shorthand properties.
		JsRuntime rt = JsRuntime.of(interpreter);
		JsValues.JsObject o = new JsValues.JsObject();
		if (valueList != null && valueList.isPresent())
		{
			java.util.ArrayList<Javascript_Expression> exprs = new java.util.ArrayList<Javascript_Expression>();
			exprs.add(valueList.arg);
			if (JsRuntime.has(valueList.moreArgs))
				for (Javascript_ArgumentList.Javascript_MoreArguments more : valueList.moreArgs._elements) exprs.add(more.arg);
			for (Javascript_Expression e : exprs)
			{
				if (e.getWhich() instanceof Javascript_VariableExpression)
				{
					String name = Javascript_Variable.firstName(((Javascript_VariableExpression) e.getWhich()).variable);
					o.set(name, rt.read(name));
				}
				else if (e.getWhich() instanceof Javascript_EllipsisExpr)
				{
					EagleValue from = rt.eval(((Javascript_EllipsisExpr) e.getWhich()).expr);
					if (from instanceof JsValues.JsObject) o.props.putAll(((JsValues.JsObject) from).props);
				}
				else throw new RuntimeException("Cannot read " + e.getWhich().getClass().getSimpleName() + " as an object property");
			}
		}
		interpreter.pushEagleValue(o);
	}
}
