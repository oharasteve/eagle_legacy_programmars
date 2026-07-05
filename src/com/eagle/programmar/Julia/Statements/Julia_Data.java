// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Symbols.Julia_Variable_Definition;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Julia_Data extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @S(10) Julia_KeywordChoice VAR = new Julia_KeywordChoice("const", "var");
	public @S(20) Julia_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Julia_Expression value;
	public @S(50) Julia_EOLN eoln;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(value);
		interpreter.setSymbol(id, id.toString(), val);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// See if the Declaration has some assignments in the metrics file
		TypeEnum typeEnum = transformer.findAssignMetric(id);
		AbstractType newType = generator.transformType(typeEnum, null, this);

		String name = id.getValue();
		AbstractExpression initial = transformer.transformExpression(generator, value);
		StaticEnum isConst = StaticEnum.NONE;
		if (VAR.getValue().equals("const"))
		{
			isConst = StaticEnum.CONST;
		}
		return generator.newDataDeclaration(isConst, name, null, newType, initial, this);
	}
}
