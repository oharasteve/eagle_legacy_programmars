// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Reference;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.programmar.Javascript.Terminals.Javascript_TemplateLiteral;
import com.eagle.tokens.PrimaryOperator;
import java.util.ArrayList;
import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_TemplateExpr extends PrimaryOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @OPT Javascript_Function_Reference func;
	public @S(20) Javascript_TemplateLiteral template;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.pushEagleValue(JsRuntime.of(interpreter).template(template.getValue()));
	}

	/** `a ${b} c` becomes the generator's concatenation; each ${...} is parsed as the runtime parses it. */
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (func != null && func.isPresent())
		{
			throw new RuntimeException("Cannot translate a tagged template (" + func.getValue() + "`...`) yet");
		}
		AbstractLanguage lang = null;
		for (AbstractToken t = this; t != null; t = t.getParent())
		{
			if (t instanceof AbstractLanguage) lang = (AbstractLanguage) t;
		}
		ArrayList<AbstractExpression> pieces = new ArrayList<AbstractExpression>();
		for (String[] part : JsRuntime.templateParts(template.getValue()))
		{
			if (part[0] != null)
			{
				pieces.add(generator.newLiteralExpression(part[0], this));
				continue;
			}
			Javascript_Expression expr = new Javascript_Expression();
			if (lang == null || !generator._parser.parseLine(part[1], lang, expr))
			{
				throw new RuntimeException("Cannot parse the template expression ${" + part[1] + "}");
			}
			expr.setParent(this);
			pieces.add(transformer.transformExpression(generator, expr));
		}
		return generator.newConcatExpression(pieces, this);
	}
}
